// default package라 패키지 선언 없음

import com.horstmann.corejava.Employee; // 2. 사용할 클래스 임포트
import static java.lang.System.out;     // 3. Static import 예시

public class PackageTest {
    public static void main(String[] args) {
        // Employee 클래스는 다른 패키지에 있지만 import해서 사용 가능
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        harry.raiseSalary(5);

        // System.out 대신 out 사용 (static import 덕분)
        out.println("name=" + harry.getName() + ", salary=" + harry.getSalary());
    }
}