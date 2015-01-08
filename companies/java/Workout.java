package interview.java;

public class Workout {
	public static void main(String[] args) {
		workHard();
		System.out.println("it's nap time");
	}
	
	public static void workHard() {
		try {
			workHard();
		} finally {
			workHard();
		}
	}
}