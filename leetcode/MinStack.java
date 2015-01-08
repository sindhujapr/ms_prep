package lc;

public class MinStack {
    private List<Integer> stack = new ArrayList<Integer>();
    private List<Integer> minStack = new ArrayList<Integer>();
    
    public void push(int x) {
        stack.add(x);
        if(minStack.size() == 0 || x <= minStack.get(minStack.size()-1))
            minStack.add(x);
    }
    
    public void pop() {
        int top = stack.remove(stack.size()-1);
        if(top != minStack.get(minStack.size()-1))
            return;
        
        minStack.remove(minStack.size()-1);
    }

    public int top() {
        return stack.get(stack.size()-1);
    }

    public int getMin() {
        return minStack.get(minStack.size()-1);
    }

    /*
     * not sure why the above exceeds memory limit while the below doesn't.
     * It seems be caused by ArrayList vs. Stack (???)
     */
    private Stack<Integer> mStack = new Stack<Integer>();
    private Stack<Integer> mMinStack = new Stack<Integer>();
    
    public void push(int x) {
        mStack.push(x);
        if (mMinStack.size() != 0) {
            int min = mMinStack.peek();
            if (x <= min) {
                mMinStack.push(x);
            }
        } else {
            mMinStack.push(x);
        }
    }
    
    public void pop() {
        int x = mStack.pop();
        if (mMinStack.size() != 0 && x == mMinStack.peek())
            mMinStack.pop();
    }
    
    public int top() {
        return mStack.peek();
    }
    
    public int getMin() {
        return mMinStack.peek();
    }
}
