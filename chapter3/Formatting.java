import java.util.*;

public class Formatting {
    public static void main(String[] args) {
        double x = 10000.0 / 3.0;
        System.out.print(x);
        //print: 전달한 값을 그대로 문자열로 변환해서 출력
        // 줄바꿈 x
        //3333.3333333333335

        System.out.printf("%8.2f",x);
        //formatting 가능
        //줄바꿈 x
        //3333.33
    }
}
