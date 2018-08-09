import java.io.*;
import java.util.*;

public class WordListGenerator {

    public ArrayList<String> getWordList(int wordLength)
    {   ArrayList<String> words = new ArrayList<>();
        File file = new File("./src/sowpods.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
               if(st.length()!=wordLength)
                   continue;
               words.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
    public static void main(String[] args)
    {
        System.out.println("GUESS THE WORD\nWhat difficulty will you play?\n1. Easy\n2. Medium \n3. Hard\n");
        Scanner sc=new Scanner(System.in);
        int userInput = sc.nextInt();
        userInput += 3;
    }
}
