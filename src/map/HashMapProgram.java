package map;

public class HashMapProgram {
    public static void main(String[] args) {
        SimpleHashMap<Integer, Integer> m = new SimpleHashMap<>(16);

        m.put(1, 1);
        m.put(17,17);
        System.out.println(m.put(1,2));
//        System.out.println(m.put(1, 2));
//        System.out.println(m.put(3, 3));
//        System.out.println(m.put(123, 123));
//        System.out.println(m.put(1, 10));
//        System.out.println(m.put(1, 20));
//        System.out.println(m.put(1, 30));
//        System.out.println(m.put(123, 4));


    }
}
