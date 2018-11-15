package notifire;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class NotifireServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Data data = Data.getData();
        data.addStudent(123, "qwerty", "qwerty-san", "gg@gg.gg");
        data.addTeacher(1, "onepunch", "saitama", "dead@gg.ez");
        data.addStudent(59010093, "karnnumart", "karnnumart INW ZA", "k.iamthammarak@hotmail.com");
        data.addStudent(999, "999", "I've got 99 Problems and the Bitch ain't one", "bitchAint1@gg.ez");
        Data d = Data.getData();
        data.addCurriculum(1, "CE");
        data.addCourse(55, "ooad", LocalDateTime.now(), 3, 10, 1);
        data.getUser(123).addCourse(data.getCourse(55));
        System.out.println(data.getUser(123).getName() + " - " + data.getCourse(55).getName());
        d.saveData();
        
        Server sv = new Server(5000);

        while (true) {
            sv.waitClient();
            String m;

            m = (String) sv.fromClient(); // #1 command
            boolean correct = false;

//-----------------------ALL------------------------------                
            if (m.equals("login")) { // login
                int id = (int) sv.fromClient(); // #2 id 
                //int id = Integer.valueOf(ids);
                System.out.println("id " + id);
                String pass = (String) sv.fromClient(); // #3 pass 
                System.out.println("pass " + pass);
                if (data.getUser(id) != null) {
                    if (data.getUser(id).checkPassword(pass)) { //correct password
                        sv.toClient("correct");
                        sv.toClient(data.getUser(id));
                    }
                }
            } else if (m.equals("changePassword")) {//Change Password
                int id = (int) sv.fromClient(); //#2 id
                String oldPass = (String) sv.fromClient();//#3 Old Password
                String newPass = (String) sv.fromClient();//#4 New Password
                data.getUser(id).changePassword(oldPass, newPass);
                sv.toClient("Change Password Complete");
            } else if (m.equals("home")) { // Back to Home
                int id = (int) sv.fromClient();// ยังไม่เสร็จ
                sv.toClient(data.getUser(id));

            } //----------------------- Teacher------------------------               
            else if (m.equals("cancel")) { // cancel
                int id = (int) sv.fromClient(); // #2 course id
                LocalDate date = (LocalDate) sv.fromClient(); //#3 date(Time) to cancel
                data.getCourse(id).cancel(date);

            } else if (m.equals("announce")) {// annouce
                int id = (int) sv.fromClient(); // #2  course ID
                String name = (String) sv.fromClient(); // #3 name
                String message = (String) sv.fromClient(); //#4 message
                data.getCourse(id).addAnnounce(name, message);
            } else if (m.equals("addTask")) {// Add Tasks
                int id = (int) sv.fromClient(); //#2 course ID
                String name = (String) sv.fromClient(); // #3 name
                String message = (String) sv.fromClient(); // #4 message
                LocalDate date = (LocalDate) sv.fromClient(); //#5 date(
                data.getCourse(id).addTask(name, message, date);
            } else if (m.equals("makeUp")) {// Make Up Class
                int id = (int) sv.fromClient(); //#2 course ID
                LocalDateTime date = (LocalDateTime) sv.fromClient(); //#3 date
                data.getCourse(id).makeUp(date);
            } //----------------------------Teacher/Student------------------------------------------               
            else if (m.equals("joinCourse")) {// Join a Course
                int userId = (int) sv.fromClient(); // #2 User ID
                int courseId = (int) sv.fromClient();//#3 Course ID
                data.getCourse(courseId).addMember(data.getUser(userId));
                data.getUser(userId).addCourse(data.getCourse(courseId));
            } //---------------------------Admin--------------------------------------------------             
            else if (m.equals("addCourse")) {// Add New Courses
                int id = (int) sv.fromClient();//#2 User courseID
                String name = (String) sv.fromClient();// #3 Course Name
                LocalDateTime startDate = (LocalDateTime) sv.fromClient();// #4 Time
                int period = (int) sv.fromClient();// #5 period
                int total = (int) sv.fromClient();//#6 Total class
                int cId = (int) sv.fromClient();// #7  Curriculum Id
                data.addCourse(id, name, startDate, period, total, cId);

            } else if (m.equals("addCurriculum")) {// Add New Curriculums
                int id = (int) sv.fromClient();//#2 User ID
                String name = (String) sv.fromClient();//#3 Currculums name
                data.addCurriculum(id, name);
            } else if (m.equals("addUser")) { //Add New Users
                String type = (String) sv.fromClient();//#2 User Type(Teacher/Student)
                int id = (int) sv.fromClient();//#3 User ID
                String pass = (String) sv.fromClient();//#4 User password
                String name = (String) sv.fromClient();//#5 User Name
                String email = (String) sv.fromClient();//#6 User Email
                if (type.equals("teacher")) {
                    data.addTeacher(id, pass, name, email);
                } else {
                    data.addStudent(id, pass, name, email);
                }
            } else if (m.equals("linkCourseCurriculum")) {//Link Course to Curriculum
                int courseId = (int) sv.fromClient();// #2 Course ID
                int curriculumId = (int) sv.fromClient();// #3 Curriculum ID
                data.getCurriculum(curriculumId).addCourse(data.getCourse(courseId));
            }

            if (!correct) {
                sv.toClient("incorrect");
            }
            sv.toClient("done");
            sv.fromClient();
        }

    }

}
