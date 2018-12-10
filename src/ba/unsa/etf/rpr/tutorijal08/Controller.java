package ba.unsa.etf.rpr.tutorijal08;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


public class Controller {
    public TextField unos;

    public void trazi(ActionEvent actionEvent){
        String string=unos.getText();
        System.out.println(string);
    }
}
