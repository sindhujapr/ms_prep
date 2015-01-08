package interview.commvault.PrimeNumber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Timer;

/*
 * The upper bound may be arbitrary big, thus I use BigInteger to avoid overflow.
 */
public class PrimeNumberGeneratorTest {
    private final INumberGenerator<BigInteger> generator;
    private final BigInteger upperBound;
    private final IConverter<BigInteger> converter;

    @SuppressWarnings("unused")
    private PrimeNumberGeneratorTest() {
        this(null, null, null);
    }

    public PrimeNumberGeneratorTest(INumberGenerator<BigInteger> generator,
            BigInteger upperBound, IConverter<BigInteger> converter) {
        this.generator = generator;
        this.upperBound = upperBound;
        this.converter = converter;
    }

    public static void main(String[] args) throws IllegalArgumentException {
        /* Change these two paths if needed */
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        BufferedReader bufReader = null;
        BigInteger upperBound = null;
        Integer cntPerLine = null;

        try {
            bufReader = new BufferedReader(new FileReader(new File(inputFile)));
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                if (line.matches("upperBound[ ]*=[ ]*[0-9]{1,}"))
                    upperBound = new BigInteger(line.substring(
                            line.indexOf('=') + 1).trim());
                else if (line.matches("cntPerLine[ ]*=[ ]*[0-9]{1,9}"))
                    cntPerLine = Integer.parseInt(line.substring(
                            line.indexOf('=') + 1).trim());
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (bufReader != null)
                    bufReader.close();
            } catch (Exception e2) {
                System.err.println("Error closing the input file");
            }
        }

        if (upperBound == null ||
            upperBound.compareTo(new BigInteger("2")) < 0 ||
            cntPerLine == null ||
            cntPerLine <= 0)
            throw new IllegalArgumentException("upper bound: " + upperBound
                    + " count per line: " + cntPerLine);

        INumberGenerator<BigInteger> generator = new PrimeNumberGenerator();
        int longestLength = upperBound.toString().length();
        IConverter<BigInteger> converter = new CommaBigIntegerConverter(longestLength, 3);

        PrimeNumberGeneratorTest instance = new PrimeNumberGeneratorTest(generator, upperBound, converter);
        instance.output(outputFile, cntPerLine);
        System.out.println("Please check the results in " + outputFile);
    }

    public void output(String outputFile, int cntPerLine) {
        BufferedWriter bufWriter = null;
        try {
            bufWriter = new BufferedWriter(new FileWriter(new File(outputFile)));

            StringBuilder strBuilder = new StringBuilder(upperBound.intValue()/10);
            while (true) {
                BigInteger next = null;
                for (int i = 0; i < cntPerLine; i++) {
                    next = generator.next();
                    if (next.compareTo(upperBound) > 0) {
                        bufWriter.write(strBuilder.toString());
                        return;
                    }

                    strBuilder.append(converter.convert(next));
                    if (i == cntPerLine - 1)
                        strBuilder.append("\n");
                    else
                        strBuilder.append("  ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufWriter != null)
                    bufWriter.close();
            } catch (Exception e2) {
                System.err.println("Error closing the output file");
            }
        }
    }
}