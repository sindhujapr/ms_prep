package interview.commvault.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateData {

    public static void generateData(File file, int count) {
        
        /*
         * The first four bytes will keep the number of objects.
         * The following bytes will keep the length of each object.
         * The rest part will be the serialized objects
         */
        List<Integer> list = new ArrayList<Integer>();
        List<MyObject> objects = new ArrayList<MyObject>();

        RandomAccessFile randFile = null;
        try {
            randFile = new RandomAccessFile(IPageLoader.DATA_FILE, "rw");
            randFile.writeInt(count);
            
            for (int i = 0; i < count; i++) {
                MyObject obj = new MyObject();
                /* The string field doesn't allow separator */
                obj.setColumn1("Row" + i);
                obj.setColumn2((int) Math.random() * count);
                obj.setColumn3(Math.random() > 0.5 ? true : false);
                obj.setColumn4(Math.round(1.255));
                
                list.add(obj.toString().length());
                objects.add(obj);
            }
            
            for (int i = 0; i < count; i++) {
//              System.out.println("length of object #" + i + ": " + list.get(i));
                randFile.writeInt(list.get(i));
            }
            for (int i = 0; i < count; i++) {
                randFile.writeBytes(objects.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(randFile != null)
                    randFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /* this is to verify we can parse the file with RandomAccessFile */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int count = 20;
        GenerateData.generateData(new File(IPageLoader.DATA_FILE), count);
        
        RandomAccessFile randFile = new RandomAccessFile(IPageLoader.DATA_FILE, "rw");
        int cnt = randFile.readInt();

        
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < cnt; i++) {
            list.add(randFile.readInt());
        }

        int offset = 4 + cnt * 4;
        randFile.seek(offset);
        byte[] bytes = new byte[1024];
        for (int i = 0; i < cnt; i++) {
            randFile.read(bytes, 0, list.get(i));
            System.out.println("row #" + i + "\t\t" + new String(bytes));
            Arrays.fill(bytes, (byte)0x00);
        }
        
        randFile.close();
    }
}