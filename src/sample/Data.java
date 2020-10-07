package sample;

import java.security.Key;
import java.util.*;
import java.util.regex.PatternSyntaxException;


public class Data {

    public static LinkedList<Node> nodelist =  new LinkedList<>();;
    private Node node;


    public Data(Node n) {//constructor
        nodelist.add(n);
    }
    public Data() {//constructor
        System.out.println("beginning Node made.");
    }

    public void addWord(Node n) {
        nodelist.add(n);
    }

    /*
    *
    * Find the word in the dictonary and all other possible words that have the same meaning
    * */
    public ArrayList<Node> findWord(String Word) throws wordNotFound {

        if (nodelist.isEmpty()) {
            return null;
        }
        ArrayList<Node> all_possible_words = new ArrayList<>();//all the possbile words that have the same meaning

        for (Node x : nodelist) {// iterate through the nodelist
            if (x.ENG.contains(Word) || x.ARABIC.contains(Word))// check to see if the node is that word the user requested
                all_possible_words.add(x);
        }//end for
        return all_possible_words;// return all the possible combination

    }

    public void printList(){
        for (Node x : nodelist){
            System.out.println(x.ENG+": "+x.ARABIC+" :"+x.WORDTYPE);
        }

    }

}//end class

 class Node {

     public HashSet<String> ENG= new HashSet<>();//english word
     public HashSet<String>  ARABIC = new HashSet<>();//arabic word
     public Map<String,String> DETAIL = new HashMap<>();// the plural or detail forms

     private String IPA;// international phonetic alphabet for future release

     public String WORDTYPE;

     public ArrayList<String> EXAMPLE = new ArrayList<>(); // sentence example in arabic
     public ArrayList<String>  SEN_MEANING = new ArrayList<>();//sentence meaning

     public Node(String eng,String a, String type){
         //insert the info of the word into the dictionary
         try {
             String temp [] = eng.split(";");// if the meaning contains ; then remove then to help the search
             for(String x : temp){
                 ENG.add(x);
                 ARABIC. add(a);
                 WORDTYPE = type;
             }
         }catch (PatternSyntaxException e){// if no ; it will catch the issue and continue as normal
             ENG.add(eng);
             ARABIC.add(a);
             WORDTYPE = type;
         }//end try catch
     }//constructor

     public void setSentence(String x) {
         EXAMPLE.add(x);
     }

     public void setSenMeaning(String x) {
         SEN_MEANING.add(x);
     }


 }
