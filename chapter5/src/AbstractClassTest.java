import java.time.LocalDate;

public class AbstractClassTest {
    public static void main(String[] args) {
        //추상 클래스 배열 생성
        Person[] people = new Person[2];

        // 다형성 활용: 부모 타입 배열에 자식 객체 담기
        // 추상 클래스 자체는 new 불가능
        people[0] = new AbstractEmployee("Harry Hacker", 50000, 1989, 10, 1);
        people[1] = new Student("Maria Morris", "Computer Science");

        //공통 메서드 호출
        // p.getDescription() 호출 시, 실제 객체(Employee, Student)의 메서드가 실행됨
        for (Person p : people) {
            System.out.println(p.getName() + ", " + p.getDescription());
        }
    }
}

//추상 클래스
abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 구현 없이 선언만 함 (자식들이 구현해야 함)
    public abstract String getDescription();
}

class AbstractEmployee extends Person {
    private double salary;
    private LocalDate hireDay;

    public AbstractEmployee(String name, double salary, int year, int month, int day) {
        super(name); // 부모 생성자 호출
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    // 추상 메서드 구현
    @Override
    public String getDescription() {
        return "an employee with a salary of $" + salary;
    }
}


class Student extends Person {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    // 추상 메서드 구현
    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }
}