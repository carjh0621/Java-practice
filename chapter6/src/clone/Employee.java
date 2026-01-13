package clone;

import java.util.Date;
import java.util.GregorianCalendar;

// 1. Cloneable 인터페이스 구현 (필수)
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay; // 가변(Mutable) 객체 필드

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date(); // 현재 시간
    }

    // 2. clone 메서드를 public으로 오버라이드
    // 반환 타입을 Object가 아닌 Employee로 지정 (공변 반환 타입)
    @Override
    public Employee clone() throws CloneNotSupportedException {
        // A. 먼저 얕은 복사를 수행 (Object.clone 호출)
        // name, salary, hireDay(주소)가 복사됨
        Employee cloned = (Employee) super.clone();

        // B. 가변 필드에 대해 깊은 복사 수행
        // hireDay를 새로 복제하여 갈아끼움.
        // 이걸 안 하면 원본과 복제본이 같은 Date 객체를 공유하게 됨.
        cloned.hireDay = (Date) this.hireDay.clone();

        return cloned;
    }

    /**
     * 날짜를 변경하는 메서드 (가변성 테스트용)
     */
    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }
}