package ba.unsa.etf.rpr.tutorijal08;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.concurrent.ExecutorService;


public class Controller{
    public TextField unos;
    public ListView<String> list = new ListView<String>();
    ObservableList<String> items =FXCollections.observableArrayList ();

    Task<ObservableList<String>> task=new Task<ObservableList<String>>(){
        @Override protected ObservableList<String> call(){
            walk("C:\\Users\\Mirna\\");
            return items;
        }
    };


    public void trazi(ActionEvent actionEvent){

       // ExecutorService.submit(task);

       // walk("C:\\Users\\Mirna\\Desktop\\");
        Thread th = new Thread(task);

        th.setDaemon(true);

        th.start();

        list.setItems(items);
    }

    public void walk(String path){
        File root = new File(path);
        File[] lista=root.listFiles();
        if(list==null) return;
        for(File f:lista){
            if(f.isDirectory()){
                walk(f.getAbsolutePath());
            }
            else{
                String s=f.getAbsoluteFile().toString();
                if(s.contains(unos.getText())){
                   items.add(s);
                    list.setItems(items);
                  // System.out.println(s);
                }
            }
        }

    }

}
