package ba.unsa.etf.rpr.tutorijal08;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.File;


public class Controller {
    public TextField unos;

    public void trazi(ActionEvent actionEvent){
        walk("C:\\");
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
                if(s.contains(unos.getText())) System.out.println(s);
            }
        }
    }
}
