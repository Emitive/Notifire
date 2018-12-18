/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notifire.Admin;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author 59010401
 */
public class Admin_courseController implements Initializable {
        @FXML private AnchorPane ap;
    @FXML private Button can;
    @FXML private Button sub;

    @FXML private TextField id; 
    @FXML private TextField name; 
    @FXML private DatePicker dp;
    @FXML private TextField time;
    @FXML private TextField period;
    @FXML private TextField amount;
    @FXML private TextField cid;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void cancel() throws IOException{
         linkTo("admin_home");
    }
    @FXML
    private void OK() throws IOException, ClassNotFoundException {
        if(id.getText().isEmpty()||time.getText().isEmpty()||period.getText().isEmpty()||amount.getText().isEmpty()||cid.getText().isEmpty()||name.getText().isEmpty()){
            return;
        }
        Admin a = (Admin) ThisUser.getUser();
        //String r = role.getSelectionModel().getSelectedItem();
        int d,cd,pr,am;
        try {
            d = Integer.parseInt(id.getText());
        } catch (NumberFormatException e) {
            id.setText("");
            return;
        }
        try {
            cd = Integer.parseInt(cid.getText());
        } catch (NumberFormatException e) {
            cid.setText("");
            return;
        }
        try {
            pr = Integer.parseInt(period.getText());
        } catch (NumberFormatException e) {
            period.setText("");
            return;
        }
        try {
            am = Integer.parseInt(amount.getText());
        } catch (NumberFormatException e) {
            amount.setText("");
            return;
        }
         String[] times = time.getText().split(":");
         int hour,minute;
         try {
            hour = Integer.parseInt(times[0]);
            minute = Integer.parseInt(times[1]);
        } catch (Exception e) {
            time.setText("hh:mm");
            System.out.println("format error");
            return;
        }
        LocalDateTime date = dp.getValue().atTime(hour, minute);
        a.addCourse(d, name.getText(), date, pr, am, cd);

        linkTo("admin_home");
    }
    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }
}
