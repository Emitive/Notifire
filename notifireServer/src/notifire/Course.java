package notifire;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;

public class Course implements Serializable {

    private int id;
    private String name;

    private HashMap<Integer, User> member = new HashMap<>(); //member - key: user id
    private HashMap<LocalDate, Announce> announce = new HashMap<>(); //announcement - key:announce date //only 1 announce per day
    private HashMap<LocalDate, LocalDateTime> lesson = new HashMap<>(); //all lesson time
    private int period = 0;
    private int total = 0;

    public Course(int id, String name, LocalDateTime firstDate, int period, int total) {
        this.id = id;
        this.name = name;
        this.period = period; //how long lesson takes
        this.total = total;
        for (int i = 0; i < total; i++) {
            lesson.put(firstDate.toLocalDate(), firstDate); //put it in HashMap
            firstDate = firstDate.plusWeeks(1);
        }
    }

    public void addLessonDay(LocalDateTime firstDate) { //option for day add another set of day lesson
        for (int i = 0; i < total; i++) {
            lesson.put(firstDate.toLocalDate(), firstDate); //put it in HashMap
            firstDate = firstDate.plusWeeks(1);
        }
    }

    public void makeUp(LocalDateTime date) {//teacher
        lesson.put(date.toLocalDate(), date);
        //make mail request
    }

    public void cancel(LocalDate date) { //teacher
        lesson.remove(date);
        //make mail request
    }

    public void addAnnounce(String name, String s) { //teacher
//        if (announce.containsKey(LocalDate.now())) {
//            announce.get(LocalDate.now()).appendName(name);
//            announce.get(LocalDate.now()).appendMessage(s);
//        } else {
            Announce a = new Announce(name, s);
            announce.put(a.getAnnoucedDate(), a);
 //       }
    }

    public void addTask(String name, String s, LocalDate deadline) { //teacher
        if (announce.containsKey(deadline)) {
            announce.get(deadline).appendName(name);
            announce.get(deadline).appendMessage(s);
        } else {
            Task t = new Task(name, s, deadline);
            announce.put(t.getAnnoucedDate(), t);
        }
    }

    public void addMember(User u) {//admin
        member.put(u.getId(), u);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPeriod() {
        return period;
    }

    public HashMap getMember() { //don't forget to cast
        return member;
    }

    public HashMap getAnnounce() {
        return announce;
    }

    public HashMap getLesson() {
        return lesson;
    }

}
