/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.rmi.activation.ActivationSystem;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import notifire.ThisUser;
import notifire.Announce;
import notifire.*;

/**
 * FXML Controller class
 *
 * @author WIN 7
 */
public class AnnounceController implements Initializable {

    @FXML private AnchorPane ap;
    @FXML private TextField head;
    @FXML private TextArea text;
    @FXML private ComboBox <String> sub;
    
    @FXML private void OK() throws IOException, ClassNotFoundException{
        String[] part = sub.getSelectionModel().getSelectedItem().split("-");
        int courseId = Integer.parseInt(part[0]);

        Teacher t = (Teacher)ThisUser.getUser();
        t.announce(courseId, head.getText(), text.getText());
        
        linkTo("Home");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }
    private void loadData(){
        
        HashMap<Integer,Course> map = ThisUser.getUser().getCourse();
        map.forEach((k,v)-> sub.getItems().add(v.getId() +"-"+ v.getName()) );

    }
    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);
        
    }

}
