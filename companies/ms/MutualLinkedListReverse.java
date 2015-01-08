package interview.ms;

class Element<T> {
	private T value;
	private Element<T> next;
	private Element<T> previous;
	
	Element(T value) {
		this.value = value;
		this.next = null;
		this.previous = null;
	}
	
	public void setNext(Element<T> next) { this.next = next; }
	public void setPrevious(Element<T> prev) { this.previous = prev; }
	public Element<T> getNext() { return next; }
	public Element<T> getPrevious() { return previous; }
	public T getValue() { return value; }
	public String toString() { return value.toString(); }
}

public class MutualLinkedListReverse {
	public static void main(String[] args) {
		
	}
}