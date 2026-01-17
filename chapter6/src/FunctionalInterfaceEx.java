import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceEx {
    public static void main(String[] args) {
        // 1. Predicate 사용 예제 (조건 검사)
        // Predicate<T>는 T를 받아 boolean을 반환합니다.
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add(null);
        names.add("Bob");
        names.add(null);

        // removeIf 메서드는 Predicate를 파라미터로 받습니다.
        // 람다식: "e가 null이면 true(삭제해라)"
        names.removeIf(e -> e == null);
        System.out.println("Null 제거 후: " + names);

        // 2. Supplier 사용 예제 (지연 생성)
        // Supplier<T>는 인자 없이 T를 반환합니다.
        String input = null;

        // input이 null이면 Supplier가 실행되어 기본값을 만듭니다.
        // () -> "Default Value" 부분이 Supplier입니다.
        String result = Objects.requireNonNullElseGet(input, () -> generateDefaultValue());
        System.out.println("결과값: " + result);
    }

    // Supplier에서 호출할 메서드
    public static String generateDefaultValue() {
        System.out.println("기본값 생성 로직이 실행되었습니다!"); // 지연 실행 확인용
        return "Default Name";
    }
}