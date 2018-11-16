/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifire.panel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import notifire.Announce;
import notifire.Course;
import notifire.ThisUser;

/**
 * FXML Controller class
 *
 * @author Thawin Boonchoen
 */
public class DayController implements Initializable {

    @FXML
    private Text date;
    @FXML
    private AnchorPane ap;
    @FXML
    private ComboBox<String> sub;
    @FXML
    private Text text;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void OK() throws IOException {
        linkTo("Home");

    }

    @FXML
    private void selectSub() {
        String name = "h";
        String message = "m";
        String[] part = sub.getSelectionModel().getSelectedItem().split("-");
        int courseId = Integer.parseInt(part[0]);
        Course selectedCourse = (Course) ThisUser.getUser().getCourse().get(courseId);
        Announce a = (Announce) selectedCourse.getAnnounce().get(ThisUser.date());
        if (a != null) {
            name = a.getName();
            message = a.getMessage();
            text.setText(name + "\n\n" + message);
        }
    }

    private void loadData() {

        HashMap<Integer, Course> map = ThisUser.getUser().getCourse();
        map.forEach((k, v) -> {
            sub.getItems().add(v.getId() + "-" + v.getName());
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setText(ThisUser.date().toString());
        loadData();
    }

    private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        sc.setRoot(root);

    }

}
