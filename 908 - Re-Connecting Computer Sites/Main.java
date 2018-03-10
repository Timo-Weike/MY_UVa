import java.util.Scanner;
import java.util.PriorityQueue;

public class Main{

    public static void main(String[] args){
        //---------
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();
        PriorityQueue<Connection> connections = new PriorityQueue<Connection>();
        boolean first = true;

        while(in.hasNextInt()){
            int n, oldCost = 0, k, newCost = 0;	

            n = in.nextInt();
            if(!first)
                out.append("\n");

            MST clustores = new MST(n);
            for(int i=0; i < n-1; i++){
                in.nextInt(); in.nextInt();
                oldCost += in.nextInt();
            }

            for(int i=0; i < 2; i++){
                k = in.nextInt();
                for(int j=0; j < k; j++){
                    int u = in.nextInt()-1;
                    int v = in.nextInt()-1;
                    int kosten = in.nextInt();
                    if(u != v){
                        Connection c = new Connection(u, v, kosten);
                        connections.add(c);
                    }
                }
            }
            //System.out.println(clustores);
            while(!connections.isEmpty()){
                Connection c = connections.poll();
                if(!clustores.isSameCluster(c.u, c.v)){
                    newCost += c.kosten;
                    clustores.mergeCluster(c.u, c.v);
                    //System.out.println(clustores);
                }
            }

            out.append(oldCost + "\n" + newCost + "\n");
            first = false;
        }
        System.out.print(out);
    }

    //------------
    private static class Connection implements Comparable<Connection>{
        private final int u;
        private final int v;
        private final int kosten;

        public Connection(int v, int u, int kosten){
            this.u = u;
            this.v = v;
            this.kosten = kosten;
        }

        public int compareTo(Connection c){
            if(kosten > c.kosten)
                return 1;
            else
                return -1;
        }

    }

    private  static class MST{

        private int[] clusters;

        public MST(int n){
            clusters = new int[n];
            for(int i=0; i < n; i++){
                clusters[i] = i;
            }
        }

        public boolean isSameCluster(int v, int u){
            return findRootCluster(v) == findRootCluster(u);
        }

        private int findRootCluster(int v){
            if(clusters[v] == v)
                return v;
            else {
                int vIsInCluster = findRootCluster(clusters[v]);
                clusters[v] = vIsInCluster; //reduziert die späteren rekrusiven aufrufe
                return vIsInCluster;
            }
        }

        public void mergeCluster(int v, int u){
            int vRootCluster = findRootCluster(v);
            int uRootCluster = findRootCluster(u);
            clusters[vRootCluster] = uRootCluster;
        }

    }

}
