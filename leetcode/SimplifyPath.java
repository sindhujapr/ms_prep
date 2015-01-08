package lc;

import java.util.ArrayList;
import java.util.List;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath instance = new SimplifyPath();
//      System.out.println(instance.simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
        System.out.println(instance.simplifyPath1("/."));
    }

    public String simplifyPath1(String path) {
        String[] paths = path.split("/");
        List<String> stack = new ArrayList<String>();
        
        for(String p : paths) {
            // we cannot write it like this if(p.equals("..") && stack.size() > 0)
            if(p.equals("..")) {
                if(stack.size() > 0)
                    stack.remove(stack.size()-1);
            } else if(!p.equals(".") && p.length() > 0) {
                stack.add(p);
            }
        }
        
        if(stack.size() == 0)
            return "/";
            
        StringBuilder builder = new StringBuilder();
        for(String p : stack)
            builder.append("/").append(p);
        return builder.toString();
    }
    
    public String simplifyPath(String path) {
        if (path.length() == 0)
            return path;

        if (path.lastIndexOf('/') == path.length() - 1 && !path.equals("/"))
            path = path.substring(0, path.length() - 1);

        List<String> stack = new ArrayList<String>();
        for (int i = 0; i < path.length() && i != -1;) {
            int index1 = path.indexOf('/', i);
            if (index1 < 0)
                break;
            int index2 = path.indexOf('/', index1 + 1);
            String sub;
            if (index2 < 0)
                sub = path.substring(index1);
            else
                sub = path.substring(index1, index2);

            if (sub.equals("/")) {
                if (stack.size() == 0)
                    stack.add("/");
            } else if (sub.equals("/.")) {
                if (stack.size() == 0)
                    stack.add("/");
            } else if (sub.equals("/..")) {
                if (stack.size() > 0
                        && !stack.get(stack.size() - 1).equals("/"))
                    stack.remove(stack.size() - 1);
                if(stack.size() == 0)
                    stack.add("/");
            } else {
                if (stack.size() > 0 && stack.get(stack.size() - 1).equals("/"))
                    stack.remove(stack.size() - 1);
                stack.add(sub);
            }

            i = index2;
        }

        StringBuilder sb = new StringBuilder();
        for (String str : stack)
            sb.append(str);

        return sb.toString();
    }
}