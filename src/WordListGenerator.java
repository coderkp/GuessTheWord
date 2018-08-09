import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordListGenerator {

    public List<String> getWordList(int wordLength)
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
    public static void main(String args[])
    {
        WordListGenerator wlg = new WordListGenerator();
        System.out.println(wlg.getWordList(4));
    }
}
