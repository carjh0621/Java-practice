package lambda;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * 람다 표현식의 사용을 보여주는 프로그램
 */
public class LambdaTest
{
    public static void main(String[] args)
    {
        var planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune" };

        System.out.println(Arrays.toString(planets));

        // 1. 일반적인 사전 순 정렬 (Comparable 구현 사용)
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        // 2. 람다 표현식을 사용한 길이 순 정렬 (Comparator 사용)
        System.out.println("Sorted by length:");
        // 문법 포인트:
        // - 파라미터 타입 생략 (String first, String second -> first, second)
        // - 블록 {} 및 return 생략 (단일 표현식)
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        // 3. 람다 표현식을 사용한 타이머 (ActionListener 사용)
        // 문법 포인트:
        // - 단일 파라미터이므로 괄호 () 생략 (event -> ...)
        // - ActionListener 인터페이스의 actionPerformed(ActionEvent e)를 구현
        var timer = new Timer(1000, event ->
                System.out.println("The time is " + new Date()));
        timer.start();

        // 사용자가 확인을 누를 때까지 프로그램 유지
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}