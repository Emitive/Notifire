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
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import notifire.Course;
import notifire.Teacher;
import notifire.ThisUser;
import notifire.User;

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
    private Text percent;
    
    @FXML
    private void OK() throws IOException, ClassNotFoundException {
        setPercent();
        ThisUser.update(true);
        String selCourse = sub.getSelectionModel().getSelectedItem();
        LocalDate date = dp.getValue();
        
        if(selCourse == null || date == null || time.getText().isEmpty()){
            return;
        }
        
        if (sub.getSelectionModel().getSelectedItem().contains("-") && dp.getValue() != null) {
            String[] part = selCourse.split("-");
            int courseId = Integer.parseInt(part[0]);
            String[] times = time.getText().split(":");
            try {
                int hour = Integer.parseInt(times[0]);
                int minute = Integer.parseInt(times[1]);

                if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
                    Teacher t = (Teacher) ThisUser.getUser();
                    t.makeUp(courseId, date.atTime(hour, minute));
                    linkTo("Home");
                    return;
                }
            } catch (Exception e) {
                System.out.println("format error");
            }
        }
        time.setText("");
        time.setPromptText("format error");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }
    @FXML
    private void setPercent() {
        
        int courseId = 0,attendStudent = 0, hour = 0, minute = 0;
        String sel = sub.getSelectionModel().getSelectedItem();
        LocalDate date = dp.getValue();
        //System.out.println("SetPercent!"+sel+" "+date+" "+time.getText());
        if(sel == null || date == null || time.getText().isEmpty()){
            //System.out.println("null found bitch");
            return;
        }
        if (sub.getSelectionModel().getSelectedItem().contains("-") && dp.getValue() != null) {
            String[] part = sub.getSelectionModel().getSelectedItem().split("-");
            courseId = Integer.parseInt(part[0]);
            String[] times = time.getText().split(":");
            try {
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
                if (hour > 0 && hour < 24 && minute >= 0 && minute < 60) {
                    Course selCourse = ThisUser.getUser().getCourse(courseId);
                    attendStudent = selCourse.getMember().size();
                    HashMap<Integer, User> map = selCourse.getMember();
                    for (HashMap.Entry<Integer, User> m : map.entrySet()) { //for every student
                        User member = m.getValue();
                        HashMap<Integer, Course> aMap = member.getCourse();
                        for (HashMap.Entry<Integer, Course> c : aMap.entrySet()) { //for every course of student
                            HashMap<LocalDate, LocalDateTime> lessonMap = c.getValue().getLesson();
                            if (lessonMap.containsKey(dp.getValue())) { //if course has the same day as selected date
                                LocalDateTime original = lessonMap.get(dp.getValue());
                                LocalDateTime make = dp.getValue().atTime(hour, minute);
                                if (original.isEqual(make) || (original.isAfter(make) && original.isBefore(make.plusHours(selCourse.getPeriod()))) || (make.isAfter(original) && make.isBefore(original.plusHours(c.getValue().getPeriod())))) {
                                    //System.out.println("student "+member.getName()+" course: " + c.getValue().getName()+" is overlap: "+original+" dur: "+ c.getValue().getPeriod() +" and "+make +" dur:" + selCourse.getPeriod());
                                    attendStudent--;//if overlap break from for loop course
                                    break;
                                }
                            }
                        }
                    }
                    int result = 100*attendStudent/selCourse.getMember().size();
                    percent.setText(Integer.toString(result)+"%");
                    percent.setFill(Color.GREENYELLOW);
                    if(result<75){
                        percent.setFill(Color.YELLOWGREEN);
                    }if(result<50){
                        percent.setFill(Color.GOLD);
                    }if(result<25){
                        percent.setFill(Color.RED);
                    }
                }else{
                    percent.setText("N/A");
                    percent.setFill(Color.PURPLE);
                }
            } catch (Exception e) {
                percent.setText("N/A");
                percent.setFill(Color.PURPLE);
                //System.out.println("time format error");
            }
        }
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
