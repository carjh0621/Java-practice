package timer;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*; // 주의: javax.swing.Timer를 사용하기 위해 필요

public class TimerTest {
    public static void main(String[] args) {

        // 1. 리스너 객체 생성 (ActionListener를 구현한 클래스)
        var listener = new TimePrinter();

        // 2. 타이머 생성
        // 첫 번째 인자: 1000ms (1초) 간격
        // 두 번째 인자: 1초마다 호출될 리스너 객체
        var timer = new Timer(1000, listener);

        // 3. 타이머 시작
        timer.start();

        // 4. 프로그램 종료 방지
        // 타이머는 별도의 스레드(Event Dispatch Thread 등)에서 동작합니다.
        // 메인 메서드가 바로 종료되면 프로그램이 끝나버리므로,
        // 다이얼로그를 띄워 사용자가 확인을 누를 때까지 프로그램을 유지시킵니다.
        JOptionPane.showMessageDialog(null, "Quit program?");

        System.exit(0);
    }
}

// ActionListener 인터페이스를 구현하는 클래스
class TimePrinter implements ActionListener {

    // 인터페이스에 정의된 메서드 구현
    @Override
    public void actionPerformed(ActionEvent event) {
        // event.getWhen(): 이벤트 발생 시간(long, epoch milliseconds)
        // Instant.ofEpochMilli(): 사람이 읽기 쉬운 시간 객체로 변환
        System.out.println("At the tone, the time is "
                + Instant.ofEpochMilli(event.getWhen()));

        // 시스템 비프음 발생
        Toolkit.getDefaultToolkit().beep();
    }
}