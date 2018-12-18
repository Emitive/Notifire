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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notifire.Admin;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author 59010401
 */
public class Admin_accController implements Initializable {

    @FXML private AnchorPane ap;
    @FXML private Button can;
    @FXML private Button sub;
    @FXML private Button cu;
    @FXML private Button cr;
    @FXML private TextField id; 
    @FXML private TextField pass; 
    @FXML private TextField email; 
    @FXML private TextField name; 
    @FXML private ComboBox<String> role; 
    @FXML
    private void cancel() throws IOException{
         linkTo("admin_home");
    }
    /**
     * Initializes the controller class.
     */
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.getItems().addAll("Teacher","Student");
        role.getSelectionModel().selectFirst();
    } 
        @FXML
    private void OK() throws IOException, ClassNotFoundException {
        if(id.getText().isEmpty()||pass.getText().isEmpty()||email.getText().isEmpty()||name.getText().isEmpty()){
            return;
        }
        Admin a = (Admin) ThisUser.getUser();
        String r = role.getSelectionModel().getSelectedItem();
        int d;
        try {
            d = Integer.parseInt(id.getText());
        } catch (NumberFormatException e) {
            id.setText("");
            return;
        }
        a.addUser(r,d, pass.getText(), name.getText(), email.getText());

        linkTo("admin_home");
    }
    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }
    
}
