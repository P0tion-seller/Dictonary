package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class Controller {

    private String lElement = "";// last element in the history

    static Data w = Main.wordsData;

    private String needToTranslate;

    @FXML
    private ListView history_tab;
    @FXML
    private ListView list_display;


    @FXML
    private TextArea translate_display;
    @FXML
    private TextField wordBox;

    @FXML
    private TextArea details_display;



    @FXML
    void OnClickEvent(ActionEvent event) {
        Translate();
    }

    @FXML
    void KeyPressed(KeyEvent event) {
        int  key =  event.getCode().getCode();
        if(key == KeyCode.ENTER.getCode()){//if enter is pressed
            Translate();//translate the word
        }

    }

    private void Translate(){
        needToTranslate = wordBox.getText();// get texts and change it to lower case for easy finding
        needToTranslate = needToTranslate.toLowerCase();
        list_display.getItems().remove(0,list_display.getItems().size());

        try {
            ArrayList<Data> x = w.findWord(needToTranslate);
            //translate_display.setText();
            String text= "",text2="";
            for(int i =0; i < x.size(); i++){
               // System.out.println(x.get(i).getArabicWord());
           // translate_display.setText(x.getArabicWord()+" " +x.getEnglishWord()+"\n\n\n"+ x.getExampleASentence()+"\n"+x.getSentenceMeaning());
           // details_display.setText(x.getIPA());
                 translate_display.setText(x.get(i).getSentence()+"\n"+x.get(0).getSenMeaning()+"\n\n");
            text+=(x.get(i).getArabicWord() +":  "+ x.get(i).getWordType()+"\n");
            list_display.getItems().add(x.get(i).getEnglishWord() +": "+x.get(i).getArabicWord() +":  "+ x.get(i).getWordType());
            }
             //translate_display.setText(text);


            if(!(lElement.equals(needToTranslate))){//to insure it does not duplicate in the history tab
                lElement = needToTranslate;
                history_tab.getItems().add(x.get(0).getEnglishWord() + " : " + x.get(0).getArabicWord());
            }


        }
        catch (Exception e){// if word is no found return this
            translate_display.setText("Not Found");
            System.out.println("Error either word not found or list is empty");
        }


    }//end translate
}

