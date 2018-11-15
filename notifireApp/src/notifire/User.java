package notifire;

import java.io.IOException;
import java.io.Serializable;
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

    public boolean checkPassword(String pass) {
        return pass.equals(this.password);
    }

    public boolean changePassword(String oldPassword, String newPassword) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("changePassword");
        client.toServer(oldPassword);
        client.toServer(newPassword);
        client.disconnect();
        return true;
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

    public boolean setEmail(String password, String email) {
        if (checkPassword(password)) {
            this.email = email;
            return true;
        }
        return false;
    }
}
