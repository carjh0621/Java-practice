package com.horstmann.corejava; // 1. 패키지 선언

// 다른 패키지의 클래스 사용 시 임포트 필요
import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    // 생성자 (public이어야 다른 패키지에서 생성 가능)
    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}