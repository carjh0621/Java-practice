import java.math.BigDecimal;

public class BigDecimalTrap {
    public static void main(String[] args) throws Exception{

        // 1.double 값을 그대로 생성자에 넣음
        BigDecimal unsafe = new BigDecimal(0.1);

        // 2.String으로 값을 넣음
        BigDecimal safe = new BigDecimal("0.1");

        System.out.println("=== 0.1을 저장했을 때 차이 ===");
        System.out.println("double 생성자: " + unsafe);
        // 0.100000000000000005551115... (오차)

        System.out.println("String 생성자: " + safe);
        //0.1
        if (unsafe.equals(safe)) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }
}
