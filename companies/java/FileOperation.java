package interview.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileOperation implements Serializable {
	transient private final int i = 10;
	private static String str = "hello";

	/*public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("C:/playground/filedeletion.log")));
			String str = null;
			StringBuilder sb = new StringBuilder(2000);
			
			while((str = br.readLine()) != null)
				sb.append(str);
			System.out.println(sb.toString());
			System.out.println(sb.capacity());
			System.out.println(sb.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public static void main(String[] args) {
		PrintWriter pw = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			
//			pw = new PrintWriter(System.out, true);
//			pw.println("print writer");

			FileChannel in = new FileInputStream(args[0]).getChannel();
			FileChannel out = new FileOutputStream(args[1]).getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(in.read(buffer) != -1) {
				/* prepare to be read */
				buffer.flip();
				out.write(buffer);
				/* prepare for the next operation */
				buffer.clear();
			}

			FileChannel in1 = new FileInputStream(new File("C:/playground/fileoperation.txt")).getChannel();
			FileChannel out1 = new FileOutputStream(new File("C:/playground/fileoutput2.txt")).getChannel();
			ByteBuffer buffer1 = ByteBuffer.allocate(1024);
			while(in1.read(buffer1) != -1) {
				buffer1.flip();
				out1.write(buffer1);
				buffer1.clear();
			}
				
//			infc.close();
//			outfc.close();

			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/playground/fileoperation.txt"))); 
			char buf[] = {'a', 'v', 'j', 's' };
//			pw.write(buf, 0, 2);
//			oos.writeObject(new FileOperation());
			oos.writeInt(10);
			oos.writeBoolean(false);
			oos.writeBytes("hello");
			oos.close();
//		
			ois = new ObjectInputStream(new FileInputStream(new File("C:/playground/fileoperation.txt")));
//			FileOperation fo = (FileOperation) ois.readObject();
//			System.out.println(fo.i);
//			System.out.println(fo.str);
			System.out.println(ois.readInt());
			System.out.println(ois.readBoolean());
			for (int i = 0; i < 5; i++) {
				System.out.print((char) (ois.readByte()));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);;
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try{ 
//				oos.close();
//				ois.close();
			} catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}