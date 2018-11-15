package notifire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable { // We create/access everything here. save this as a whole single object.

    private static Data data = null;
    private HashMap<Integer, User> user = new HashMap<>();
    private HashMap<Integer, Course> course = new HashMap<>();
    private HashMap<Integer, Curriculum> cr = new HashMap<>();
    private static final String fileName = "data";

    private Data() {
    }

    public static Data getData() throws IOException, FileNotFoundException, ClassNotFoundException { //only load for the first time call (Singleton)
        if (data == null) {
            IO io = new IO();
            if (!io.exist(fileName)) {
                io.save(fileName, new Data());
            }
            data = (Data) io.load(fileName);
        }
        return data;
    }

    public void saveData() throws IOException {
        IO io = new IO();
        io.save(fileName, data);
    }

    public boolean addStudent(int id, String pass, String name, String email) {
        if (user.containsKey(id)) { //id exist?
            System.out.println("id exist" + id);
            return false;//yes
        }
        Student s = new Student(id, pass, name, email); //create instance
        user.put(id, s); //put in Map
        System.out.println("student added" + id + name);
        return true;
    }

    public boolean addTeacher(int id, String pass, String name, String email) {
        if (user.containsKey(id)) {
            System.out.println("teacher exist" + id);
            return false;
        }
        Teacher t = new Teacher(id, pass, name, email);
        user.put(id, t);
        System.out.println("teacher added" + id + name);
        return true;
    }

    public boolean addCurriculum(int id, String name) {
        if (cr.containsKey(id)) {
            return false;
        }
        Curriculum c = new Curriculum(id, name);
        cr.put(id, c);
        return true;
    }

    public boolean addCourse(int id, String name, LocalDateTime startDate, int period, int total, int cId) {
        if (course.containsKey(id)) {
            return false;
        }
        Course c = new Course(id, name, startDate, period, total);
        cr.get(cId).addCourse(c);
        course.put(id, c);
        return true;
    }

    public User getUser(int id) {
        return user.get(id);
    }

    public Course getCourse(int id) {
        return course.get(id);
    }

    public Curriculum getCurriculum(int id) {
        return cr.get(id);
    }

    public void sendMail(int courseId, String head, String message, LocalDate date) throws IOException, ClassNotFoundException {
        System.out.println("Mail commented header is : " + head);
//        Client client = new Client("127.0.0.1", 5001);
//        ArrayList<EmailRequest> aList = new ArrayList<>();
//        HashMap<Integer, User> map = data.getCourse(courseId).getMember();
//        map.forEach((k, v) -> aList.add(new EmailRequest(v.getEmail(), head, message, date)));
//        client.toServer(aList);
//        client.disconnect();
    }

}
