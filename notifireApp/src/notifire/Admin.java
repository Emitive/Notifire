package notifire;

import java.io.IOException;
import java.time.LocalDateTime;

public class Admin extends User {

    public Admin(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public void addUser(String type, int id, String pass, String name, String email) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 5000);
        client.toServer("addUser");
        client.toServer(type);
        client.toServer(id);
        client.toServer(pass);
        client.toServer(name);
        client.toServer(email);
        client.disconnect();
    }

    public void addCourse(int id, String name, LocalDateTime startDate, int period, int total, int cId) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 5000);
        client.toServer("addCourse");
        client.toServer(id);
        client.toServer(name);
        client.toServer(startDate);
        client.toServer(period);
        client.toServer(total);
        client.toServer(cId);
        client.disconnect();
    }

    public void addCurriculum(int id, String name) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 5000);
        client.toServer("addCurriculum");
        client.toServer(id);
        client.toServer(name);
        client.disconnect();
    }

    public void linkCourseCurriculum(int cId, int crId) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 5000);
        client.toServer("linkCourseCurriculum");
        client.toServer(cId);
        client.toServer(crId);
        client.disconnect();
    }

}
