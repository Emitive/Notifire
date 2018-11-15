
package notifire;

import java.io.Serializable;
import java.time.LocalDate;


public class EmailRequest  implements Serializable {
    private String address;
    private String head;
    private String message;
    private LocalDate dateSend;
    
    public EmailRequest(String address, String head,String message, LocalDate dateSend) {
        this.address = address;
        this.dateSend = dateSend;
        this.message = message;
        this.head = head;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateSend() {
        return dateSend;
    }

    public String getHead() {
        return head;
    }

    public String getMessage() {
        return message;
    }

    
    
}
