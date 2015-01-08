public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;
        while(i1 < version1.length() && i2 < version2.length()) {
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

        if(i1 < version1.length()) {
            while(i1 < version1.length() && (version1.charAt(i1) == '0' || version1.charAt(i1) == '.'))
                i1++;
            return i1 == version1.length() ? 0 : 1;
        } else if(i2 < version2.length()) {
            while(i2 < version2.length() && (version2.charAt(i2) == '0' || version2.charAt(i2) == '.'))
                i2++;
            return i2 == version2.length() ? 0 : -1;
        }

		return 0;
    }
}
