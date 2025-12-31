import java.time.LocalDate;

// 1. 실제 데이터를 담고 기능을 수행하는 클래스
// public이 없으므로 이 파일 안에서만 주로 쓰임
class Employee {
    // 인스턴스 필드 (데이터)
    private String name;
    private double salary;
    private LocalDate hireDay;

    // 생성자 (Constructor)
    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    // 메서드 (기능)
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}

// 2. 메인 메서드를 포함한 실행용 클래스
// 파일 이름과 동일해야 하므로 public
public class EmployeeTest {
    public static void main(String[] args) {
        // Employee 객체 배열 생성 (참조변수 배열만 생성됨, 실제 객체는 아직)
        Employee[] staff = new Employee[3];

        // 실제 객체 생성 후 배열에 연결
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        // 월급 인상 전 직원 정보
        for (Employee e : staff) {
            System.out.println("name=" + e.getName()
                    + ", salary=" + e.getSalary()
                    + ", hireDay=" + e.getHireDay());
        }

        System.out.println("========================");

        // 모든 직원의 월급 5% 인상 (Mutator 메서드 호출)
        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        // 정보 출력 (Accessor 메서드 호출)
        for (Employee e : staff) {
            System.out.println("name=" + e.getName()
                    + ", salary=" + e.getSalary()
                    + ", hireDay=" + e.getHireDay());
        }
    }
}