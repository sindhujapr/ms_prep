package http;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/*
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 */
public class CookieHandling {
    public static void testCookies() throws Exception {
    /*
     * Test with another URL that will return cookies
     */
    String url = "http://www.google.com.hk/finance";

    // First set the default cookie manager.
    CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

    // Gather all cookies on the first request.
    URLConnection connection = new URL(url).openConnection();
    connection.connect();
    List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

    // Then use the same cookies on all subsequent requests.
    connection = new URL(url).openConnection();
    for (String cookie : cookies) {
        connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
    }
    }
    
    public static void main(String[] args) throws Exception {
    testCookies();
    }
}