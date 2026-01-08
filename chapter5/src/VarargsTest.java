public class VarargsTest {

    //가변 인자를 사용한 max 메서드 정의
    // double... values는 내부적으로 double[] values와 똑같음
    public static double max(double... values) {
        double largest = Double.NEGATIVE_INFINITY;
        // 배열처럼 향상된 for문 사용 가능
        for (double v : values) {
            if (v > largest) largest = v;
        }
        return largest;
    }

    // main 메서드도 String[] 대신 String... 으로 쓸 수 있음
    public static void main(String... args) {
        // 인자 3개 전달
        double m1 = max(3.1, 40.4, -5);
        System.out.println("Max(3개): " + m1);

        // 인자 1개 전달
        double m2 = max(100.0);
        System.out.println("Max(1개): " + m2);

        // 인자 0개 전달 (가능은 함, 로직에 따라 처리 필요
        System.out.println("Max(0개): " + max());

        //배열을 직접 전달
        double[] myData = { 10.5, 20.1, 9.9 };
        System.out.println("Max(배열 전달): " + max(myData));
    }
}