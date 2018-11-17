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
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notifire.Course;
import notifire.Teacher;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author Thawin Boonchoen
 */
public class MakeupController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane ap;
    @FXML
    private TextArea text;
    @FXML
    private ComboBox<String> sub;
    @FXML
    private DatePicker dp;
    @FXML
    private TextField time;

    @FXML
    private void OK() throws IOException, ClassNotFoundException {

        ThisUser.update(true);
        //int courseiD = sub.get        
        if (sub.getSelectionModel().getSelectedItem().contains("-") && dp.getValue()!=null) {
            String[] part = sub.getSelectionModel().getSelectedItem().split("-");
            int courseId = Integer.parseInt(part[0]);
            System.out.println(courseId);
            String[] times = time.getText().split(":");
            try{
                int hour = Integer.parseInt(times[0]);
                int minute = Integer.parseInt(times[1]);
                if (hour > 0 && hour < 24 && minute > 0 && minute < 60) {
                Teacher t = (Teacher) ThisUser.getUser();
                t.makeUp(courseId, dp.getValue().atTime(hour, minute));
                linkTo("Home");
                return;
            }
            }catch(NumberFormatException e){
                System.out.println("time format error");
            }
        }
        time.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }

    private void loadData() {
        list.removeAll(list);
        HashMap<Integer, Course> map = ThisUser.getUser().getCourse();

        map.forEach((k, v) -> list.addAll(v.getId() + "-" + v.getName()));
        sub.getItems().addAll(list);
    }

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }

}
