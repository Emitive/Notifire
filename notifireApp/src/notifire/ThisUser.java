
package notifire;


public class ThisUser{
    static private User u = null;
    static private String type = null;
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
    public String type(){
        return type;
    }
}
