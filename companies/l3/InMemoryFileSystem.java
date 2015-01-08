package interview.l3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static interview.l3.Command.*;

public class InMemoryFileSystem {
    public static void main(String[] args) {
        FileSystem fs = FileSystem.getInstance();
        
        while(true) {
            System.out.println(fs.getCurrentDirectory() + "> ");
            
            List<String> params = parseCommand();
            fs.execute(params);
        }
    }
    
    public static List<String> parseCommand() {     
        List<String> params = new LinkedList<String>();
        
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String commands = bufferRead.readLine();
            String[] result = commands.split(" ");

            for(String str : result) {
                String trimed = str.trim();
                if(!trimed.equals(""))
                    params.add(trimed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return params;
    }
}