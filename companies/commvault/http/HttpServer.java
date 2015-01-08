package interview.commvault.http;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/*
 * Simple HTTP server implementation to handle GET request
 * If the requested resource is available, HTTP 200 will be returned along with the resource.
 * if the requested resource is unavailable or the operation is other than GET,
 * HTTP 404 will be returned. Tested with IE8 and Chrome 25.0.*. But there is always an 
 * additional resource (favicon.ico) requested by Chrome.
 * To run this simple HTTP server:
 * 1) Update HttpServer.httpRoot if needed
 * 2) Update the log paths and levels in log4j.properties
 * 3) Update the port, backlog, etc. in the main function, if needed.
 * 4) Run the server and request for a valid file in browser such as http://localhost:port/validfile.txt
 */
public class HttpServer {
    /* This is the content root of the HTTP server. */
    static final String httpRoot = "C:/playground";
    static final String PAGE_START = "<html>" + "<title>Simple HTTP Server</title>" + "<body>";
    static final String PAGE_END = "</body>" + "</html>";

    /*
     * port: the port that the server listens on
     * backlog: the listen backlog
     * nThread: the size of the worker thread pool
     */
    private final int port;
    private final int backlog;
    private final int nThreads;
    
    private static final Logger LOG = Logger.getLogger(HttpServer.class);

    public HttpServer(int port, int backlog, int nThreads) {
        this.port = port;
        this.backlog = backlog;
        this.nThreads = nThreads;
    }

    public void start() {
        /* Create a thread pool of worker threads to handle HTTP GET requests */
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, backlog, InetAddress.getByName("127.0.0.1"));
            LOG.info("Http server ready on localhost:" + port);
    
            /* infinite loop unless otherwise stopped */
            while (true) {
                Socket connection = serverSocket.accept();
                LOG.info("Connection from " + connection.getInetAddress() + ":" + connection.getPort());

                /* 
                 * Handle the request in a separate thread.
                 * The main thread continues listening for new connection
                 */
                executor.execute(new HttpWorker(connection));
            }
        } catch (UnknownHostException e) {
            LOG.error("Unknow Host" + e.getMessage());
        } catch (IOException e) {
            LOG.error("Exception " + e.getMessage());
        } finally {
            try {
                if(serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                LOG.warn("Exception closing server socket " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) throws Exception {
        /* Specify your preferred port, backlog and size of thread pool here */
        HttpServer httpServer = new HttpServer(9999, 10, 10);
        httpServer.start();
    }
}