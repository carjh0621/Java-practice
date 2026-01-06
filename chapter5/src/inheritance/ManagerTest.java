package inheritance;

public class ManagerTest {
    public static void main(String[] args) {
        // 1. Manager 객체 생성
        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        // 2. 다형성 활용: Employee 배열에 Manager와 Employee를 섞어서 저장
        Employee[] staff = new Employee[3];

        staff[0] = boss; // Manager 객체 대입 (가능!)
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        // 3. 동적 바인딩 테스트
        for (Employee e : staff) {
            // e가 Manager면 Manager의 getSalary(), Employee면 Employee의 getSalary()가 호출됨
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }
    }
}