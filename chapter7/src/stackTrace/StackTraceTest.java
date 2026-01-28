/*
 * 실행을 위해 패키지 선언을 주석 처리하거나,
 * stackTrace 폴더 안에 파일을 위치시키세요.
 */
package stackTrace;

import java.util.*;
import java.lang.StackWalker;

/**
 * 재귀 메서드 호출의 스택 추적(Trace) 기능을 보여주는 프로그램
 * @version 1.10 2017-12-14
 * @author Cay Horstmann
 */
public class StackTraceTest
{
    /**
     * 숫자의 팩토리얼(factorial)을 계산합니다.
     * @param n 음이 아닌 정수
     * @return n! = 1 * 2 * . . . * n
     */
    public static int factorial(int n)
    {
        System.out.println("factorial(" + n + "):");

        // Java 9 StackWalker를 사용하여 현재 스택 프레임 출력
        StackWalker walker = StackWalker.getInstance();
        walker.forEach(System.out::println);

        int r;
        if (n <= 1) r = 1;
        else r = n * factorial(n - 1);

        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args)
    {
        try (var in = new Scanner(System.in))
        {
            System.out.print("Enter n: ");
            int n = in.nextInt();
            factorial(n);
        }
    }
}