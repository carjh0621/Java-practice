package resources;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import javax.swing.*;

public class ResourceTest {
    public static void main(String[] args) throws IOException {
        // 기준이 될 클래스 객체를 얻음
        Class cl = ResourceTest.class;

        // 이미지 로딩 (URL 반환)
        // "about.gif"는 현재 패키지(resources) 안에 있어야 함
        URL aboutURL = cl.getResource("about.gif");
        var icon = new ImageIcon(aboutURL);

        // 텍스트 파일 로딩 1 (InputStream 반환)
        // "data/about.txt"는 현재 패키지 안의 data 폴더(resources/data)에 있어야 함
        InputStream stream = cl.getResourceAsStream("data/about.txt");
        // Java 9+ 메서드인 readAllBytes()로 내용을 한 번에 읽음
        var about = new String(stream.readAllBytes(), "UTF-8");

        //텍스트 파일 로딩 2 (절대 경로 사용)
        // "/corejava/title.txt"는 루트(CLASSPATH root)의 corejava 폴더에 있어야 함
        InputStream stream2 = cl.getResourceAsStream("/corejava/title.txt");
        var title = new String(stream2.readAllBytes(), StandardCharsets.UTF_8).strip();

        //결과 출력
        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }
}