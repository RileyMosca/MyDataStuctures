public class Queue<T> {
    private ArrayList<T> list = new ArrayList<>();

    /**
     * Enqueues an element at
     * the back of the queue.
     *
     * @param item The element
     *             to be enqueued.
     */
    public void enqueue(T item) {
        list.addItem(item);
    }

    /**
     * Dequeues and returns the front
     * element from the queue.
     *
     * @return The front element if the
     * queue is not empty, otherwise null.
     */
    public T dequeue() {
        if (!isEmpty()) {
            T front = list.get(0);
            list.remove(0);
            return front;
        }
        return null;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is
     * empty, otherwise false.
     */
    public boolean isEmpty() {
        return list.getCount() == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return The number of elements in the queue.
     */
    public int size() {
        return list.getCount();
    }

    /**
     * Peeks at the front element of
     * the queue without removing it.
     *
     * @return The front element if the
     * queue is not empty, otherwise null.
     */
    public T peek() {
        if (!isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * Clears all elements from the queue, making it empty.
     */
    public void clear() {
        list = new ArrayList<>();
    }

    /**
     * Converts the queue to an array.
     *
     * @return A String containing the elements
     * of the queue.
     */
    public String toArray() {
        return list.toString();
    }
}

class QueueTest {
    public static void main(String[] args) {
        /* Testing the Queue Class */
        Queue<Integer> queue = new Queue<>();

        /* Enqueue elements FIFO */
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        /* Dequeue elements FIFO */
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
    }
}
