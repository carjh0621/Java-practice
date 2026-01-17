import java.util.Arrays;
import java.util.List;

public class MethodReferenceEx {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");

        // 1. object::instanceMethod (인스턴스 메서드 참조)
        // x -> System.out.println(x) 와 동일
        System.out.println("--- 출력 (System.out::println) ---");
        list.forEach(System.out::println);

        // 2. Class::staticMethod (정적 메서드 참조)
        // (x, y) -> Math.max(x, y) 와 동일
        // 여기서는 직접 함수형 인터페이스에 대입해봅니다.
        // BiFunction<Double, Double, Double>과 같은 역할
        double result = merge(10.5, 20.2, Math::max);
        System.out.println("\nMax 값: " + result);

        // 3. Class::instanceMethod (클래스 이름으로 인스턴스 메서드 참조)
        // (s1, s2) -> s1.compareToIgnoreCase(s2) 와 동일
        // 첫 번째 파라미터가 메서드 호출의 주체가 됩니다.
        String[] strings = { "Banana", "apple", "Cherry" };
        Arrays.sort(strings, String::compareToIgnoreCase);

        System.out.println("\n--- 정렬 후 (String::compareToIgnoreCase) ---");
        System.out.println(Arrays.toString(strings));
    }

    // 함수형 인터페이스를 인자로 받는 유틸 메서드 (직접 정의)
    // java.util.function.BinaryOperator<T>는 (T, T) -> T 입니다.
    public static double merge(double a, double b, java.util.function.BinaryOperator<Double> func) {
        return func.apply(a, b);
    }
}