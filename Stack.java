class Stack<T> {
    /*
        Initialise an array list structure
         with a default constructor for our
         stack implementation
    */
    public ArrayList<T> list = new ArrayList<>();

    /***
     * Push an item to the end of the Stack
     * @param item to add to the stack.
     */
    public void push(T item) {
        list.addItem(item);
    }

    /***
     * Pop an item off the end of the
     * Stack. Last-in-first-out LIFO.
     * @return (T) Element at the end
     * of the stack. null if it does
     * not exist in the stack.
     */
    public T pop() {
        /* If the Stack is non-empty, pop off the stack */
        if (!isEmpty()) {
            /* Storing popped element before removal */
            T top = list.get(this.count() - 1);

            /* Removing it from Stack */
            list.remove(this.count() - 1);
            return top;
        }
        return null;
    }

    /***
     *  Method that returns the size of
     *  the stack
     * @return (int) gets the stack
     * capacity.
     */
    public int capacity() {
        return list.getSize();
    }

    /***
     *  Method that returns the number
     *  of elements in the stack.
     * @return (int) gets the stack
     * count (# of elements).
     */
    public int count() {
        return list.getCount();
    }

    /***
     *  Method that returns the state of the
     *  queue, if it is empty of not
     * @return (boolean) true if empty,
     * else false
     */
    public boolean isEmpty() {
        return list.getCount() == 0;
    }

    /***
     * Method to get a human-readable string
     * of the Stack Data Structure
     * @return (String) Human-readable string of
     * the Stack.
     */
    public String toString() {
        return list.toString();
    }
}

class StackTest {
    public static void main(String[] args) {

        /* Creating a test Stack */
        Stack<Integer> stack = new Stack<>();
        int push_amount = 10;

        /* Push a number of elements onto the stack */
        for(int i  = 0; i < push_amount; i++) {
            System.out.println("Pushing element " + (i) + " onto the stack.");
            stack.push(i);
        }

        System.out.println("Original Stack:   " + stack);

        /* Popping LIFO */
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop() + ". Giving " + stack);
        }

    }
}
