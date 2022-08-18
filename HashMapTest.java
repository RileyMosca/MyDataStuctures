
class HashMapTest {

    /***
     * Test code in main
     * @param args default args
     */
    public static void main(String[] args) {

        HashMap<String, Integer> newMap = new HashMap<>();
        System.out.println(newMap.size());

        /* Add some key and value */
        newMap.put("Key_1", 1);

        /* Check that size is now "1" */
        System.out.println("Actual Size = " + newMap.size()  + ", Expected Size = 1");

        /* Print the head entries key and value */
        System.out.println("Actual Key = " + newMap.headEntry.key  + ", Expected Key = Key_1");
        System.out.println("Actual Value = " + newMap.headEntry.value  + ", Expected Value = " +
                "1");

        /* Check that the key and value exist*/
        System.out.println("Contains Key (Actual) = " + newMap.containsKey("Key_1")  + ", " +
                "Contains Key (Expected) = True");

        System.out.println("Contains Value (Actual) = " + newMap.containsValue(1)  + ", " +
                "Contains Value (Expected) = True");

        /* Add another key and value */
        newMap.put("Key_2", 2);

        /* check that the size has increased should be "2"*/
        System.out.println("Actual Size = " + newMap.size()  + ", Expected Size = 2");

        /* Add another key and value */
        newMap.put("Key_3", 3);

        /* check that the size has increased should be "2"*/
        System.out.println("Actual Size = " + newMap.size()  + ", Expected Size = 3");

        System.out.println(newMap.toString());
        System.out.println(newMap.peek().value);

        System.out.println("Contains Key (Actual) = " + newMap.containsKey("Key_3")  + ", " +
                "Contains Key (Expected) = True");
        newMap.remove("Key_3");

        System.out.println("ANSWER = " + "[" + newMap.toString() + "]");

    }
}

/**
 *  C
 * @param <K> Data type for Key is set to K for generics
 * @param <V> Data type for Value is set to V for generics
 */
class MapEntry<K, V> {

    /* Initialising Hash Map elements as public elements */
    public K key;
    public V value;
    public MapEntry<K, V> next;

    /**
     * Constructor for Hash Map entry, taking a key and value pair
     * @param key The key of the entry (with T type)
     * @param value The value of the entry (with T type)
     */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashMap<K, V> {

    /* Initialising Variables as public*/
    public int size = 0;
    public MapEntry<K, V> headEntry;

    /**
     * Generic constructor for a hash map, initialises an empty hash map
     */
    public HashMap() {}

    /**
     *  Checks the size of the hash map (by entries)
     * @return returns the size as an integer
     */
    public int size() {
        return size;
    }

    /**
     * Increasing the size of the hash map by 1
     */
    public void increaseSize() {
        this.size++;
    }

    /**
     * Decreasing the size of the hash map by 1
     */
    public void decreaseSize() {
        this.size--;
    }

    /**
     *  Checks whether the map is empty
     * @return returns true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public K put(K key, V value) {

        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        newEntry.key = key;
        newEntry.value = value;
        newEntry.next = null;

        /* No entries present, therefore this is the "head entry" */
        if(headEntry == null) {

            headEntry = newEntry;
        } else {

            MapEntry<K, V> existingEntry = headEntry;
            /* Init of new map entry to add to existing non empty map */
            while(existingEntry.next != null) {

                existingEntry = existingEntry.next;
            }
            existingEntry.next = newEntry;
        }


        /* Increase the size, now that an element has been removed */
        increaseSize();

        /* return the key that has been inputted into the map */
        return key;
    }

    /**
     *  Checks that the hash map contains the specified key
     * @param key the key we are checking in the hash map
     * @return true if key exists in map, false otherwise
     */
    public boolean containsKey(K key) {

        /* head node is null, no entry exists, return false*/
        if(headEntry == null) return false;

        /* Check every entry in the map until null for the key */
        while(headEntry != null) {

            if(headEntry.key == key) {
                return true;
            }
            headEntry = headEntry.next;
        }
        return false;
    }

    /**
     *  Checks that the hash map contains the specified key
     * @param value the value we are checking in the hash map
     * @return true if value exists in map, false otherwise
     */
    public boolean containsValue(V value) {

        /* head node is null, no entry exists, return false*/
        if(headEntry == null) return false;

        /* Check every entry in the map until null for the key */
        while(headEntry != null) {

            if(headEntry.value == value) {

                return true;
            }
            headEntry = headEntry.next;
        }
        return false;
    }

    /**
     * Removes and returns the
     * @param key of the entry to remove
     * @return a MapEntry<K, V> containing the Key Value pair removes
     */
    public MapEntry<K, V> remove(K key) {
        MapEntry<K, V> tempEntry = null;
        MapEntry<K, V> entryToRemove = headEntry;

        if(headEntry.key == key) {
            headEntry = headEntry.next;
            decreaseSize();
        }

        if(containsKey(key)) {

            while(headEntry.key != key) {

                entryToRemove = headEntry.next;
                System.out.println(headEntry.next);
            }



        }
        return entryToRemove;
    }

    /**
     * Peeks the map for the head entry
     * @return returns the head entry as a MapEntry<K, V>
     *         with key value pair
     */
    public MapEntry<K, V> peek() {
        return headEntry;
    }

    /**
     * Prints out the key value pairs
     */
    @Override
    public String toString() {
        MapEntry<K, V> entryToPrint = headEntry;
        StringBuilder result = new StringBuilder();

        while(entryToPrint != null) {
            result.append("Key = ").append(entryToPrint.key)
                    .append(", Value = ").append(entryToPrint.value).append("\n");
            entryToPrint = entryToPrint.next;
        }
        return result.toString();
    }
}