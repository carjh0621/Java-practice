package innerClass;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

/**
 * This program demonstrates the use of inner classes.
 * @version 1.11 2017-12-14
 * @author Cay Horstmann
 */
public class InnerClassTest
{
    public static void main(String[] args)
    {
        // 1초 간격, 비프음 켜짐(true)으로 시계 생성
        var clock = new TalkingClock(1000, true);
        clock.start();

        // 사용자가 확인을 누를 때까지 프로그램 유지
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
    private int interval;
    private boolean beep;

    /**
     * Constructs a talking clock
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public TalkingClock(int interval, boolean beep)
    {
        this.interval = interval;
        this.beep = beep;
    }

    /**
     * Starts the clock.
     */
    public void start()
    {
        // 여기서 TimePrinter 객체가 생성됩니다.
        // 컴파일러는 자동으로 이 객체에 'this'(현재 TalkingClock 객체)에 대한 참조를 넘깁니다.
        var listener = new TimePrinter();
        var timer = new Timer(interval, listener);
        timer.start();
    }

    // 내부 클래스 (Inner Class)
    // 'public'으로 선언되어 있지만, 'private'으로 선언하면
    // TalkingClock 외부에서는 이 클래스의 존재조차 모르게 할 수 있습니다.
    public class TimePrinter implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("At the tone, the time is "
                    + Instant.ofEpochMilli(event.getWhen()));

            // [핵심] 외부 클래스(TalkingClock)의 private 필드인 beep에 직접 접근
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}