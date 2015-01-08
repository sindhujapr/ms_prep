package interview.commvault.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Worker implements Runnable {
    private Socket connection;
    public Worker(Socket connection) {
        this.connection = connection;
    }
    
    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        String message;
        // 3. get Input and Output streams
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            out.writeObject("connection successfully");
            out.flush();
            // 4. The two parts communicate via the input and output streams
            message = (String) in.readObject();
            System.out.println("client>" + message);
            if (message.equals("bye")) {
                out.writeObject("connection successfully");
                out.flush();
            }
        } catch (ClassNotFoundException classnot) {
            System.err.println("Data received in unknown format");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SocketServer {
    private Socket connection = null;
    /* Size of the thread pool */
    private final int nThreads;

    public SocketServer(int nThreads) {
        this.nThreads = nThreads;
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
            /* Create a server socket */
            serverSocket = new ServerSocket(9999, 10);
            
            while(true) {
                /* Wait for connection */
                connection = serverSocket.accept();
                Future<?> result = executor.submit(new Worker(connection));
                
                System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(serverSocket != null) {
                    serverSocket.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        SocketServer server = new SocketServer(10);
        server.start();
    }
}