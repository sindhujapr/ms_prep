package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/*
 * This program can be used to test the simple http server I implemented for Commvault interview
 * 
 * reference:
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 * 
 * url:
 * http://www.google.com.hk/finance?gl=cn&hl=zh-CN
 */
public class HttpPost {
    private static String url;
    private static String charset;
    private static String query;

    static {
    url = "http://www.google.com.hk/finance?gl=cn&hl=zh-CN";
    charset = "utf-8";
    try {
        query = String.format("gl=%s&hl=%s",
            URLEncoder.encode("cn", charset),
            URLEncoder.encode("zh-CN", charset));
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    }

    public static void testUrlConnection() throws Exception {
    URLConnection connection = new URL(url).openConnection();
    // Triggers POST.
    connection.setDoOutput(true);
    connection.setRequestProperty("Accept-Charset", charset);
    connection.setRequestProperty("Content-Type",
        "application/x-www-form-urlencoded;charset=" + charset);

    OutputStream output = null;
    try {
        output = connection.getOutputStream();
        output.write(query.getBytes(charset));
    } finally {
        try {
        if (output != null)
            output.close();
        } catch (IOException e) {
        System.out.println(e);
        }
    }

    InputStream response = null;
    try {
        response = connection.getInputStream();
        int ch;
        while ((ch = response.read()) != -1) {
        System.out.print((char) ch);
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
        if (response != null)
            response.close();
        } catch (IOException e2) {
        System.out.println(e2);
        }
    }
    }

    public static void testHttpUrlConnection() throws Exception {
    /*
     * It should be noted that a URLConnection instance does not establish
     * the actual network connection on creation. This will happen only when
     * calling URLConnection.connect().
     */
    URLConnection connection = new URL(url).openConnection();

    /*
     * The HttpURLConnection will by default buffer the entire request body
     * before actually sending it, regardless of whether you've set a fixed
     * content length yourself using
     * connection.setRequestProperty("Content-Length", contentLength);. This
     * may cause OutOfMemoryExceptions whenever you concurrently send large
     * POST requests (e.g. uploading files). To avoid this, you would like
     * to set the HttpURLConnection#setFixedLengthStreamingMode().
     * 
     * httpConnection.setFixedLengthStreamingMode(contentLength); But if the
     * content length is really not known beforehand, then you can make use
     * of chunked streaming mode by setting the
     * HttpURLConnection#setChunkedStreamingMode() accordingly. This will
     * set the HTTP Transfer-Encoding header to chunked which will force the
     * request body being sent in chunks. The below example will send the
     * body in chunks of 1KB.
     * 
     * httpConnection.setChunkedStreamingMode(1024);
     */
    HttpURLConnection httpConnection = (HttpURLConnection) connection;

    /*
     * You can also cast the obtained URLConnection to HttpURLConnection and
     * use its HttpURLConnection#setRequestMethod() instead. But if you're
     * trying to use the connection for output you still need to set
     * URLConnection#setDoOutput() to true.
     */
    httpConnection.setRequestMethod("POST");
    connection.setDoOutput(true);
    /*
     * It can happen that a request returns an unexpected response, while it
     * works fine with a real web browser. The server side is probably
     * blocking requests based on the User-Agent request header. The
     * URLConnection will by default set it to Java/1.6.0_19 where the last
     * part is obviously the JRE version. You can override this as follows:
     * connection.setRequestProperty("User-Agent",
     * "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.3) Gecko/20100401"
     * ); // Do as if you're using Firefox 3.6.3.
     */
    httpConnection.setRequestProperty("Accept-Charset", charset);
    httpConnection.setRequestProperty("Content-Type",
        "application/x-www-form-urlencoded;charset=" + charset);

    OutputStream output = null;
    try {
        output = httpConnection.getOutputStream();
        output.write(query.getBytes(charset));
    } finally {
        try {
        if (output != null)
            output.close();
        } catch (IOException e) {
        System.out.println(e);
        }
    }

    /*
     * You can fire the HTTP request explicitly with
     * URLConnection#connect(), but the the request will automatically be
     * fired on demand when you want to get any information about the HTTP
     * response, such as the response body using
     * URLConnection#getInputStream() and so on.
     */
    InputStream response = null;
    try {
        response = httpConnection.getInputStream();
        /*
         * 200 OK
         */
        int responseCode = httpConnection.getResponseCode();
        /*
         * If the HTTP response code is 4nn (Client Error) or 5nn (Server
         * Error), then you can to read HttpURLConnection#getErrorStream()
         * to see if the server has sent any useful error information.
         */
        if (responseCode != HttpURLConnection.HTTP_OK) {
        InputStream error = ((HttpURLConnection) connection)
            .getErrorStream();
        }
        String responseMsg = httpConnection.getResponseMessage();

        /*
         * dump http response header
         */
        for (Entry<String, List<String>> header : connection
            .getHeaderFields().entrySet()) {
        System.out.println(header.getKey() + "=" + header.getValue());
        }

        String contentType = connection.getHeaderField("Content-Type");
        String charset = null;
        for (String param : contentType.replace(" ", "").split(";")) {
        if (param.startsWith("charset=")) {
            charset = param.split("=", 2)[1];
            break;
        }
        }

        if (charset != null) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response,
                charset));
            // PrintStream ps = new PrintStream(System.out, true,
            // "utf-8");
            for (String line; (line = reader.readLine()) != null;) {
            /*
             * Once utf-8 is configured in page
             * preferences->workspace, some Chinese characters can
             * be displayed, however, some characters still cannot
             * be recognized.
             */
            System.out.println(line);
            }
        } finally {
            if (reader != null)
            try {
                reader.close();
            } catch (IOException logOrIgnore) {
            }
        }
        } else {
        // TODO
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
        if (response != null)
            response.close();
        } catch (IOException e2) {
        System.out.println(e2);
        }
    }
    }

    public static void main(String[] args) throws Exception {
    /*
     * If the HTTP response code is -1, then something went wrong with
     * connection and response handling. The HttpURLConnection
     * implementation is somewhat buggy with keeping connections alive. You
     * may want to turn if off by setting the http.keepAlive system property
     * to false. You can do this programmatically in the beginning of your
     * application by:
     * 
     * System.setProperty("http.keepAlive", "false");
     */
    Locale locale = new Locale("zh", "CN");
    System.out
        .println(locale.getDisplayLanguage(Locale.SIMPLIFIED_CHINESE));
    // testUrlConnection();
    testHttpUrlConnection();
    }
}