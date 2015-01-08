public class OneEditDistance {
    public static boolean oneDistance(String s1, String s2) {
        if(Math.abs(s1.length()-s2.length()) > 1)
            return false;

        if (s1 == null || s1.length() == 0)
            return s2 != null && s2.length() == 1;

        if (s2 == null || s2.length() == 0)
            return s1 != null && s1.length() == 1;

        if (s1.charAt(0) == s2.charAt(0))
            return oneDistance(s1.substring(1), s2.substring(1));
        else
            return s1.substring(1).equals(s2.substring(1)) || s1.substring(1).equals(s2) || s1.equals(s2.substring(1));
    }

	// not yet carefully verified
    public static boolean oneDistance(String left, String right) {
        String longer = left.length() > right.length() ? left : right;
        String shorter = left.length() > right.length() ? right : left;

        for (int i = 0; i < shorter.length(); i++) {
            if (shorter.charAt(i) != longer.charAt(i)) {
				// we can delete one char from the longer or replace one char from either
                return shorter.substring(i).equals(longer.substring(i + 1)) || shorter.substring(i+1).equals(longer.substring(i+1));
            }
        }

		// if we matched so far, we still need to check the longer has only one remaining char to delete
        return longer.length() == shorter.length()+1;
    }
}
