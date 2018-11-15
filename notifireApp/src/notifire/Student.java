package notifire;

import java.io.IOException;

public class Student extends User {

    public Student(int id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public void joinCourse(int userId, int courseId) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 5000);
        client.toServer("joinCourse");
        client.toServer(userId);
        client.toServer(courseId);
        client.disconnect();
    }
}
