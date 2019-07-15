package glavna;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
 
public class Glavna
{
 
    public static void main(String[] args) {
       
        List<String> zaustavneRijeci = new ArrayList<>();
       
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/stop-word-list.txt"));
            String line = reader.readLine();
            zaustavneRijeci.add(line);
            //System.out.println(line);
            while (line != null)
            {  
                zaustavneRijeci.add(line);
                System.out.println(line);
                line = reader.readLine();          
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prvi unos:");
        String recenica1 = scanner.nextLine();
        System.out.println("Drugi unos:");
        String recenica2 = scanner.nextLine();
       
        scanner.close();
       
        Map<String, Integer> obeReceniceVektor = new TreeMap<>();
        Map<String, Integer> recenica1Vektor = new TreeMap<>();
        Map<String, Integer> recenica2Vektor = new TreeMap<>();
       
        recenica1 = recenica1.toLowerCase().replaceAll("'", "");
        recenica2 = recenica2.toLowerCase().replaceAll("'", "");
       
        String outputWordBag = "";
        boolean isAStopWord = false;
        for(String word : recenica1.split("[\\s\\.\\,\\!\\?\\-\\\\\\/\\_\\*]+"))
        {
        	        	
            
            isAStopWord = false;
            for(String stopWord : zaustavneRijeci)
            {
                if(word.toLowerCase().equals(stopWord))
                    isAStopWord = true;
            }
            if(!isAStopWord)
            {
            	outputWordBag += word + " ";
            	boolean exists = false;
                for(String mapWord : recenica1Vektor.keySet())
                {
                    if(mapWord.equals(word))
                    {
                        exists = true;
                    }
                }  
                if(exists)
                {
                    int pojavljivanja = recenica1Vektor.get(word);
                    recenica1Vektor.put(word, pojavljivanja+1);
                }
                else
                {
                    recenica1Vektor.put(word, 1);
                }
                exists = false;
                for(String mapWord : obeReceniceVektor.keySet())
                {
                    if(mapWord.equals(word))
                    {
                        exists = true;
                    }
                }  
                if(exists)
                {
                    int pojavljivanja = obeReceniceVektor.get(word);
                    obeReceniceVektor.put(word, pojavljivanja+1);
                }
                else
                {
                    obeReceniceVektor.put(word, 1);
                }
            }
        }
        System.out.println("Prvi korigirani text je: \n" + outputWordBag);
        outputWordBag = "";
        for(String word : recenica2.split("[\\s\\.\\,\\!\\?\\-\\\\\\/\\_\\*]+"))
        {
            isAStopWord = false;
            for(String stopWord : zaustavneRijeci)
            {
                if(word.toLowerCase().equals(stopWord))
                    isAStopWord = true;
            }
            if(!isAStopWord)
            {
            	outputWordBag += word + " ";
            	boolean exists = false;
                for(String mapWord : recenica2Vektor.keySet())
                {
                    if(mapWord.equals(word))
                    {
                        exists = true;
                    }
                }  
                if(exists)
                {
                    int pojavljivanja = recenica2Vektor.get(word);
                    recenica2Vektor.put(word, pojavljivanja+1);
                }
                else
                {
                    recenica2Vektor.put(word, 1);
                }
                exists = false;
                for(String mapWord : obeReceniceVektor.keySet())
                {
                    if(mapWord.equals(word))
                    {
                        exists = true;
                    }
                }  
                if(exists)
                {
                    int pojavljivanja = obeReceniceVektor.get(word);
                    obeReceniceVektor.put(word, pojavljivanja+1);
                }
                else
                {
                    obeReceniceVektor.put(word, 1);
                }
            }
        }
        System.out.println("Drugi ureðeni tekst: \n" + outputWordBag);
        for(String word : obeReceniceVektor.keySet())
        {
            boolean exists = false;
            for(String subWord : recenica1Vektor.keySet())
            {
                if(word.equals(subWord))
                    exists = true;
            }
            if(!exists)
                recenica1Vektor.put(word, 0);
           
            exists = false;
            for(String subWord : recenica2Vektor.keySet())
            {
                if(word.equals(subWord))
                    exists = true;
            }
            if(!exists)
                recenica2Vektor.put(word, 0);
        }
       
        System.out.println("Vektor prvog unosa:");
        for(String word : recenica1Vektor.keySet())
            System.out.print(word + "-"+recenica1Vektor.get(word)+ " ");
        System.out.println("\nVektor drugog unosa: ");
        for(String word : recenica2Vektor.keySet())
            System.out.print(word + "-"+recenica2Vektor.get(word)+" ");
        System.out.println();
 

        Integer sumaKvadrata=0;
        Double slicnostKosinusa=0.0;
        Double JaccardovIndeks= 0.0;
        Integer CardA=0;
        Integer CardB=0;
        Double AB=0.0;
        Double modulA=0.0;
        Double modulB=0.0;
        Integer ApresjekB =0;
        Iterator<Map.Entry<String, Integer>> iter1 = recenica1Vektor.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> iter2 = recenica2Vektor.entrySet().iterator();
        while(iter1.hasNext() && iter2.hasNext()) {
              Map.Entry<String, Integer> e1 = iter1.next();
              Map.Entry<String, Integer> e2 = iter2.next();
              Integer frek1=e1.getValue();
              Integer frek2=e2.getValue();
              sumaKvadrata += (frek1 - frek2) * (frek1 - frek2);
              AB += (frek1*frek2);
              modulA+= frek1*frek1;
              modulB+= frek2*frek2;
             
              if (frek1>0)
                  CardA++;
              if (frek2>0)
                  CardB++;
              if (frek1>0 && frek2 > 0) {
                  ApresjekB++;
              }
            }
        modulA = Math.sqrt(modulA);
        modulB = Math.sqrt(modulB);
        slicnostKosinusa = AB / (modulA * modulB);
        JaccardovIndeks= ((double)ApresjekB / (CardA + CardB - ApresjekB));
        System.out.println("SSD(A,B)=" + sumaKvadrata);
        System.out.println("cos(A,B)=" + slicnostKosinusa);
        System.out.println("J(A,B)=" + JaccardovIndeks);
    }
}