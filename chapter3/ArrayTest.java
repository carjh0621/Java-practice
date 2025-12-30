import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};

        // 1. 그냥 대입 (참조 복사)
        int[] b = a;
        b[0] = 999;

        System.out.println("=== 그냥 대입 (b = a) ===");
        System.out.println("a[0]: " + a[0]); // 999


        // 2. Arrays.copyOf
        int[] original = {1, 2, 3};
        int[] copy = Arrays.copyOf(original, original.length);

        copy[0] = 888;
        System.out.println("\n=== Arrays.copyOf ===");
        System.out.println("original[0]: " + original[0]);
        System.out.println("copy[0]: "     + copy[0]);     // 888
    }
}