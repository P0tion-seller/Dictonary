package sample;

import java.security.Key;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class Data {

    private HashSet<String> EngWord= new HashSet<>();
    private HashSet<String>  AWord = new HashSet<>();
    private Map<String,String> Plural = new HashMap<>();

    private String IPA;// international phonetic alphabet for future release

    private String wordType;
    private HashSet<Data> Node;

    private ArrayList<String> Example = new ArrayList<>(); // sentence example in arabic
    private ArrayList<String>  sen = new ArrayList<>();//sentence meaning


    public Data(){
        Node = new HashSet<Data>();
    }

    public Data(String eng,String a,String type){

        try {
            String temp [] = eng.split(";");
            for(String x : temp){
                EngWord.add(x);
                AWord. add(a);
                wordType = type;
                Node = new HashSet<Data>();
            }
        }catch (PatternSyntaxException e){

            EngWord.add(eng);
            AWord.add(a);
            wordType = type;
            Node = new HashSet<Data>();

        }


    }

    public void addWord(String eng,String a,String type){
        Data word = new Data(eng,a,type);
        Node.add(word);
    }

    public void addWord(Data d){
        Data word = d;
        Node.add(word);
    }

    public ArrayList<Data> findWord(String Word) throws Exception{
        if (Node == null || Node.isEmpty()){
            return null;
        }
        ArrayList<Data> allWords= new ArrayList<>();
        for (Data x :Node){
            if(x.EngWord.contains(Word) ||x.AWord.contains(Word))
                allWords.add(x);
           // if(x.EngWord.equals(Word))
                //return x;
        }
        return allWords;
    }

    public void printList(){
        for (Data x :Node){
            System.out.println(x.EngWord +": "+x.getWordType()+": "+x.AWord);
        }
    }


    public HashSet<String> getEnglishWord(){
        return  EngWord;
    }

    public HashSet<String> getArabicWord(){
        return AWord;
    }

    public void setEnglishWord(String setWord){
        EngWord.add(setWord);
    }

    public void setArabicWord(String setWord){ AWord.add(setWord); }

    public String getIPA(){
        return IPA;
    }

    public ArrayList<String> getSentence() {
        return Example;
    }

    public void setSentence(String e) {
        Example.add(e);
    }

    public void setSenMeaning(String s) {
        sen.add(s);
    }

    public ArrayList<String> getSenMeaning() {
        return  sen;
    }

    public void setWordType(String w) {
        this.wordType = w;
    }

    public String getWordType() {
        return  wordType;
    }

    public  String getPlural(String key) {
        return Plural.get(key);
    }

    public void setPlural(String key,String plural) {
        Plural.put(key,plural);
    }
}
