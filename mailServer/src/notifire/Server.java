package notifire;

// A Java program for a Server 
import java.net.*;
import java.io.*;

public class Server {

    //initialize socket and input stream 
    private Socket socket = null;
    private ServerSocket server = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    // constructor with port 
    public Server(int port) throws IOException {
            server = new ServerSocket(port);
            System.out.println("Server started");
    }

    public void waitClient() throws IOException, ClassNotFoundException {
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new ObjectOutputStream(socket.getOutputStream());
            //out.writeObject("Hi client");
            System.out.println("Client accepted");
    }

    public void toClient(Object o) throws IOException {
        out.writeObject(o);
    }

    public Object fromClient() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void disconnect() throws IOException {
        socket.close();
        in.close();
        System.out.println("Closing connection");
    }

}
