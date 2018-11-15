package notifire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMain {

    /**
     * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
     * authentication) Use Authentication: Yes Port for TLS/STARTTLS: 587
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        final String fromEmail = "AttendarNotification@gmail.com"; //requires valid gmail id
        final String password = "NotiWT4K"; // correct password for gmail id
        final String fileName = "emailRequest";
        String toEmail = "k.iamthammarak@hotmail.com";
        String head = "";
        String message = "";
        IO io = new IO();
        HashMap<LocalDate, ArrayList<EmailRequest>> request;
        if (io.exist(fileName)) {
            request = (HashMap<LocalDate, ArrayList<EmailRequest>>) io.load(fileName);
        } else {
            request = new HashMap<>(); //must be loaded
            io.save(fileName, request);
        }

        
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        
        //---------- repeater --------------
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable a = new Runnable() {
            @Override
            public void run() {
                LocalDate today = LocalDate.now();
                if (request.containsKey(today)) {
                    System.out.println(today);
                    for (EmailRequest e : request.get(today)) {
                        System.out.println("Mail send " + e.getDateSend());
                        String toEmail = e.getAddress();
                        String head = e.getHead();
                        String message = e.getMessage();
                        Util.sendEmail(session, toEmail, head, message);

                    }
                    request.remove(today);
                    try {
                        io.save(fileName, request);
                    } catch (IOException ex) {
                        Logger.getLogger(MailMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
//Change here for the hour you want ----------------------------------.at()       
        scheduler.scheduleAtFixedRate(a, 0, 4, TimeUnit.SECONDS);

        Server server = new Server(5001);
        while (true) {
            server.waitClient(); 
            ArrayList<EmailRequest> el = (ArrayList<EmailRequest>) server.fromClient();
            server.toClient("done");
            String over = (String) server.fromClient();
            System.out.println(over);
            
            for (EmailRequest e: el){
                System.out.println("DATE:" + e.getDateSend());
                if (e.getDateSend().isEqual(LocalDate.now())) {
                    
                    Util.sendEmail(session, e.getAddress(), e.getHead(), e.getMessage());
                } else {
                    if (request.containsKey(e.getDateSend())) {
                        request.get(e.getDateSend()).add(e);
                    } else {
                        ArrayList<EmailRequest> newList = new ArrayList<>();
                        newList.add(e);
                        request.put(e.getDateSend(), newList);
                    }
                } 
            }
            
            
        }
    }

}
