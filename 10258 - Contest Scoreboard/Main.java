import java.io.*;
import java.util.*;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Main{

    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int n = Integer.valueOf(br.readLine());

            br.readLine();
            creadStats(br);

            for(int i=1; i < n; i++){
                System.out.println();
                creadStats(br);
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void creadStats(BufferedReader in) throws IOException{
        Map<Integer, Teilnehmer> map = new HashMap<Integer, Teilnehmer>();

        String input = in.readLine();
        do{
            String[] inputS = input.split(" ");
            int tID  = Integer.valueOf(inputS[0]);
            int pID  = Integer.valueOf(inputS[1]);
            int time = Integer.valueOf(inputS[2]);
            char L   = inputS[3].charAt(0);

            Teilnehmer t = map.get(tID);
            if(t == null){
                t = new Teilnehmer(tID);
                map.put(tID, t);
            }

            t.addSubmission(pID, time, L);

            input = in.readLine();
        }while(input != null && !(input.equals("") || input.equals(" ")));

        List<Teilnehmer> list = new LinkedList<Teilnehmer>(map.values());

        Collections.sort(list);

        for(Teilnehmer t : list){
            System.out.println(t.toString());
        }

    }

    //---------------------------------------------------

    public static class Teilnehmer implements Comparable<Teilnehmer>{

        private Map<Integer, Problem> probleme = new HashMap<Integer, Problem>();
        private int ID;

        public Teilnehmer(int ID){
            this.ID = ID;
        }

        public void addSubmission(int pID, int time, char L){
            if(L == 'C' || L == 'I'){
                Problem p = probleme.get(pID);
                if(p == null){
                    p = new Problem();
                    probleme.put(pID, p);
                }
                p.addSubmission(time, L);
            }
        }

        public int compareTo(Teilnehmer teil){
            int[]   myDatas = this.getDatas();
            int[] yourDatas = teil.getDatas();

            if(myDatas[0] != yourDatas[0]){
                if(myDatas[0] < yourDatas[0]) return  1; //ich habe weniger Probleme gelöst
                else
                if(myDatas[0] > yourDatas[0]) return -1; 
            }
            if(myDatas[1] != yourDatas[1]){
                if(myDatas[1] < yourDatas[1]) return -1;
                else
                if(myDatas[1] > yourDatas[1]) return  1;
            }
            return ((Integer)this.ID).compareTo(teil.ID);
        }

        public int[] getDatas(){
            int[] n = new int[2];
            for(Problem p : probleme.values()){
                if(p.isSolved()){
                    n[0]++;
                    n[1] += p.penaltyTime();
                }
            }
            return n;
        }

        public String toString(){
            int[] datas = getDatas();
            String str = ID + " " + datas[0] + " " + datas[1];
            return str;
        }

    }

    public static class Problem{

        private int incorrectSubmissions = 0;
        private boolean solved = false;
        private int timeSolved = 0;

        public void addSubmission(int time, char L){
            if(!solved){
                if(L == 'I'){
                    incorrectSubmissions++;
                    return;
                }else if(L == 'C'){
                    solved = true;
                    timeSolved = time;
                    return;
                }
            }
        }

        public boolean isSolved(){
            return solved;
        }

        public int penaltyTime(){
            if(solved){
                int time = timeSolved + (20 * incorrectSubmissions);
                return time;
            } else {
                return 0;
            }
        }

    }

}

