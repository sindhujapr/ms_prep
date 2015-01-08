package careercup.fb;

/*
 * http://www.careercup.com/question?id=13216725
 * http://stackoverflow.com/questions/10013933/conversion-to-proper-postfix-notation-in-minimum-number-of-steps
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        rnf("x**x");
        rnf("x**x*");
        rnf("x**x**");
    }

    public static void rnf(String input) {
        int xs = 0;
        int noOfReplaces = 0;
        int noOfdeletes = 0;
        int noOfInserts = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'x') {
                xs++;
                continue;
            }

            if (xs > 1) {
                xs--;
            } else if (xs == 1) {
                if (noOfdeletes > 0) {
                    noOfReplaces++;
                    noOfdeletes--;
                } else {
                    // If this is last {
                    if (i == input.length() - 1) {
                        // Add one x at first
                        noOfInserts++;
                    } else {
                        noOfdeletes++;
                    }
                }
            } else {
                if (noOfdeletes > 1) {
                    // As the 2 deletes can be replcaced with xs
                    noOfdeletes = noOfdeletes - 2;
                    noOfReplaces = noOfReplaces + 2;
                } else
                    noOfdeletes++;
            }
        }

        while (xs > 1) {
            if (xs > 2) {
                // Replace one x with *
                noOfReplaces++;
                xs = xs - 2;
            }
            if (xs == 2) {
                // Add one * at last
                noOfInserts++;
                xs--;
            }
        }

        System.out.println("deletes:" + noOfdeletes);
        System.out.println("insersts:" + noOfInserts);
        System.out.println("replaces:" + noOfReplaces);
        System.out.println("total:" + (noOfdeletes + noOfReplaces + noOfInserts));
    }
}