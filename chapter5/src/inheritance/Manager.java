package inheritance;

public class Manager extends Employee {
    private double bonus;

    // 생성자: 부모 생성자 호출이 필수
    public Manager(String name, double salary, int year, int month, int day) {
        // 부모 클래스의 생성자를 가장 먼저 호출해야 함
        super(name, salary, year, month, day);
        bonus = 0;
    }

    // 메서드 오버라이딩 (재정의)
    @Override
    public double getSalary() {
        // 부모의 원래 월급 + 보너스
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }
}