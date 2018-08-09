import java.io.*;
import java.util.*;

public class WordListGenerator {

    static public ArrayList<String> validWords;
    static public String secretWord;

    static void setSecretWord(){
        Random rand = new Random();
        secretWord = validWords.get(rand.nextInt(validWords.size()));
    }


    static String getNextWord(){
        return validWords.get(0);
    }

    static void removeInvalidWords(String word,int matchedCharacter){
        ArrayList<String> InvalidWords= new ArrayList<String>();
        for(int x=0;x<validWords.size();x++){
            if(distinctCharCount(word,validWords.get(x))!=matchedCharacter){
                InvalidWords.add(validWords.get(x));
            }
        }
        validWords.removeAll(InvalidWords);
        validWords.remove(word);
    }


    public static int distinctCharCount(String one, String two){
        if(one.equals(two))
            return -1;
        int count=0;
        int[] charFrequencyofOne=new int[26];
        int[] charFrequencyofTwo=new int[26];
        for(int i=0;i<one.length();i++){
            charFrequencyofOne[one.charAt(i)-65]++;
        }
        for(int i=0;i<two.length();i++){
            charFrequencyofTwo[two.charAt(i)-65]++;
        }
        for(int i=0;i<26;i++){
            if(charFrequencyofOne[i]>0 && charFrequencyofTwo[i]>0)
                count++;
        }
        return count;
    }

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
        WordListGenerator wordlistgenerator = new WordListGenerator();
        validWords= wordlistgenerator.getWordList(userInput+3);
        setSecretWord();
        boolean hasSomeoneWon = false;
        while(!hasSomeoneWon){
            String nextWord = getNextWord();
            System.out.println("My guess is: " + nextWord);
            System.out.println("Is my guess correct?(Enter 0 for No and 1 for Yes");
            int guess = sc.nextInt();
            if(guess==1)
            {
                hasSomeoneWon = true;
                System.out.println("I won, noob human.");
                break;
            }
            System.out.println("How many letters matched with your secret word");
            int similarityCount = sc.nextInt();
            removeInvalidWords(nextWord, similarityCount);
            System.out.println("Your Turn. Guess my secret word");
            String userGuess = sc.nextLine();
            userGuess = sc.nextLine();
            //System.out.println(userGuess);
            int userSimilarityCount = distinctCharCount(secretWord, userGuess);
            if(userSimilarityCount == -1){
                hasSomeoneWon = true;
                System.out.println("You win. Gotta improve my algo");
                break;
            }
            System.out.println("Your guess wasn't correct. " + userSimilarityCount + " number of letters matched\nMy Turn");

        }
    }
}
