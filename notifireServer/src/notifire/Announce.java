
package notifire;

import java.io.Serializable;
import java.time.LocalDate;


public class Announce implements Serializable{
    private LocalDate annoucedDate;
    private String name;
    private String message;

    public Announce(String name, String message) {
        this.name = name;
        this.annoucedDate = LocalDate.now();
        this.message = message;
    }

    public LocalDate getAnnoucedDate() {
        return annoucedDate;
    }
    
    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
    public void appendName(String s){
        this.name += "," + s;
    }
    public void appendMessage(String s){
        this.name += "\n" + s;
    }
}
