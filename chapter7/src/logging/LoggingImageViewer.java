/*
 * 실행을 위해 package 선언을 환경에 맞게 조정하세요.
 * 폴더 없이 실행하려면 package logging; 라인을 주석 처리하거나 지우세요.
 */
package logging;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * 이벤트를 로깅하는 이미지 뷰어 프로그램
 * @version 1.03 2015-08-20
 * @author Cay Horstmann
 */
public class LoggingImageViewer
{
    public static void main(String[] args)
    {
        // 시스템 설정 파일이 지정되지 않았을 경우, 프로그래밍 방식으로 로거를 설정합니다.
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            try
            {
                // 1. 루트 로거의 레벨을 ALL로 설정하여 모든 로그를 허용
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);

                final int LOG_ROTATION_COUNT = 10;

                // 2. 파일 핸들러 생성
                // %h: 사용자 홈 디렉토리, 0: 기본 크기 제한 없음, count: 10개 파일 회전
                var handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);

                // 3. 로거에 파일 핸들러 부착
                Logger.getLogger("com.horstmann.corejava").addHandler(handler);
            }
            catch (IOException e)
            {
                Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE,
                        "Can't create log file handler", e);
            }
        }

        // GUI 스레드 시작
        EventQueue.invokeLater(() ->
        {
            var windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);

            var frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
            frame.setVisible(true);
        });
    }
}

/**
 * 이미지를 보여주는 프레임
 */
class ImageViewerFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("com.horstmann.corejava");

    public ImageViewerFrame()
    {
        logger.entering("ImageViewerFrame", "<init>"); // 생성자 진입 로그
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // 메뉴바 설정
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        var openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        var exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });

        // 이미지를 표시할 라벨
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>"); // 생성자 종료 로그
    }

    private class FileOpenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

            // 파일 선택기 설정
            var chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            // .gif 파일만 필터링
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
            {
                public boolean accept(File f)
                {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                public String getDescription()
                {
                    return "GIF Images";
                }
            });

            // 파일 선택 대화상자 표시
            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            // 이미지가 선택되면 라벨 아이콘으로 설정
            if (r == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.fine("File open dialog canceled.");

            logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}

/**
 * 로그 레코드를 별도의 윈도우(JTextArea)에 보여주는 커스텀 핸들러
 */
class WindowHandler extends StreamHandler
{
    private JFrame frame;

    public WindowHandler()
    {
        frame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);

        // OutputStream을 재정의하여 JTextArea에 append 하도록 설정
        setOutputStream(new OutputStream()
        {
            public void write(int b)
            {
            } // 호출되지 않음

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));
            }
        });
    }

    @Override
    public void publish(LogRecord record)
    {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush(); // 중요: 버퍼링하지 않고 즉시 출력하기 위해 flush 호출
    }
}