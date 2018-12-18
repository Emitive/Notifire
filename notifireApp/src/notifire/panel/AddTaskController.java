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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import notifire.Course;
import notifire.Teacher;
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
    @FXML
    private ComboBox<String> sub;
    @FXML
    private DatePicker dp;
    @FXML
    private TextField time;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextArea text;

    @FXML
    void OK() throws IOException, ClassNotFoundException {
        
        if (sub.getSelectionModel().getSelectedItem().contains("-") && dp.getValue()!=null) {
            ThisUser.update(true);
            String[] part = sub.getSelectionModel().getSelectedItem().split("-");
            int courseId = Integer.parseInt(part[0]);
            Teacher t = (Teacher) ThisUser.getUser();
            t.addTask(courseId, time.getText(), text.getText(), dp.getValue());
            System.out.println("deadline: " + dp.getValue().toString());
            linkTo("Home");
        }
    }

    @FXML
    void getDate() {
        Calendar c = GregorianCalendar.from(dp.getValue().atStartOfDay(ZoneId.systemDefault()));
        System.out.println(c.getTime());
    }
    
    @FXML
    private void cancel() throws IOException, ClassNotFoundException {       
            linkTo("Home");        
    }
    
    private void loadData() {
        list.removeAll(list);
        HashMap<Integer, Course> map = ThisUser.getUser().getCourse();

        map.forEach((k, v) -> list.addAll(v.getId() + "-" + v.getName()));
        sub.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }

}
