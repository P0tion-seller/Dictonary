package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Controller {

    private String lElement = "";// last element in the history

    //static Data w = Main.wordsData;
     static Data w = Main.wordData;

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

    private void Translate() {
        needToTranslate = wordBox.getText();// get texts and change it to lower case for easy finding
        needToTranslate = needToTranslate.toLowerCase();
        list_display.getItems().remove(0,list_display.getItems().size());

        try{
            ArrayList<Node> e = w.findWord(needToTranslate);//trans;ate word
            String sentence = "";
            for (Node x:e) {
                sentence +=x.EXAMPLE+"\n\n"+x.SEN_MEANING+"\n\n";
                translate_display.setText(sentence);
                String output = x.ENG + " : " + x.ARABIC+ "Type: "+x.WORDTYPE;
                System.out.println(output);
                list_display.getItems().add(output);
                if(!(lElement.equals(needToTranslate))){//to insure it does not duplicate in the history tab
                    lElement = needToTranslate;
                    history_tab.getItems().add(x.ENG.toString() +": "+ x.ARABIC.toString());
                }
            }


        }catch (wordNotFound e){
            details_display.setText("MIS FIMIT BRO");
        }

    }//end translate
}

