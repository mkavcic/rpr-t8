package ba.unsa.etf.rpr.tutorijal08;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;


public class Controller {
    public TextField unos;
    public ListView<String> list = new ListView<String>();
    ObservableList<String> items =FXCollections.observableArrayList ();


    public void trazi(ActionEvent actionEvent){

        walk("C:\\Users\\Mirna\\Desktop\\");

        list.setItems(items);
    }

    public void walk(String path){
        File root = new File(path);
        File[] list=root.listFiles();
        if(list==null) return;
        for(File f:list){
            if(f.isDirectory()){
                walk(f.getAbsolutePath());
            }
            else{
                String s=f.getAbsoluteFile().toString();
                if(s.contains(unos.getText())){
                   items.add(s);

                   System.out.println(s);
                }
            }
        }

    }
}
