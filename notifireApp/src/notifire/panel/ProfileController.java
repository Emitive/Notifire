/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notifire.Course;
import notifire.Teacher;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author Thawin Boonchoen
 */
public class ProfileController implements Initializable {

   @FXML
    private AnchorPane ap;
   
    @FXML
    private ComboBox<String> sub;
    @FXML
    private Label labelid;
    @FXML
    private Label labelpass;
    @FXML
    private Label labelname;
    @FXML
    private Label labelemail;

    @FXML
    private void OK() throws IOException, ClassNotFoundException {       
            linkTo("Home");        
    }
    @FXML
    private void edit() throws IOException, ClassNotFoundException {       
            linkTo("editprofile");        
    }
    @FXML
    private void change() throws IOException, ClassNotFoundException {       
            linkTo("passchange");        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

   

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }
    
}
