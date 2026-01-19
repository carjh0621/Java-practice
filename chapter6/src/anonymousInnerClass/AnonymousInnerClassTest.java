package anonymousInnerClass;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

/**
 * This program demonstrates anonymous inner classes.
 * @version 1.12 2017-12-14
 * @author Cay Horstmann
 */
public class AnonymousInnerClassTest
{
    public static void main(String[] args)
    {
        var clock = new TalkingClock();
        clock.start(1000, true);

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
    /**
     * Starts the clock.
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public void start(int interval, boolean beep)
    {
        // [핵심] 익명 내부 클래스 사용
        // ActionListener 인터페이스를 구현하는 이름 없는 클래스의 객체를 생성하여 listener 변수에 담음
        var listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("At the tone, the time is "
                        + Instant.ofEpochMilli(event.getWhen()));

                // 여기서도 외부 메서드의 매개변수 beep (effectively final)에 접근 가능
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }; // 세미콜론 필수 (구문의 끝)

        var timer = new Timer(interval, listener);
        timer.start();
    }
}