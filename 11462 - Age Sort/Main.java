import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args){
        try{
            StringTokenizer st;
            int n;
            int[] arr;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder("");
            boolean flag=true;
            while(true){
                n = Integer.parseInt(br.readLine());
                if (n == 0) {
                    break;
                }
                if(!flag) sb.append("\n");
                st = new StringTokenizer(br.readLine());
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(arr);
                for (int i = 0; i < n; i++) {
                    if (i > 0) {
                        sb.append(" ");
                    }
                    sb.append(arr[i]);
                }
                flag = false;
            }
            System.out.println(sb);
        }catch (IOException e){
            
        }
    }
    
}
