package notifire;
// A Java program for a Client 

import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    // initialize socket and input output streams 
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    User user = null;

    // constructor to put ip address and port 
    public Client(String address, int port) {

        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            
            System.out.println("Server accepted");
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    public void toServer(Object o) throws IOException {
        out.writeObject(o);
    }

    public Object fromServer() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void disconnect() throws ClassNotFoundException {
        try {
            fromServer();
            System.out.println("server want to close");
            System.out.println(LocalDateTime.now());
            in.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}
