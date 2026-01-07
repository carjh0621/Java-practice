public class ObjectClassTest {
    public static void main(String[] args) {
        // 1. Object 변수에 하위 클래스 객체 담기 (다형성)
        Object obj = new Employee("홍길동", 50000);

        // 2. Object 타입 상태로는 하위 클래스 메서드 못 씀
        // obj.getSalary(); //Object에는 getSalary가 없음

        // 3. 사용하려면 원래 타입으로 캐스팅해야 함
        if (obj instanceof Employee) {
            Employee e = (Employee) obj;
            System.out.println("직원 이름: " + e.getName());
        }

        // 4. 배열은 원시 타입 배열이라도 Object 취급됨
        int[] numbers = new int[10];
        Object arrayObj = numbers; // 에러 x

        System.out.println("배열도 객체라 Object 변수에 들어감: " + arrayObj.toString());
    }
}
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
}