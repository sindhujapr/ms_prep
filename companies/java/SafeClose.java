package interview.java;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SafeClose {
    private static final String src = "C:/playground";
    private static final String des = "C:/hello";

    public static void main(String[] args) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        
        try {
            in = new FileInputStream(new File(src));
            out = new FileOutputStream(new File(des));
            byte[] buf = new byte[1024];
            int n;
            while((n = in.read(buf)) > 0)
                out.write(buf, 0, n);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
            
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }       
    }

    public static void CloseIgnoreException(Closeable c) {
        if(c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // TODO: handle exception
            }
        }
    }
}