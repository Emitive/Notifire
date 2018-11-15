/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import notifire.Client;
import notifire.ThisUser;
import notifire.User;

/**
 * FXML Controller class
 *
 * @author WIN 7
 */
public class HomeController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    private FullCalendarView fv;
    @FXML private AnchorPane ap;
    @FXML private Pane pane;
    @FXML private DatePicker dp;
    @FXML private Button anc;
    @FXML private Button task;
    @FXML private Button makeUp;
    @FXML private Button temp;
    @FXML private Button join;
    
    @FXML private Text selected;
    @FXML private Text date;
    @FXML private Text name;
    @FXML private void task() {
        System.out.println("so many work jaa");
    }
    @FXML
    private void makeUp() throws IOException{
         linkTo("Makeup");
    }
    @FXML
    private void cancel()throws IOException {
        linkTo("Cancel");
    }
    @FXML
    private void previous() {
        fv.previousMonth();
        date.setText(fv.getMonth());
    }
    @FXML
    private void next() {
        fv.nextMonth();
        date.setText(fv.getMonth());
    }
    @FXML
    private void announce() throws IOException {
        linkTo("Announce");
        
    }
    @FXML private void selectDate(){
        selected.setText(ThisUser.date().toString());
        
    }
    
    @FXML
    private void addTask() throws IOException {
        linkTo("AddTask");
    }
    
    public HomeController() throws IOException, ClassNotFoundException{ //update user data
        Client client = new Client(ThisUser.ip,5000);
        client.toServer("home");
        client.toServer(ThisUser.getUser().getId());
        ThisUser.setUser((User)client.fromServer());
        client.disconnect();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fv = new FullCalendarView(YearMonth.now());
        ObservableList<Node> n = fv.getView().getChildren();
        date.setText(fv.getMonth());
        pane.getChildren().addAll(n);
        name.setText(ThisUser.getUser().getName());     
    }

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s +".fxml"));
        sc.setRoot(root);
    }
    

}
