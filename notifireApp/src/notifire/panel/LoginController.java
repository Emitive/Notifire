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
        try{
            d = Integer.parseInt(id.getText());
        }catch(NumberFormatException e){
            id.setText("");
            return;
        }
        client = new Client("127.0.0.1", 5000);
        client.toServer("login");
        client.toServer(d);
        client.toServer(pass.getText());
        String res =  (String)client.fromServer();
        System.out.println(res);
        if (res.equals("correct")) {
            ThisUser.setUser((User) client.fromServer());
            
            String time = "Morning ";
            if(LocalTime.now().isAfter(LocalTime.NOON)){
                time = "Afternoon ";
            }if(LocalTime.now().isAfter(LocalTime.of(18, 0))){
                time = "Evening ";
            }
            System.out.println("Type: " + ThisUser.getUser().getClass());
            System.out.println("Good " + time + ThisUser.getUser().getName() +", how can I help you.");
            //client.disconnect();

            Stage stage = (Stage) id.getScene().getWindow();
            if( ThisUser.type().equals("Admin") ){Parent root = FXMLLoader.load(getClass().getResource("admin_home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
            
            else{ Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
           

            //create a new scene with root and set the stage
            
        }
        else if(res.equals("incorrect")){
            id.setText("");
            pass.setText("");
        }
        client.disconnect();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("init completed");
        // TODO
    }


}
