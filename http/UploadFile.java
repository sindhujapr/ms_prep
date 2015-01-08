package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/*
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 * 
 * You'd normally use multipart/form-data encoding for mixed POST
 * content (binary and character data). The encoding is in more detail
 * described in RFC2388: http://www.faqs.org/rfcs/rfc2388.html
 * 
 * The Apache HttpComponents HttpClient is much more convenient:
 * http://hc.apache.org/httpcomponents-client-ga/
 */
public class UploadFile {
    /*
     * If the other side is a HttpServlet, then its doPost() method will be
     * called and the parts will be available by HttpServletRequest#getPart()
     * (note, thus not getParameter() and so on!). The getPart() method is
     * however relatively new, it's introduced in Servlet 3.0 (Glassfish 3,
     * Tomcat 7, etc). Prior to Servlet 3.0, your best choice is using Apache
     * Commons FileUpload to parse a multipart/form-data request. Also see this
     * answer for examples of both the FileUpload and the Servelt 3.0
     * approaches.
     */
    public static void uploadFile() throws Exception {
    String url = "http://www.google.com.hk/finance";
    String charset = "utf-8";

    String param = "value";
    File textFile = new File("/path/to/file.txt");
    File binaryFile = new File("/path/to/file.bin");
    // Just generate some unique random value.
    String boundary = Long.toHexString(System.currentTimeMillis());
    // Line separator required by multipart/form-data.
    String CRLF = "\r\n";

    URLConnection connection = new URL(url).openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type",
        "multipart/form-data; boundary=" + boundary);
    PrintWriter writer = null;
    try {
        OutputStream output = connection.getOutputStream();
        // true = autoFlush, important!
        writer = new PrintWriter(new OutputStreamWriter(output, charset),
            true);

        // Send normal param.
        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"param\"")
            .append(CRLF);
        writer.append("Content-Type: text/plain; charset=" + charset)
            .append(CRLF);
        writer.append(CRLF);
        writer.append(param).append(CRLF).flush();

        // Send text file.
        writer.append("--" + boundary).append(CRLF);
        writer.append(
            "Content-Disposition: form-data; name=\"textFile\"; filename=\""
                + textFile.getName() + "\"").append(CRLF);
        writer.append("Content-Type: text/plain; charset=" + charset)
            .append(CRLF);
        writer.append(CRLF).flush();
        BufferedReader reader = null;
        try {
        reader = new BufferedReader(new InputStreamReader(
            new FileInputStream(textFile), charset));
        for (String line; (line = reader.readLine()) != null;) {
            writer.append(line).append(CRLF);
        }
        } finally {
        if (reader != null)
            try {
            reader.close();
            } catch (IOException logOrIgnore) {
            }
        }
        writer.flush();

        // Send binary file.
        writer.append("--" + boundary).append(CRLF);
        writer.append(
            "Content-Disposition: form-data; name=\"binaryFile\"; filename=\""
                + binaryFile.getName() + "\"").append(CRLF);
        writer.append(
            "Content-Type: "
                + URLConnection.guessContentTypeFromName(binaryFile
                    .getName())).append(CRLF);
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        InputStream input = null;
        try {
        input = new FileInputStream(binaryFile);
        byte[] buffer = new byte[1024];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            output.write(buffer, 0, length);
        }
        // Important! Output cannot be closed. Close of writer will
        // close output as well.
        output.flush();
        } finally {
        try {
            if (input != null)
            input.close();
        } catch (IOException logOrIgnore) {
            System.err.println(logOrIgnore);
        }
        }
        // CRLF is important! It indicates end of binary boundary.
        writer.append(CRLF).flush();

        // End of multipart/form-data.
        writer.append("--" + boundary + "--").append(CRLF);
    } finally {
        if (writer != null)
        writer.close();
    }
    }

    public static void main(String[] args) throws Exception {
    uploadFile();
    }
}