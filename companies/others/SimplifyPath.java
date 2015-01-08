package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 9/15/14.
 */
public class SimplifyPath {
    public static void main(String[] args) {
        String path = new SimplifyPath().simplifyPath("/..");
        System.out.println(path);
    }

    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        List<String> stack = new ArrayList<String>();
        for(String str : paths) {
            if(str.equals("..") && stack.size() > 0) {
                stack.remove(stack.size()-1);
            } else if (str.length() > 0 && !str.equals(".")) {
                stack.add(str);
            }
        }

        if(stack.size() == 0)
            return "/";

        StringBuilder builder = new StringBuilder();
        for(String str : stack) {
            builder.append("/").append(str);
        }

        return builder.toString();
    }
}
