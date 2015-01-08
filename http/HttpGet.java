package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/*
 * If all you want is parsing and extracting data from HTML,
 * then better use a HTML parser like Jsoup
 * What are the pros/cons of leading HTML parsers in Java
 * http://stackoverflow.com/questions/3152138/what-are-the-pros-and-cons-of-the-leading-java-html-parsers/3154281#3154281
 *
 * How to scan and extract a webpage in Java
 * http://stackoverflow.com/questions/2835505/how-to-scan-a-website-or-page-for-info-and-bring-it-into-my-program/2835555#2835555
 * 
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 * http://www.google.com.hk/finance?gl=cn&hl=zh-CN
 */
public class HttpGet {

    public static void main(String[] args) throws IOException {
    String url = "http://www.google.com.hk/finance";
    String charset = "UTF-8";
    String query = String.format("gl=%s&hl=%s",
        URLEncoder.encode("cn", charset),
        URLEncoder.encode("zh-CN", charset));

    URLConnection connection = new URL(url + "?" + query).openConnection();
    connection.setRequestProperty("Accept-Charset", charset);

    InputStream response = null;
    try {
        response = connection.getInputStream();
        // InputStream response2 = new URL(url).openStream();
        int ch;
        while ((ch = response.read()) != -1) {
        System.out.print((char) ch);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
        if (response != null)
            response.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    }
}