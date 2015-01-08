package interview.commvault.synchronization;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkService1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(10000, 10)) {
            System.out.println("server listening on port 10000");
            int i = 0;
            while(i++ < 10000) {
                Socket socket = serverSocket.accept();
                executor.execute(new Worker(socket, i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Worker implements Runnable {
    private final Socket socket;
    private final int id;
    public Worker(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println(socket);
        try {
            OutputStream out = socket.getOutputStream();
            out.write(id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}