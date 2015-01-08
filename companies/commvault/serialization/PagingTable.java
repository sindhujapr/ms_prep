package interview.commvault.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Generate specified MyObject instances and keep them in a file,
 * then load some of the instances on-demand.
 */
public class PagingTable implements IPageLoader {
	private int count;
	private int start;
	private int end;

	/* Size of each object, which is kept right after the first 4 bytes */
	private List<Integer> sizes = new ArrayList<Integer>();
	/* Offset of each object in the file */
	private List<Integer> offsets = new ArrayList<Integer>();
	private List<MyObject> objects = null;

	public PagingTable() {
		
	}

	@Override
	public void setDataAvailable(int start, int end) {
		if (start >= end || start < 0) {
			System.out.println("Invalid parameter");
			// throw new IllegalArgumentException();
		}

		RandomAccessFile randFile = null;
		try {
			randFile = new RandomAccessFile(IPageLoader.DATA_FILE, "rw");

			this.count = randFile.readInt();
			objects = new ArrayList<MyObject>(count);
			System.out.println("# of objects " + count);

			if (end >= count) {
				System.out.println("Only " + count + " objects found.");
				// throw new IllegalArgumentException();
			}

			for (int i = 0; i < count; i++) {
				int size = randFile.readInt();
				sizes.add(size);
			}
			System.out.println(sizes);

			/* this is the offset of the beginning of data part */
			int initOffSet = 4 + count * 4;
			/*
			 * calculate the offset of each object, which is (4+count*4) plus
			 * sizes of all previous objects
			 */
			for (int i = 0; i < count; i++) {
				int sum = initOffSet;
				for (int j = 0; j < i; j++) {
					sum += sizes.get(j);
				}

				offsets.add(sum);
			}
			System.out.println(offsets);

			/* suppose the size of each object is less than 1024 */
			byte[] bytes = new byte[1024];

			/* now we need to load objects [start, end] */
			for (int i = start; i <= end; i++) {
				randFile.seek(offsets.get(i));
				randFile.read(bytes, 0, sizes.get(i));
//				System.out.println("object #" + i + "\t\t" + new String(bytes));
				String objectStr = new String(bytes);
				MyObject object = MyObject.parseMyObject(objectStr);
				objects.add(object);
				Arrays.fill(bytes, (byte)0x00);
			}
			System.out.println(objects);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				randFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public MyObject getRowAt(int row) {
		if (row < 0 || row > count) {
			System.out.println("Invalid parameter");
			// throw new IllegalArgumentException();
		}

		if (row >= start && row <= end) {
			return objects.get(row - start);
		}

		/* Read the specified row from the file and return it */

		return null;
	}

	@Override
	public int getRowCount() {
		return count;
	}

	public static void main(String[] args) {
		GenerateData.generateData(new File(IPageLoader.DATA_FILE), 20);

		PagingTable pt = new PagingTable();
		pt.setDataAvailable(3, 10);
	}
}
