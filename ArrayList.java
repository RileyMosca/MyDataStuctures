public class ArrayList<T> {

    /* Current Array Size */
    public int size = 0;

    /* Current Array Count */
    public int count = 0;

    /* Generic Array */
    public T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[10];
        this.size = 10;
    }

    /* Array Constructor (parameterised) */
    public ArrayList(int initialSize) {
        this.array = (T[]) new Object[initialSize];
        this.size = initialSize;
    }

    /***
     * Method that retrieves the array count
     * @return (int) count of the array
     */
    public int getCount() {
        return this.count;
    }

    /***
     * Method that sets the array count
     * @param count (int) new array count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /***
     * Method that retrieves the array size
     * @return (int) size of the array
     */
    public int getSize() {
        return this.size;
    }

    /***
     * Method that sets a new array size, given an int
     * @param size (int) New size of the array
     */
    public void setSize(int size) {
        this.size = size;
    }

    /***
     * Method that doubles array size
     */
    public void doubleSize() {
        int sizeMultiplier = 2;
        setSize(this.size * sizeMultiplier);
    }

    /***
     * Method that retrieves an array value, given an index
     * @param index (int) Index of the array we wish to access
     * @return (T) value within array at given index
     */
    public T get(int index) {
        return this.array[index];
    }

    /***
     * Method that sets the value in an array at a given index
     * @param item (T) Object that replaces the previous indexed value
     * @param index (int) Index in the array that is being replaced
     */
    public void set(T item, int index) {
        this.array[index] = item;
    }

    /***
     * Method that adds an item to the end of an array
     * and expands the size by double when the original
     * capacity is exceeded by the item count
     * @param item (T) the item that is being added
     */
    public void addItem(T item) {
        /* Size is at capacity, double array capacity */
        if (count == this.array.length) {
            /* New Array Size Multiplier */
            int sizeMultiplier = 2;

            /* Initialize new generic array of double capacity */
            T[] newArray = (T[]) new Object[this.size * sizeMultiplier];
            this.doubleSize();

            /* Copy items of old array to new array */
            System.arraycopy(this.array, 0, newArray, 0, this.count);
            this.array = newArray;

            /* Adding item to new Array */
            this.array[this.count] = item;

            /* Increment Count */
            this.count++;
        } else {
            /* Else add to the original array, and increment count */
            this.array[count] = item;
            this.count++;
        }
    }

    /***
     * Method that adds an item at any specified index and
     * shifts elements of the previous array around this new
     * item
     * @param item (T) Item which is being added to the array
     * @param index (int) Index that the item is being added to
     */
    public void addAtIndex(T item, int index) {
        /* Increase Array if needed with previous logic */
        addItem(item);

        /* Update class array (size handled by addItem method) */
        this.array = arrayCopyHelper(item, index);
    }

    /***
     * Helper Method that creates a copy of an array based
     * on the type of element (null or otherwise) being added.
     * null additions are treated as removals
     * @param item (T) Item being added to the array
     * @param index (int) Index the item is being added to
     * @return (T[]) A copy of the new generic array
     * after the changes have been made
     */
    private T[] arrayCopyHelper(T item, int index) {
        /* Create new array of the same size, for copying */
        T[] newArray = (T[]) new Object[this.size + 1];

        /*
            ADDING AN ITEM:

            Replacing and shifting items at indices
            only replacing at index, else copying
            original contents of array into a new array
            around the specified index.
        */
        if (item != null) {
            for (int idx = 0; idx < this.count; idx++) {
                if (idx == index) {
                    newArray[idx] = item;
                } else if (idx < index) {
                    newArray[idx] = this.get(idx);
                } else {
                    newArray[idx] = this.get(idx - 1);
                }
            }
        } else {
            /*
                REMOVING AN ITEM:
                Removes an item at a specified index,
                and shifts elements back
            */
            for (int idx = 0; idx < this.count - 1; idx++) {
                if (this.get(idx) != null) {
                    if (idx < index) {
                        newArray[idx] = this.get(idx);
                    } else {
                        newArray[idx] = this.get(idx + 1);
                    }
                }
            }
        }
        return newArray;
    }

    /***
     * Method that removes an item from the array at a given index
     * @param index (int) Index of the item to be removed
     */
    public void remove(int index) {
        /* Use Helper to remove item at index */
        this.array = arrayCopyHelper(null, index);

        /* Reduce count */
        this.count--;
    }

    /***
     * Overridden Method of the toString() method that
     * represents a human-readable string displaying the
     * contents of the array
     * @return (String) Human-readable representation of
     * the array
     */
    @Override
    public String toString() {
        /* Defined builder strings/ chars */
        String separator = ", ";
        char openBrace = '[';
        char closeBrace = ']';

        /* Initialise String builder */
        StringBuilder sb = new StringBuilder();

        /* Add opening brace to display */
        sb.append(openBrace);

        /* Append value-separated elements to the string builder */
        for (int i = 0; i < this.count; i++) {
            if (i < this.count - 1) {
                sb.append(this.array[i]);
                sb.append(separator);
            } else {
                sb.append(this.array[i]);
            }
        }

        /* Add closing brace to display */
        sb.append(closeBrace);

        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < this.count; i++) {
            T element = this.array[i];
            if (element != null) {
                hashCode += element.hashCode() * ((i + 1) * (i + 2));
            }
        }
        return hashCode;
    }

    public static void main(String[] args) {

        /* Init X Array */
        ArrayList<Integer> x = new ArrayList<>(5);

        x.addItem(1);
        x.addItem(2);
        x.addItem(3);
        System.out.println(x);

        x.remove(1);

        System.out.println(x);

        /* Init Y Array */
        ArrayList<Integer> y = new ArrayList<>(5);

        y.addItem(1);
        y.addItem(2);
        y.addItem(3);

        System.out.println("Array X");
        String display_x = x.toString();
        System.out.println(display_x);
        System.out.println("Size = " + x.size + ", Count = " + x.count + " Hashcode = " + x.hashCode());

        System.out.println("Array Y");
        String display_y = y.toString();
        System.out.println(display_y);
        System.out.println("Size = " + y.size + ", Count = " + y.count + " Hashcode = " + y.hashCode());

        System.out.println(x.hashCode() == y.hashCode() ? "MATCH" : "DIFFERENT");
    }
}
