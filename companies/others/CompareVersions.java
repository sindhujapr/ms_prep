package test;

/**
 * Created by qingcunz on 12/16/14.
 */
public class CompareVersions {
    public static void main(String[] args) {
        System.out.println(compareVersion("1", "0"));
    }

    public static int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;
        while(i1 < version1.length() || i2 < version2.length()) {
            if(i1 >= version1.length())
                return -1;
            else if(i2 >= version2.length())
                return 1;

            int j1 = i1+1;
            while(j1 < version1.length() && version1.charAt(j1) != '.')
                j1++;
            int v1 = Integer.valueOf(version1.substring(i1, j1));
            i1 = j1+1;

            int j2 = i2+1;
            while(j2 < version2.length() && version2.charAt(j2) != '.')
                j2++;
            int v2 = Integer.valueOf(version2.substring(i2, j2));
            i2 = j2+1;

            if(v1 > v2)
                return 1;
            else if(v1 < v2)
                return -1;
        }

        return 0;
    }
}
