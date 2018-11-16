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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notifire.Teacher;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author 59010401
 */
public class JointController implements Initializable {
    @FXML private TextField  id;
    @FXML private Button enter;
    @FXML private Button cancel;
    @FXML private AnchorPane ap;
    
    @FXML
    private void cancel() throws IOException{
         linkTo("Home");
    }
    @FXML
    private void Enter() throws IOException, ClassNotFoundException{
        int courseId = Integer.parseInt(id.getText());
      //  System.out.println(courseId);
        Teacher t = (Teacher)ThisUser.getUser();
        t.joinCourse(t.getId(),courseId);
    //    System.out.println(t.getId());
         linkTo("Home");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);
        
    }
}
