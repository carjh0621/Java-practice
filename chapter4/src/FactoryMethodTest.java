import java.text.NumberFormat;
import java.util.Locale;

public class FactoryMethodTest {
    public static void main(String[] args) {
        double x = 0.1;

        // 통화 포맷 인스턴스 생성 (한국 원화 기준)
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);

        // 퍼센트 포맷 인스턴스 생성
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();

        System.out.println("통화 형식: " + currencyFormatter.format(x * 10000));
        System.out.println("퍼센트 형식: " + percentFormatter.format(x));

        // 실제 반환된 객체의 클래스 확인 (상속 관련 내용)
        // NumberFormat 타입 변수에 받았지만, 실제로는 자식 클래스인 DecimalFormat이 들어있음
        System.out.println("\n--- 실제 클래스 타입 확인 ---");
        System.out.println("currencyFormatter의 실제 클래스: " + currencyFormatter.getClass().getName());
        System.out.println("percentFormatter의 실제 클래스: " + percentFormatter.getClass().getName());

        // 결론: 팩토리 메서드는 내부적으로 최적화된 자식 클래스 객체를 반환할 수 있음.
    }
}