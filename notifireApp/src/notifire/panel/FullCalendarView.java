package notifire.panel;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import notifire.ThisUser;
import notifire.*;
import notifire.Course;

public class FullCalendarView {

    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    private HashMap<LocalDate, Integer> position = new HashMap<>();

    /**
     * Create a calendar view
     *
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth) {
        currentYearMonth = yearMonth;
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setMinSize(100*7, 82*6);
        calendar.setMaxSize(100*7, 82*6);

        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                calendar.add(ap, j, i);
                allCalendarDays.add(ap);

            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
            new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
            new Text("Saturday")};
        
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(725);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(200, 0);
            ap.setLeftAnchor(txt, 0.0);
            ap.setTopAnchor(txt, 0.0);
            txt.setFill(Color.CORNSILK);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month


        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(dayLabels, calendar);

    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     *
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers

        for (AnchorPaneNode ap : allCalendarDays) {

            if (ap.getChildren().size() != 0) {
                ap.getChildren().clear();
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            if(calendarDate.getMonthValue() == yearMonth.getMonthValue()){
                txt.setFill(Color.WHITESMOKE);
            }else{
                txt.setFill(Paint.valueOf("#4c4c4c"));
            }
                
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 1.0);
            ap.setLeftAnchor(txt, 1.0);
            ap.getChildren().add(txt);

            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
       
        //----- loop 
        HashMap<Integer, Course> map = ThisUser.getUser().getCourse();
        for (HashMap.Entry<Integer, Course> course : map.entrySet()) {
            Course c = course.getValue();
            HashMap<LocalDate, Announce> aMap = c.getAnnounce();
            for (HashMap.Entry<LocalDate, Announce> announce : aMap.entrySet()) {
                setDayText(announce.getValue().getAnnoucedDate(), announce.getValue().getName());
            }

        }
        //loop for every course
        

    }

    public void setDayText(LocalDate d, String s) {
        // Get the date we want to start with on the calendar
        if (d.getMonthValue() == currentYearMonth.getMonthValue()) {
            LocalDate firstDay = d.of(d.getYear(), d.getMonth(), 1);
            int offset = firstDay.getDayOfWeek().getValue(); //monday = 1; sunday = 7
  
            AnchorPane ap = allCalendarDays.get(d.getDayOfMonth() + offset - 1);
            String an ="";
            if(ap.getChildren().size()>1){//concat
                Text tx = (Text)ap.getChildren().get(1);
                an = tx.getText() + "," + s;
            }else{//new
                an = s;
            }
            if(an.length()>11){
                an = an.substring(0,8) + " . . . ";
            }
            Text txt = new Text(an);
            if(d.getMonthValue() == currentYearMonth.getMonthValue()){
                txt.setFill(Color.WHITESMOKE);
            }else{
                txt.setFill(Paint.valueOf("#555555"));
            }
            ap.setBottomAnchor(txt, 1.0);
            ap.setRightAnchor(txt, 1.0);
            ap.getChildren().add(txt);
            
        }else{
            //System.out.println("outer date: - " + d +" - "+ s);
        }

    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct
     * dates.
     */
    public void previousMonth() {

        currentYearMonth = currentYearMonth.minusMonths(1);
        ThisUser.ym = currentYearMonth;
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct
     * dates.
     */
    public String getMonth() {
        return currentYearMonth.getMonth().toString() +" "+ currentYearMonth.getYear();
    }

    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        ThisUser.ym = currentYearMonth;
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

}
