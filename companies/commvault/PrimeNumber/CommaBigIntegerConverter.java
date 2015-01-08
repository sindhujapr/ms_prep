package interview.commvault.PrimeNumber;

import java.math.BigInteger;

/*
 * BigInteger doesn't support formatted output (eg, with commas, eg),
 * so I have to format the output myself.
 */
public class CommaBigIntegerConverter implements IConverter<BigInteger> {
    /* 
     * Maximum width of the output, which will be calculated
     * based on the maximum number and be used for alignment.
     */
    private final int adjustedWidth;
    /* Maximum count of digits between two commas */
    private final int numberOfDigitsPerComma;

    public CommaBigIntegerConverter(int width, int numberOfDigitsPerComma) {
        this.numberOfDigitsPerComma = numberOfDigitsPerComma;
        /* Take into consideration of the commas that will be inserted into the longest number */
        this.adjustedWidth = width + (width-1) / numberOfDigitsPerComma;
    }

    /*
     * we can use System.out.format("%,d", new BigInteger("10000000"));
     */
    @Override
    public String convert(BigInteger bInt) {
        String str = bInt.toString();
        int size = str.length();
        /* Calculate how many commas for this number */
        int numOfCommas = (size-1) / numberOfDigitsPerComma;

        /* Initial capacity set to the length of the number plus necessary commas */
        StringBuilder sBuilder = new StringBuilder(adjustedWidth);
            
        /* Prefix blank spaces according to the adjusted width */
        int numOfPreceedingBlanks = adjustedWidth-size-numOfCommas;
        for(int i = 0; i < numOfPreceedingBlanks; i++)
            sBuilder.append(" ");

        /* Append the leading digits. Then append a comma following by three digits */
        int index = size - numberOfDigitsPerComma * numOfCommas;
        sBuilder.append(str.substring(0, index));
        for (int i = 0; i < numOfCommas; i++) {
            sBuilder.append(',');
            sBuilder.append(str.substring(index, index+numberOfDigitsPerComma));
            index += numberOfDigitsPerComma;
        }
        return sBuilder.toString();
    }
}