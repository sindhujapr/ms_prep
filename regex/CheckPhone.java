package regex;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckPhone {

    @Test
    public void testSimpleTrue() {
        String pattern = "\\d\\d\\d([,\\s])?\\d\\d\\d\\d";
        String s = "1233323322";
        assertFalse(s.matches(pattern));
        s = "1233323";
        assertTrue(s.matches(pattern));
        s = "123 3323";
        assertTrue(s.matches(pattern));
    }
    
    @Test
    public void testPhoneNumber() {
        String pattern = "13[0-9]\\s[0-9]{4}\\s[0-9]{4}";
        String s1 = "136 6147 2948";
        String s2 = "13661472948";
        assertTrue(s1.matches(pattern));
        assertFalse(s2.matches(pattern));
    }
    
    @Test
    public void testPattern() {
        String pattern = "[1-3]{2}\\s[5-6]{2}";
        String s1 = "22 56";
        String s2 = "22 46";
        String s3 = "2256";
        assertTrue(s1.matches(pattern));
        assertFalse(s2.matches(pattern));
        assertFalse(s3.matches(pattern));
    }
}