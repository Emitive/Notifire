package notifire;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Teacher extends User {

    public Teacher(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public void announce(int courseId, String name, String message) throws IOException {
    }

    public void addTask(int courseId, String name, String message, LocalDate deadline) throws IOException {

    }

    public void cancel(int courseId, LocalDate date) throws IOException {
  
    }

    public void makeUp(int courseId, LocalDateTime date) throws IOException {

    }

    public void joinCourse(int userId, int courseId) throws IOException {

    }
}
