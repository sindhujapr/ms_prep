package interview.turn;

import java.util.ArrayList;
import java.util.List;

/*
 * Heap data structure
 * Implement a priority queue in Java with the highest priority element at the front of the queue.
 */


public class PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.addElement(5);
        queue.addElement(2);
        queue.addElement(4);
        
        System.out.println(queue.getElement());
        System.out.println(queue.getElement());
        System.out.println(queue.getElement());
        
        System.out.println(Double.valueOf("3.0"));
    }
    
    private List<Integer> list = new ArrayList<Integer>();

    public void addElement(int i) {
        list.add(0, i);
        heapify(0);
    }

    public int getElement() {       
//      if(list.size() == 0)
//          throw new NoSuchElementException("whatever");
        int ret = list.remove(0);

        if(list.size() > 0)
            heapify(0);
        return ret;
    }

    private int leftChild(int i) {
        return 2*i +1;
    }

    private int rightChild(int i) {
        return 2*i +2;
    }

    private void heapify(int i) {
        int size = list.size()-1;

        int left = leftChild(i), right = rightChild(i);

        int value = list.get(i);
        int largest = left > size ? i : (list.get(left) > value ? left : i);
        largest = right > size ? largest : (list.get(right) > list.get(largest) ? right : largest);
    
        if(largest != i) {
            swap(largest, i);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}