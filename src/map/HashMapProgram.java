package map;

import java.util.Random;

public class HashMapProgram {
    public static void main(String[] args) {
        SimpleHashMap<Integer, Integer> m = new SimpleHashMap<>();

        Random rand = new Random(12345);
        for (int i = 0; i < 100000; i++) {
            int n = rand.nextInt(100) - 50;
            m.put(n, n);
        }
        System.out.println(m.show());
    }
}
