public class VariableScopeEx {
    public static void main(String[] args) {
        String text = "Hello Lambda"; // 지역 변수
        int repeatCount = 3;          // 지역 변수 (Effectively Final)

        // 람다식 정의
        Runnable runner = () -> {
            // 바깥 스코프의 변수(text, repeatCount)를 캡처해서 사용
            for (int i = 0; i < repeatCount; i++) {
                System.out.println(text);
            }

            // [에러 발생 예시]
            // 캡처된 변수는 내부에서 값을 변경할 수 없습니다.
            // repeatCount--; // 컴파일 에러: Variable used in lambda expression should be final or effectively final
        };

        // [에러 발생 예시]
        // 람다 정의 이후에 바깥에서 변수를 변경해도 안 됩니다.
        // repeatCount = 10; // 주석 해제 시 위쪽 람다에서 에러 발생

        runner.run(); // 실행
    }
}