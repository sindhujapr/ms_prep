package interview.java;

final class RefObject<T>
{
    public T argvalue;
    public RefObject(T refarg) { argvalue = refarg; }
    public String toString() { return argvalue.toString(); }
}

public class Swap {
	public static void swap(RefObject<Integer> p, RefObject<Integer> q)
	{
//		T temp;
//		temp = p.argvalue;
//		p.argvalue = q.argvalue;
//		q.argvalue = temp;
		p.argvalue ^= q.argvalue;
		q.argvalue ^= p.argvalue;
		p.argvalue ^= q.argvalue;
	}
	
	public static void swap(int arr[], int i, int j) {
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}
	
	public static void swap(Integer aa, Integer bb) {
		aa ^= bb;
		bb ^= aa;
		aa ^= bb;
	}
	
	public static void main(String[] args) {
		RefObject<Integer> r1 = new RefObject<Integer>(new Integer(1));
		RefObject<Integer> r2 = new RefObject<Integer>(new Integer(2));
		
		swap(r1, r2);
		
		System.out.println(r1 + "\t" + r2);
		
		int array[] = new int[] { 1, 2 };
		swap(array, 0, 1);
		System.out.println(array[0] + "\t" + array[1]);
		
		// Doesn't work for primitive types and wrapped types.
		Integer ma = new Integer(1);
		Integer mb = new Integer(2);
		swap(ma, mb);
		System.out.println(ma + "\t" + mb);
	}
}