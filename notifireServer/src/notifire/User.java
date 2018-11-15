package notifire;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String password;
    private String name;
    private String email;
    private Curriculum curriculum;
    private HashMap<Integer, Course> course = new HashMap<>();

    public User(int id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean checkPassword(String pass) throws IOException {
        if (pass.equals(this.password)) {
            Client client = new Client("127.0.0.1", 7777);//test mail
            String head = "you've login @ " + LocalDate.now();
            String message = "hi " + this.name + "you've login at " + LocalDate.now() + "if it's not you, Good Luck.";
            client.toServer(new EmailRequest(this.email, head, message, LocalDate.now() ));
            client.disconnect();
            return true;
        }
        return false;
    }

    public boolean changePassword(String oldPassword, String newPassword) throws IOException {
        if (checkPassword(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HashMap getCourse() {
        return course;
    }

    public Course getCourse(int id) {
        return course.get(id);
    }

    public void addCourse(Course c) {
        this.course.put(c.getId(), c);
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setEmail(String password, String email) throws IOException {
        if (checkPassword(password)) {
            this.email = email;
            return true;
        }
        return false;
    }

    public void printAll() {
        System.out.println(this.id);
        System.out.println(this.password);
        System.out.println(this.name);
        System.out.println(this.course);
        System.out.println(this.email);
    }
}
