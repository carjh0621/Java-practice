package interfaces;

// 1. Comparable 인터페이스 구현 선언 (제네릭 사용)
public class Employee implements Comparable<Employee> {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    /**
     * 급여를 기준으로 직원을 비교합니다.
     * @param other 다른 Employee 객체
     * @return 이 객체의 급여가 낮으면 음수, 같으면 0, 높으면 양수
     */
    // 2. compareTo 메서드 구현 (반드시 public이어야 함)
    @Override
    public int compareTo(Employee other) {
        // 실수형 비교이므로 Double.compare 사용 (뺄셈 지양)
        return Double.compare(salary, other.salary);
    }

    @Override
    public String toString() {
        return "name=" + name + ", salary=" + salary;
    }
}