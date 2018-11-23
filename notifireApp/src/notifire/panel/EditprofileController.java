/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Thawin Boonchoen
 */
public class EditprofileController implements Initializable {

    
   @FXML
    private AnchorPane ap;
   
    @FXML
    private ComboBox<String> sub;
    @FXML
    private TextArea textid;
   
    @FXML
    private TextArea textname;
    @FXML
    private TextArea textemail;

    @FXML
    private void confirm() throws IOException, ClassNotFoundException {       
            linkTo("profile");        
    }
    @FXML
    private void cancel() throws IOException, ClassNotFoundException {       
            linkTo("profile");        
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
