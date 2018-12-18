/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import notifire.Client;
import notifire.ThisUser;
import notifire.User;

/**
 * FXML Controller class
 *
 * @author WIN 7
 */
public class LoginController implements Initializable {

    Client client;
    /**
     * Initializes the controller class.
     */
    @FXML
    TextField id;
    @FXML
    TextField pass;

    @FXML
    public void login() throws IOException, ClassNotFoundException {
        int d;
        try {
            d = Integer.parseInt(id.getText());
        } catch (NumberFormatException e) {
            id.setText("");
            return;
        }
        client = new Client(ThisUser.ip, 5000);
        client.toServer("login");
        client.toServer(d);
        client.toServer(pass.getText());
        String res = (String) client.fromServer();
        System.out.println(res);
        if (res.equals("correct")) {
            ThisUser.setUser((User) client.fromServer());

            client.disconnect();
            ThisUser.update(false);
            ThisUser.setId(d);
            
            Stage stage = (Stage) id.getScene().getWindow();
            stage.setResizable(false);
            String home = "Home.fxml";
            if (ThisUser.type().equals("Admin")) {
                home = "admin_home.fxml";
            }
            Parent root = FXMLLoader.load(getClass().getResource(home));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //create a new scene with root and set the stage
        } else if (res.equals("incorrect")) {
            id.setText("");
            pass.setText("");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("init completed");
        // TODO
    }

}
