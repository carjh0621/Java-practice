package interfaces;

import java.util.*;

public class EmployeeSortTest {
    public static void main(String[] args) {
        var staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        // Arrays.sort는 내부적으로 요소들의 compareTo를 호출하여 정렬함
        // 요소들이 Comparable을 구현하지 않았다면 ClassCastException 발생
        Arrays.sort(staff);

        // 정렬 결과 출력
        for (Employee e : staff) {
            System.out.println(e); // toString() 호출
        }
    }
}