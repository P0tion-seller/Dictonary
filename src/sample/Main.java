package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.annotation.processing.Filer;
import javax.naming.spi.DirStateFactory;
import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;


public class Main extends Application{

    public static Data wordsData = new Data();

    String server = "com.mysql.cj.jdbc.Driver";
    public String url = "jdbc:mysql://127.0.0.1:3306/?user=root/Dictionary";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("app.fxml"));
        Parent root = loader.load();

        String line = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\raouf\\IdeaProjects\\Dictonary\\src\\sample\\arabicWords.csv"));

            Data node = null;
            int flag =0;
            while ((line = bf.readLine()) != null ){

                line = line.replace("\"","");
                line = line.toLowerCase();
                String x[] = line.split(",");

                if(x[3] != "")
                {
                    node = new Data(x[1],x[0],x[3]);
                    node.setPlural(x[0],x[5]);
                    System.out.println(x[5]);
                }
                else{
                    node.setSentence(x[0]);
                    node.setSenMeaning(x[1]);
                    wordsData.addWord(node);
                }
                    //System.out.println("\n");
            }


        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }



       // wordsData.printList();
        primaryStage.setTitle("Leviathan Dictionary");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args); }


}
