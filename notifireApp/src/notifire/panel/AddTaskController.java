/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import notifire.Course;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author WIN 7
 */
public class AddTaskController implements Initializable {
ObservableList list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ok;
    @FXML private ChoiceBox <String> sub;

    @FXML
    void OK() throws IOException {
        
        Stage stage = (Stage) ok.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
     
    }
    
    @FXML
    private DatePicker dp;

    @FXML
    void getDate() {
        Calendar c = GregorianCalendar.from(dp.getValue().atStartOfDay(ZoneId.systemDefault()));
        System.out.println(c.getTime());
    }
    private void loadData(){
        list.removeAll(list);
        HashMap<Integer,Course> map = ThisUser.getUser().getCourse();
        
        map.forEach((k,v)->list.addAll(v.getName()));
        sub.getItems().addAll(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadData();
    }

}
