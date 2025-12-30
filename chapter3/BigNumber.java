import java.math.BigInteger;

public class BigNumber {
    public static void main(String[] args) {
        BigInteger result = BigInteger.ONE;

        for (int i=1; i<=100; i++){
            BigInteger value = BigInteger.valueOf(i); //일반 int/long 을 BigInteger로 변환
            result = result.multiply(value); //연산자 대신 method
        }

        System.out.print("100 팩토리얼 결과: ");
        System.out.println(result);

        BigInteger bigNum = new BigInteger("222232244629420445529739893461909967206666939096499990979600");
        System.out.println("문자열로 만든 큰 수: "+ bigNum);
    }
}
