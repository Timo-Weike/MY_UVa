import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Main{

    public static void main(String[] args){
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        try{
            int n = Integer.valueOf(br.readLine());
            for(int i=0; i < n; i++){
                String dna = br.readLine();
                String out = analyse(dna);
                System.out.println(out);
            }
        }catch(Exception e){
        }
    }

    private static String analyse(String str){
        String[] suffixe = new String[str.length()];
        for(int i=0, n=str.length(); i < n; i++){
            suffixe[i] = str.substring(i,n);
        }
        Arrays.sort(suffixe);
        String goal = "";
        int n = 0;

        a:for(int i=0; i < suffixe.length-1; i++){
            int j = i+1;
            int N = Math.min(suffixe[i].length(), suffixe[j].length());
            if(N < goal.length()) continue;
            String prefix = "";
            b:for(int x=0; x < N; x++){
                if(suffixe[i].charAt(x) == suffixe[j].charAt(x)){
                    prefix += suffixe[i].charAt(x);
                } else {
                    break b;
                }
            }
            if(goal.equals(prefix)){
                n++;
            }
            if(goal.length() < prefix.length()){
                goal = prefix;
                n = 2;
            }
        }

        if(!goal.equals("")){
            return goal + " " + n;
        } else {
            return "No repetitions found!";
        }
    }
}
