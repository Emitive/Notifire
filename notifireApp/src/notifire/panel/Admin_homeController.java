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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 59010401
 */
public class Admin_homeController implements Initializable {
    @FXML private AnchorPane ap;
    @FXML private Button logout;
    @FXML private Button acc;
    @FXML private Button cu;
    @FXML private Button cr;
     @FXML
    private void out() throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void acc() throws IOException{
         linkTo("admin_acc");
    }
    @FXML
    private void cu() throws IOException{
         linkTo("admin_cu");
    }
    @FXML
    private void cr() throws IOException{
         linkTo("admin_cr");
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
