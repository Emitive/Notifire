package notifire;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Teacher extends User {

    public Teacher(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public void announce(int courseId, String name, String message) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("announce");
        client.toServer(courseId);
        client.toServer(name);
        client.toServer(message);
        client.disconnect();
    }

    public void addTask(int courseId, String name, String message, LocalDate deadline) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("addTask");
        client.toServer(courseId);
        client.toServer(name);
        client.toServer(message);
        client.toServer(deadline);
        client.disconnect();
    }

    public void cancel(int courseId, LocalDate date) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("cancel");
        client.toServer(courseId);
        client.toServer(date);
        client.disconnect();
    }

    public void makeUp(int courseId, LocalDateTime date) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("makeUp");
        client.toServer(courseId);
        client.toServer(date);
        client.disconnect();
    }

    public void joinCourse(int userId, int courseId) throws IOException, ClassNotFoundException {
        Client client = new Client(ThisUser.ip, 5000);
        client.toServer("joinCourse");
        client.toServer(userId);
        client.toServer(courseId);
        client.disconnect();
    }
}
