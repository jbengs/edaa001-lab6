package map;

import java.util.Random;

public class HashMapProgram {
    public static void main(String[] args) {
        SimpleHashMap<Integer, Integer> m = new SimpleHashMap<>();

//        for (int i = 1; i < 100000; i++) {
//            m.put(i, i);
//            m.put(i%5, i);
//        }
        Random rand = new Random(12345);
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(100) - 50;
            m.put(n, n);
        }


//        for (int i = 0; i < 100; i++) {
//            m.put(i, i);
//        }
//        m.put(1,5);
        System.out.println(m.show());
        System.out.println("done");
        m.rehash();
        System.out.println("done2");
        System.out.println(m.show());
//        System.out.println(m.put(1, 2));
//        System.out.println(m.put(3, 3));
//        System.out.println(m.put(123, 123));
//        System.out.println(m.put(1, 10));
//        System.out.println(m.put(1, 20));
//        System.out.println(m.put(1, 30));
//        System.out.println(m.put(123, 4));


    }
}
