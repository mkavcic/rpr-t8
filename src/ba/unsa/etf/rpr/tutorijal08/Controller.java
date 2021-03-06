package ba.unsa.etf.rpr.tutorijal08;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;



public class Controller{
    public TextField unos;
    public ListView<String> list = new ListView<String>();
    public ProgressIndicator progressInd=new ProgressIndicator();
    public Button stopBtn= new Button();
    public Button searchBtn=new Button();
    private boolean stop=false;
    ObservableList<String> items =FXCollections.observableArrayList ();

   /* public Controller() {
        this.unos = unos;
        progressInd.setVisible(false);
    }*/


   /* Task<ObservableList<String>> task=new Task<ObservableList<String>>(){
        @Override protected ObservableList<String> call(){
            walk("C:\\Users", unos.getText());
            return items;
        }
    };*/


    public void trazi(ActionEvent actionEvent){
            stop=false;
        new Thread(()->{
            while(!stop) {
                progressInd.setVisible(true);
                stopBtn.setDisable(false);
                searchBtn.setDisable(true);
                if (!list.getItems().isEmpty()) {
                    Platform.runLater(() -> list.getItems().clear());

                }
                // walk("C:\\Users", unos.getText());
                walk(System.getProperty("user.home"), unos.getText());
                Platform.runLater(() -> list.setItems(items));
                progressInd.setVisible(false);
                searchBtn.setDisable(false);
                stopBtn.setDisable(true);
            }
        }).start();


    }

    public void walk(String path, String uzorak){
        File root = new File(path);
        File[] lista=root.listFiles();
        if(lista==null) return;
        for(File f:lista){
            if(f.isDirectory()){
                walk(f.getAbsolutePath(), uzorak);
            }
            else{
                String s=f.getAbsoluteFile().toString();
                if(s.contains(uzorak)){
                   Platform.runLater(() -> items.add(s));
                  //  System.out.println("File:" + s);
                }
            }
        }

    }

    public void stop(ActionEvent actionEvent) {
        stop=true;
    }

    public void send() {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
           fxmlLoader.load(getClass().getResource("send.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
           Stage stage= new Stage();
           stage.setScene(scene);
           stage.setTitle("Pošalji");
           stage.show();
        }
        catch(Exception e){

        }
    }
}
