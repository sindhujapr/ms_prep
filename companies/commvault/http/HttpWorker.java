package interview.commvault.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class HttpWorker implements Runnable {
    private static final Logger LOG = Logger.getLogger(HttpWorker.class);
    private final int BUFSIZE = 1024;
    private final Socket conn;
    
    public HttpWorker(Socket connection) {
        this.conn = connection;
    }

    @Override
    public void run() {
        BufferedReader clientStreamReader = null;
        DataOutputStream outStreamToClient = null;

        try {
            clientStreamReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            outStreamToClient = new DataOutputStream(conn.getOutputStream());

            /* 
             * The first line includes the method (GET) and the requested resource.
             * Sample: GET /filedeletion.log HTTP/1.1 
             */
            String requestString = clientStreamReader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(requestString);
            /* Retrieve the HTTP method and requested resource from the first and second fields */
            String httpMethod = tokenizer.nextToken();
            String httpQueryString = tokenizer.nextToken();

            LOG.info("Client " + conn.getInetAddress() + ":" + conn.getPort() + " resource: " + httpQueryString);

            /* We only handle "GET" request here */
            if (httpMethod.equals("GET")) {
                if (httpQueryString.equals("/")) {
                    /* Request home page */
                    sendResponse(outStreamToClient, 200, "<b> Simple HTTP Server Home Page.... </b><BR>", false);
                } else {
                    /* Convert the requested resource to a local file on the HTTP server*/
                    String filePath;
                    if(HttpServer.httpRoot.endsWith("/"))
                        filePath = HttpServer.httpRoot + httpQueryString.substring(1);
                    else
                        filePath = HttpServer.httpRoot + httpQueryString;

                    File file = new File(filePath);
                    if (file.exists() && file.isFile())
                        sendResponse(outStreamToClient, 200, filePath, true);
                    else
                        sendResponse(outStreamToClient, 404, "<b>Requested resource not found ....</b>", false);
                }
            } else {
                sendResponse(outStreamToClient, 404, "<b>Unsupported operation: " + httpMethod + "</b>", false);
                LOG.info("Unsupported operation");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(clientStreamReader != null)
                    clientStreamReader.close();
            } catch (IOException e) {
                LOG.warn("Exception thrown when closing input stream reader " + e.getMessage());
            }
                
            try {
                if(outStreamToClient != null)
                    outStreamToClient.close();
            } catch (IOException e) {
                LOG.warn("Exception thrown when closing output stream " + e.getMessage());
            }
            
            try {
                if(conn != null)
                    conn.close();
            } catch (IOException e) {
                LOG.warn("Exception thrown when closing client connection " + e.getMessage());
            }
        }
    }

    /* Send requested file to the browser */
    private void sendFile(FileInputStream fin, DataOutputStream out) throws IOException {
        byte[] buffer = new byte[BUFSIZE];
        int bytesRead;

        /* To improve performance, we can use NIO instead*/
        while ((bytesRead = fin.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
    }

    /* Send status information, followed by file content or response string, to browser */
    private void sendResponse(DataOutputStream out, int statusCode,
            String responseString, boolean isFile) throws IOException {
        String statusLine = null;
        String serverdetails = "Server: Simple HTTP Server";
        String contentLengthLine = null;
        String contentTypeLine = "Content-Type: text/html" + "\r\n";

        if (statusCode == 200)
            statusLine = "HTTP/1.1 200 OK" + "\r\n";
        else if(statusCode == 404)
            statusLine = "HTTP/1.1 404 Not Found" + "\r\n";
        LOG.warn(statusLine);

        FileInputStream fin = null;
        try {
            if (isFile) {
                /* here responseString is the file name that requested by browser */
                fin = new FileInputStream(responseString);
                contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
            } else {
                responseString = HttpServer.PAGE_START + responseString + HttpServer.PAGE_END;
                contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
            }
    
            out.writeBytes(statusLine);
            out.writeBytes(serverdetails);
            out.writeBytes(contentTypeLine);
            out.writeBytes(contentLengthLine);
            // disable persistent http connection
            out.writeBytes("Connection: close\r\n");
            out.writeBytes("\r\n");
    
            /* Send content or the response after the status information */
            if (isFile)
                sendFile(fin, out);
            else
                out.writeBytes(responseString);
        } finally {
            if(fin != null)
                fin.close();
        }
    }
}