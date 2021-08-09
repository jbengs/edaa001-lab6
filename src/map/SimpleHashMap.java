package map;

import java.util.ArrayList;

public class SimpleHashMap<K, V> implements Map<K, V>{
    private Entry<K, V>[] root;
    private static double loadFactor;

    /** Constructs an empty hashmap with the default initial capacity (16)
     and the default load factor (0.75). */
    public SimpleHashMap() {
        root = (Entry<K, V>[]) new Entry[16];
        loadFactor = 0.75;

    }

    /** Constructs an empty hashmap with the specified initial capacity
     and the default load factor (0.75). */
    public SimpleHashMap(int capacity) {
        root = (Entry<K, V>[]) new Entry[capacity];
        loadFactor = 0.75;
    }

    //** Objects to store using the HashTable */
    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        @Override
        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    @Override
    public V get(Object key) {
            K tempKey = (K) key;
            if (find(tempKey) != null) {
                return find(tempKey).getValue();
                }
      return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V put(K key, V value) {
        if (size() / root.length > loadFactor) {
            rehash();
        }
        if (find(key) != null) {
           return find(key).setValue(value);
        } else { //add first in the list, because it is the most efficient
            int index = index(key);
            if (root[index] == null) {
                root[index] = new Entry<>(key, value);
            } else {
                Entry<K, V> temp = root[index];
                root[index] = new Entry<>(key, value);
                root[index].next = temp;
            }
        }
        return null;
    }

    @Override
    public V remove(Object obj) {
            K key = (K) obj;
            if (find(key) != null) {
                int index = index(key);
                if (root[index].getKey().equals(key)) { //edge case
                    V value = root[index].getValue();
                    root[index] = root[index].next;
                    return value;
                }
                Entry<K, V> temp1 = root[index].next;
                Entry<K, V> temp2 = root[index];
                do {
                    if (temp1.getKey().equals(obj)) {
                        V value = temp1.getValue();
                        temp2.next = temp1.next;
                        return value;
                    }
                    temp2 = temp1;
                    temp1 = temp1.next;
                } while (temp1 != null);
            }
        return null;
    }

    @Override
    public int size() {
        int n = 0;
        for (int i = 0; i < root.length; i++) {
            if (root[i] != null) {
                n++;
                Entry<K, V> temp = root[i];
                while (temp.next != null) {
                    n++;
                    temp = temp.next;
                }
            }
        }
        return n;
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < root.length; i++) {
            if (root[i] != null) {
                sb.append(i + ": ");
                    Entry<K, V> n = root[i];
                    do {
                        sb.append(n.toString() + " ");
                        n = n.next;
                    } while (n != null);
                sb.append("\n");
                }
            }
        return sb.toString();
    }

    /**
     * Checks if this Hash Table contains the key. Returns the Entry<K, V> object containing the key,
     * or null if the key does not exist
     * @param key the key to be found.
     * @return the Entry<K, V> object containing the key
     */

    private Entry<K, V> find(K key) {
        int index = index(key);
        if (root[index] != null) {
            Entry<K, V> temp = root[index];
           do {
               if (temp.getKey().equals(key)) {
                   return temp;
               }
               temp = temp.next;
           } while (temp != null);
        }
        return null;
    }

    /**
     * Calculates the index in the hash table, using the hashcode() method from the provided key,
     * modulus length of hash table
     * @param key
     * @return the index of placement in the hash table
     */
    private int index(K key) {
        int i = key.hashCode();
        if (i < 0) {
            return -1 * (i % root.length);
        }
        return i % root.length;
    }

    private void rehash() {
        Entry<K, V>[] vector = (Entry<K, V>[]) new Entry[root.length * 2];
        ArrayList<Entry<K, V>> list = new ArrayList<>();
        for (Entry<K, V> n : root) {
            if (n != null) {
                do {
                    list.add(n);
                    n = n.next;
                } while (n != null);
            }
        }
        root = vector;
        for (Entry<K, V> n : list) {
            put(n.getKey(), n.getValue());
        }
    }
}
