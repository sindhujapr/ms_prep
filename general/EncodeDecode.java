package general;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class EncodeDecode {
    public static void main(String[] args) throws CharacterCodingException {
        CharBuffer cb = ByteBuffer.allocate(1000).asCharBuffer();
        cb.put("helloworldfuckccp");
        cb.flip();
        System.out.println(cb);

        Charset outCharset = Charset.forName("UTF-16");
        Charset inCharset = Charset.forName("UTF-16");

        CharsetDecoder inDecoder = inCharset.newDecoder();
        CharsetEncoder outEncoder = outCharset.newEncoder();

        ByteBuffer outbb = outEncoder.encode(cb);
        while (outbb.position() < outbb.limit())
            System.out.print(outbb.getChar());
        System.out.println();

        outbb.flip();

        CharBuffer ccb = inDecoder.decode(outbb);
        while (ccb.position() < ccb.limit())
            System.out.print(ccb.get());
    }
}