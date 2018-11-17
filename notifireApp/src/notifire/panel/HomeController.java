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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    @FXML
    private AnchorPane ap;
    @FXML
    private Pane pane;
    @FXML
    private DatePicker dp;
    @FXML
    private Button anc;
    @FXML
    private Button task;
    @FXML
    private Button makeUp;
    @FXML
    private Button temp;
    @FXML
    private Button join;
    @FXML
    private Button logout;
    @FXML
    private Button canc;

    @FXML
    private Text selected;
    @FXML
    private Text date;
    @FXML
    private Text name;
    @FXML SplitPane sp;
    @FXML
    private void makeUp() throws IOException {
        linkTo("Makeup");
    }

    @FXML
    private void joint() throws IOException {
        linkTo("joint");
    }

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
    private void cancel() throws IOException {
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

    @FXML
    private void selectDate() throws IOException {
        //selected.setText(ThisUser.date().toString());
        linkTo("Day");

    }

    @FXML
    private void addTask() throws IOException {
        linkTo("AddTask");
    }

    public HomeController() throws IOException, ClassNotFoundException { //update user data
        if(ThisUser.update()){
            System.out.println("load home");
            Client client = new Client(ThisUser.ip, 5000);
            client.toServer("home");//1
            client.toServer(ThisUser.getUser().getId());//2
            ThisUser.setUser((User) client.fromServer());//3
            client.disconnect();//4+5
            ThisUser.update(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fv = new FullCalendarView(YearMonth.now());
        ObservableList<Node> n = fv.getView().getChildren();
        date.setText(fv.getMonth());
  
        pane.getChildren().addAll(n);
        name.setText(ThisUser.getUser().getName());

        if (ThisUser.type().equals("Student")) {
            anc.setVisible(false);
            task.setVisible(false);
            makeUp.setVisible(false);
            canc.setVisible(false);
        }
    }

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);
    }
   public void setColor(Button b, String bg,String bd){
        b.setStyle("-fx-background-color: " + bg +"; -fx-border-color: "+ bd);

    }
}
