
package notifire;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;


public class Curriculum implements Serializable {
    private int id;
    private HashMap<Integer,Course> courseList;
    private String name;


    public Curriculum(int id, String name) {
        this.id = id;
        this.name = name;

        courseList = new HashMap<>();
    }
    public void addCourse(Course c){
        courseList.put(c.getId(), c);
    }
    
    public void clearCourse(){
        courseList.clear();
    }
    
    
}
