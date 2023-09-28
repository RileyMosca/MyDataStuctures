public class ArrayList {

    /* Current Array Size */
    public int size = 0;

    /* Current Array Count */
    public int count = 0;

    /* Generic Array */
    public Object[] array = null;

    /* Array Constructor */
    public ArrayList(int initialSize) {
        this.array = new Object[initialSize];
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
     * @return (Object) value within array at given index
     */
    public Object get(int index) {
        return this.array[index];
    }

    /***
     * Method that sets the value in an array at a given index
     * @param item (Object) Object that replaces previous indexed value
     * @param index (int) Index in the array that is being replaced
     */
    public void set(Object item, int index) {
        this.array[index] = item;
    }

    /***
     * Method that adds an item to the end of an array
     * and expands the size by double when the original
     * capacity is exceeded by the item count
     * @param item (Object) the item that is being added
     */
    public void addItem(Object item) {

        /* Size is at capacity, double array capacity */
        if(count == this.array.length) {
            /* New Array Size Multiplier */
            int sizeMultiplier = 2;

            /* Initialise new generic array of double capacity */
            Object[] newArray = new Object[this.size * sizeMultiplier];
            this.doubleSize();

            /* Copy items of old array to new array */
            for (int idx = 0; idx < this.array.length; idx++) {
                newArray[idx] = this.get(idx);
            }
            this.array = newArray;

            /* Adding item to new Array */
            this.array[this.count] =  item;

            /* Increment Count */
            this.count++;

            return;
        }

        /* Else add to original array, and increment count */
        this.array[count] = item;
        this.count++;
    }

    /***
     * Method that adds an item at any specified index, and
     * shifts elements of the previous array around this new
     * item
     * @param item (Object) Item which is being added to the array
     * @param index (int) Index that the item is being added to
     */
    public void addAtIndex(Object item, int index) {
        /* Increase Array if needed with previous logic */
        addItem(item);

        /* Update class array (size handled by addItem method) */
        this.array = arrayCopyHelper(item, index);
    }

    /***
     * Helper Method that creates a copy of an array based
     * on the type of element (null or otherwise) being added.
     * null additions are treated as removals
     * @param item (Object) Item being added to the array
     * @param index (int) Index the item is being added to
     * @return (Object[]) A copy of the new generic array
     * after the changes have been made
     */
    public Object[] arrayCopyHelper(Object item, int index) {
        /* Create new array of same size, for copying */
        Object[] newArray = new Object[this.size + 1];

        /*
            ADDING AN ITEM:

            Replacing and shifting items at indices
            only replacing at index, else copying
            original contents of array into new array
            around the specified index.
        */
        if(item != null) {
            for(int idx = 0; idx < this.count; idx++) {
                if(idx == index) {
                    newArray[idx] = item;
                } else if (idx < index) {
                    newArray[idx] = this.get(idx);
                } else {
                    newArray[idx] = this.get(idx - 1);
                }
            }
        }

        /*
            REMOVING AN ITEM:
            Removes an item at a specified index,
            and shifts elements back
         */
        else {
            for(int idx = 0; idx < this.count - 1; idx++) {
                if(this.get(idx) != null) {
                    if(idx < index) {
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

        /* Append value separated elements to string builder */
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
        for(int i = 0; i < this.toString().length(); i++) {
            char charAtIndex =  this.toString().charAt(i);
            hashCode += (int) charAtIndex * ((i + 1) * (i + 2));
        }
        return hashCode;
    }
}



