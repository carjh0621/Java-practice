/**
 * 간단한 수학 연산을 수행하는 클래스임.
 * <p>
 * 이 클래스는 기본적인 덧셈과 나눗셈 기능을 제공함.
 * 사용법 예시:
 * <pre>
 * {@code
 * Calculator calc = new Calculator();
 * int result = calc.add(10, 20);
 * }
 * </pre>
 *
 * @author Hwang JaeWon
 * @version 1.0
 * @since 2026-01-03
 */
public class Calculator {

    /**
     * 계산기의 기본 정밀도 상수.
     */
    public static final double PRECISION = 0.001;

    /**
     * 두 정수를 더함.
     *
     * @param a 첫 번째 정수
     * @param b 두 번째 정수
     * @return 두 정수의 합 ({@code a + b})
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 첫 번째 정수를 두 번째 정수로 나눔.
     * 0으로 나누는 경우에 대한 처리는 되어있지 않음.
     *
     * @param numerator 분자
     * @param denominator 분모
     * @return 나눗셈 결과
     * @throws ArithmeticException 분모가 0일 때 발생할 수 있음
     */
    public double divide(int numerator, int denominator) {
        return (double) numerator / denominator;
    }
}