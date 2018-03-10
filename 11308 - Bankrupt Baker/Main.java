import java.io.*;
import java.util.*;

class Main{

    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main(String[] args){

        final int t = Integer.valueOf(ReadLn(5).trim());
        for(int i=0; i < t; i++){
            String binderName = ReadLn(200).trim();
            System.out.println(binderName.toUpperCase());

            //Einlesen der Parameter
            StringTokenizer params = new StringTokenizer(ReadLn(20));
            final int anzahlZutaten = Integer.valueOf(params.nextToken()); // m
            final int anzahlRezepte = Integer.valueOf(params.nextToken()); // n
            final int budget = Integer.valueOf(params.nextToken()); // b

            //Zutatenliste einlesen
            final TreeMap<String, Zutat> zutaten = new TreeMap<String, Zutat>();
            for(int j=0; j < anzahlZutaten; j++){
                params = new StringTokenizer(ReadLn(200));
                String name = params.nextToken();
                int preis = Integer.valueOf(params.nextToken());
                zutaten.put(name, new Zutat(name, preis));
            }

            //Rezepte einlesen
            final TreeSet<Rezept> rezepte = new TreeSet<Rezept>();
            for(int j=0; j < anzahlRezepte; j++){
                String name = ReadLn(200).trim();
                int zutatenImRezept = Integer.valueOf(ReadLn(5).trim());
                Rezept r = new Rezept(name);
                for(int n=0; n < zutatenImRezept; n++){
                    params = new StringTokenizer(ReadLn(200));
                    if(r.preis <= budget){
                        String nameZ = params.nextToken();
                        int anzahl = Integer.valueOf(params.nextToken());
                        r.preis += zutaten.get(nameZ).preis * anzahl;
                    }
                }
                if(r.preis <= budget){
                    rezepte.add(r);
                }
            }

            if(rezepte.size() < 1){
                System.out.println("Too expensive!");
            } else {
                for(Rezept r : rezepte){
                    System.out.println(r.name);
                }
            }
            System.out.println();
        }

    }

    private static class Zutat{
        public final String name;
        public final int preis;
        public Zutat(String name, int preis){
            this.name = name;
            this.preis = preis;
        }
    }

    private static class Rezept implements Comparable<Rezept>{
        public final String name;
        public int preis;
        public Rezept(String name){
            this.name = name;
            this.preis = 0;
        }

        public int compareTo(Rezept r){
            Integer tmp = this.preis;
            int i = tmp.compareTo(r.preis);
            if(i==0){
                return this.name.compareTo(r.name);
            } else {
                return i;
            }
        }
    }

}



