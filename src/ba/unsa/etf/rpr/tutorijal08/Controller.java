package ba.unsa.etf.rpr.tutorijal08;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.application.Platform;

import java.io.File;



public class Controller{
    public TextField unos;
    public ListView<String> list = new ListView<String>();
    ObservableList<String> items =FXCollections.observableArrayList ();

   /* Task<ObservableList<String>> task=new Task<ObservableList<String>>(){
        @Override protected ObservableList<String> call(){
            walk("C:\\Users", unos.getText());
            return items;
        }
    };*/


    public void trazi(ActionEvent actionEvent){

        new Thread(()->{
            if(!list.getItems().isEmpty()){
                Platform.runLater(()-> list.getItems().clear());
            }
            walk("C:\\Users", unos.getText());
            Platform.runLater(()->list.setItems(items));
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

}
