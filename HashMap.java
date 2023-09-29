//TODO Replace with my own linked list implementation
import java.util.LinkedList;

class MapEntry<K, V> {
    public K key;
    public V value;
    public MapEntry<K, V> next;

    /**
     * Constructs a MapEntry with the specified key and value.
     *
     * @param key   The key of the entry.
     * @param value The value associated with the key.
     */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    //TODO Use my own Linked List
    private LinkedList<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * Constructs an empty HashMap with the initial capacity.
     */
    public HashMap() {
        this.buckets = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return The number of key-value mappings.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or
     * null if there was no mapping for the key.
     */
    public V put(K key, V value) {
        /*
            Get the Hash Index/ Hash
            Code for this element
        */
        int index = getIndex(key);

        /* Checks to replace old value at key */
        for (MapEntry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        /* Add item to the bucket, and increase the size */
        buckets[index].add(new MapEntry<>(key, value));
        this.increaseSize();

        /*
            Check if the ratio of size to
            capacity exceeds/ equals load factor
        */
        if ((double) size / buckets.length >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or
     * null if this map contains no mapping for the key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value to which the specified key is mapped, or
     * null if there is no mapping for the key.
     */
    public V get(K key) {
        /* Find gthe index of the key we are checking */
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (MapEntry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key The key whose mapping is to be removed from the map.
     * @return The previous value associated with the specified key, or null if there was no mapping for the key.
     */
    public V remove(K key) {
        int index = this.getIndex(key);
        if (this.buckets[index] != null) {
            for (MapEntry<K, V> entry : this.buckets[index]) {
                if (entry.key.equals(key)) {
                    V removedValue = entry.value;
                    this.buckets[index].remove(entry);
                    this.decreaseSize();
                    return removedValue;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key The key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key, false otherwise.
     */
    public boolean containsKey(K key) {
        int index = this.getIndex(key);
        if (this.buckets[index] != null) {
            for (MapEntry<K, V> entry : this.buckets[index]) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if this map contains a mapping for the specified value.
     *
     * @param value The value whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified value, false otherwise.
     */
    public boolean containsValue(V value) {
        for (LinkedList<MapEntry<K, V>> bucket : this.buckets) {
            if (bucket != null) {
                for (MapEntry<K, V> entry : bucket) {
                    if (entry.value.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Increases the size of the hash map by 1.
     */
    public void increaseSize() {
        this.size++;
    }

    /**
     * Decreases the size of the hash map by 1.
     */
    public void decreaseSize() {
        this.size--;
    }

    /**
     * Returns a string representation of this map.
     *
     * @return A string representation of this map.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (LinkedList<MapEntry<K, V>> bucket : this.buckets) {
            if (bucket != null) {
                for (MapEntry<K, V> entry : bucket) {
                    result.append(entry.key).append("=").append(entry.value).append(", ");
                }
            }
        }

        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        result.append("}");
        return result.toString();
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % buckets.length;
    }

    private void resize() {
        int newCapacity = this.buckets.length * 2;
        LinkedList<MapEntry<K, V>>[] newBuckets = new LinkedList[newCapacity];

        for (LinkedList<MapEntry<K, V>> bucket : this.buckets) {
            if (bucket != null) {
                for (MapEntry<K, V> entry : bucket) {
                    int index = this.getIndex(entry.key, newCapacity);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = new LinkedList<>();
                    }
                    newBuckets[index].add(entry);
                }
            }
        }

        buckets = newBuckets;
    }

    private int getIndex(K key, int capacity) {
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }
}

class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        System.out.println("Size: " + hashMap.size());
        System.out.println("Value for 'two': " + hashMap.get("two"));

        hashMap.remove("two");

        System.out.println("Size after removal: " + hashMap.size());
        System.out.println("Contains key 'two': " + hashMap.containsKey("two"));

        System.out.println("HashMap Contents: " + hashMap);
    }
}
