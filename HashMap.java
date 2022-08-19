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
        newMap.put("Key_2", 2);
        newMap.put("Key_3", 3);
        newMap.put("Key_4", 4);
        newMap.put("Key_5", 5);
        newMap.put("Key_6", 6);
        newMap.put("Key_7", 7);
        newMap.put("Key_8", 8);
        newMap.put("Key_9", 9);

        System.out.println("Init toString: \n" + newMap.toString());
        System.out.println("Init size = " + newMap.size());

        MapEntry<String, Integer> test = newMap.remove("Key_3");
        newMap.remove("Key_6");
        newMap.remove("Key_4");
        newMap.remove("Key_7");
        newMap.remove("Key_9");

        System.out.println("Final size  = " + newMap.size());
        System.out.println("Final toString: \n" + newMap.toString());
        System.out.println("Contains key (Key_7) [" + newMap.containsKey("Key_7") + "]");
        System.out.println("Contains Value (7) [" + newMap.containsValue(7) + "]");
        System.out.println("Contains key (Key_8) [" + newMap.containsKey("Key_8") + "]");
        System.out.println("Contains Value (8) [" + newMap.containsValue(8) + "]");
        System.out.println("\n" + newMap.toString());

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

        MapEntry<K, V> tempEntry = headEntry;
        boolean containsKey = false;

        /* Check every entry in the map until null for the key */
        while(tempEntry != null) {

            if(tempEntry.key == key) {
                containsKey = true;
                break;
            }
            tempEntry = tempEntry.next;
        }
        return containsKey;
    }

    /**
     *  Checks that the hash map contains the specified key
     * @param value the value we are checking in the hash map
     * @return true if value exists in map, false otherwise
     */
    public boolean containsValue(V value) {

        MapEntry<K, V> tempEntry = headEntry;
        boolean containsValue = false;

        /* Check every entry in the map until null for the key */
        while(tempEntry != null) {

            if(tempEntry.value == value) {
                containsValue = true;
                break;
            }
            tempEntry = tempEntry.next;
        }
        return containsValue;
    }

    /**
     * Removes and returns the
     * @param key of the entry to remove
     * @return a MapEntry<K, V> containing the Key Value pair removes
     */
    public MapEntry<K, V> remove(K key) {


        /* If there is only a head node (a single entry)*/
        if(headEntry.key == key) {

            headEntry = headEntry.next;
            decreaseSize();

            return headEntry;
        }

        MapEntry<K, V> tempEntry = null;
        MapEntry<K, V> entryToRemove = headEntry;

        while(entryToRemove != null) {
            if(entryToRemove.key == key) {
                tempEntry.next = entryToRemove.next;
            }
            tempEntry = entryToRemove;
            entryToRemove = entryToRemove.next;
        }
        decreaseSize();
        return tempEntry;
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