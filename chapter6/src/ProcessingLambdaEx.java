import java.util.function.IntConsumer;

public class ProcessingLambdaEx {
    public static void main(String[] args) {

        // 1. Runnable을 받는 단순 반복
        System.out.println("--- Runnable 반복 ---");
        repeat(3, () -> System.out.println("Hello!"));

        // 2. IntConsumer를 받는 인덱스 포함 반복
        System.out.println("\n--- IntConsumer 반복 (카운트다운) ---");
        // i는 repeat 메서드 내부에서 넘겨주는 0, 1, 2... 값입니다.
        repeat(5, i -> System.out.println("Countdown: " + (5 - i)));
    }

    // [직접 만든 메서드 1] Runnable 사용 (인자 없음)
    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run(); // 람다 실행
        }
    }

    // [직접 만든 메서드 2] IntConsumer 사용 (int 인자 받음)
    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i); // 람다에게 현재 인덱스 i를 전달
        }
    }
}