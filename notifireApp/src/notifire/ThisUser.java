
package notifire;

import java.time.LocalDate;


public class ThisUser{
    static private boolean update;
    static private int id;
    static private User u = null;
    static private String type = null;
    static private LocalDate date = LocalDate.now();
    final static public String ip = "127.0.0.1";//"161.246.6.33";
    private ThisUser(){}
    static public void setUser(User user){
        u = user; 
        if(user.getClass().toString().equals("class notifire.Teacher")){
            type = "Teacher";
        }else if(user.getClass().toString().equals("class notifire.Student")){
            type ="Student";
        }else if(user.getClass().toString().equals("class notifire.Admin")){
            type = "Admin";
        }
    }
    
    static public User getUser(){
        return u;
    }
    static public int getId(){
        return id;
    }
    static public String type(){
        return type;
    }
    static public LocalDate date(){
        return date;
    }
    static public void setDate(LocalDate d){
        date = d;
    }
    static public void setId(int i){
        id = i;
    }static public boolean update(){
        return update;
    }static public void update(boolean b){
        update = b;
    }
}
