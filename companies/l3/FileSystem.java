package interview.l3;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* B+ tree is best choice for performance consideration */
public class FileSystem {
    private final static FileSystem fs = new FileSystem();

    private Map<String, LinkedList<String>> root = new TreeMap<String, LinkedList<String>>();
    private String currentDir;

    public static FileSystem getInstance() {
        return fs;
    }

    private FileSystem() {
        root.put("/", new LinkedList<String>(Arrays.asList("/root/", "/home/")));
        root.put("/root/", null);
        root.put("/home/", null);
        currentDir = "/root/";
    }
    
    public String getCurrentDirectory() {
        return currentDir;
    }

    public void execute(List<String> params) {
        if(params.size() == 0)
            return;
        
        String command = params.remove(0);
        if(command.equalsIgnoreCase("ls"))
            commandLS(params);
        else if(command.equalsIgnoreCase("cd"))
            commandCD(params);
        else if(command.equalsIgnoreCase("touch"))
            commandTOUCH(params);
        else if(command.equalsIgnoreCase("mkdir"))
            commandMKDIR(params);
        else if(command.equalsIgnoreCase("exit"))
            System.exit(0);
        else
            System.out.println("Invalid command");
    }

    private void commandLS(List<String> params) {
        for (int i = 0; i < params.size(); i++) {
            if(!params.get(i).endsWith("/"))
                params.set(i, params.get(i) + "/");
        }

        if(params.size() == 0) {
            params.add(currentDir);
        }
        
        for(String dir : params) {
            if(root.get(dir) == null)
                System.out.println("empty");
            else
                System.out.println(root.get(dir));
        }
    }
    
    private void commandMKDIR(List<String> params) {
        if(params.size() == 0) {
            System.out.println("Folder name needed");
        } else {
            for (int i = 0; i < params.size(); i++) {
                if(!params.get(i).endsWith("/"))
                    params.set(i, params.get(i) + "/");
            }

            if(root.get(currentDir) != null)
                params.addAll(root.get(currentDir));
            root.put(currentDir, (LinkedList<String>) params);
            
            for(String folder : params)
                root.put(currentDir + folder, null);
        }
    }
    
    private void commandTOUCH(List<String> params) {
        if(params.size() == 0) {
            System.out.println("File name needed");
        } else {
            for (int i = 0; i < params.size(); i++) {
                if(params.get(i).endsWith("/")) {
                    System.out.println("file name cannot be ended with /");
                    params.remove(i);
                }
            }
            
            if(root.get(currentDir) != null)
                params.addAll(root.get(currentDir));
            root.put(currentDir, (LinkedList<String>) params);
        }
    }

    private void commandCD(List<String> params) {
        if(params.size() > 1) {
            System.out.println("Only one directory can be specified");
            return;
        }
        
        if(params.size() == 0) {
            currentDir = "/root/";
        }

        String dst = null;
        /* absolute path and relative path */
        if(params.get(0).startsWith("/")) {
            dst = params.get(0);
        } else {
            dst = currentDir + params.get(0);
        }

        dst = parsePath(currentDir, dst);
        if(!dst.endsWith("/"))
            dst = dst + "/";
        
        if(root.containsKey(dst)) {
            System.out.println("Current directory changed to " + dst);
            currentDir = dst;
        } else {
            System.out.println("invalid target folder");
        }
    }
    
    private String parsePath(String currentDir, String input) throws InvalidParameterException {
        String path = null;
        if(input.startsWith("/"))
            path = input;
        else
            path = currentDir + input;

//      String path = input.endsWith("/") ? input.substring(0, input.length() - 2) : input;
            
        Matcher matcher2 = pattern2.matcher(path);
        if(matcher2.find() && !path.matches(regex1))
            throw new InvalidParameterException("Double slashes found");

        path = path.replaceAll("\\./", "");
        path = path.replaceAll("\\w{1}/\\.\\./", "");
        return path;
    }
    
    private final String regex1 = "(/\\w*){1,}";
    private final Pattern pattern2 = Pattern.compile("[/]{2}|[.]{3}");
}