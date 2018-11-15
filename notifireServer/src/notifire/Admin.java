package notifire;

import java.io.IOException;
import java.time.LocalDateTime;

public class Admin extends User {

    public Admin(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public void addUser(String type, int id, String pass, String name, String email) throws IOException {
  
    }

    public void addCourse(int id, String name, LocalDateTime startDate, int period, int total, int cId) throws IOException {

    }

    public void addCurriculum(int id, String name) throws IOException {

    }

    public void linkCourseCurriculum(int cId, int crId) throws IOException {

    }

}
