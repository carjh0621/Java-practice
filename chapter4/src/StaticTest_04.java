// 파일명: StaticTest_04.java

public class StaticTest_04 {
    public static void main(String[] args) {
        System.out.println(">>> StaticTest_04 메인 실행 (전체 프로그램 동작)");

        // 직원 배열 생성 및 채우기
        Employee_04[] staff = new Employee_04[3];
        staff[0] = new Employee_04("Tom", 40000);
        staff[1] = new Employee_04("Dick", 60000);
        staff[2] = new Employee_04("Harry", 65000);

        // 정보 출력
        for (Employee_04 e : staff) {
            System.out.println("이름: " + e.getName() + ", ID: " + e.getId() + ", 급여: " + e.getSalary());
        }

        // 정적 메서드 호출
        int n = Employee_04.getNextId();
        System.out.println("다음 가능한 ID: " + n);
    }
}

// 같은 파일 내에 정의된 Employee 클래스
class Employee_04 {
    private static int nextId = 1;

    private String name;
    private double salary;
    private int id;

    public Employee_04(String n, double s) {
        name = n;
        salary = s;
        id = 0;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = nextId; // 다음 ID 가져오기
        nextId++;    // static 변수 증가
    }

    public static int getNextId() {
        return nextId; // 정적 필드 반환
    }

    // [단위 테스트용 메인 메서드]
    // 이 클래스만 따로 실행해서 로직이 맞는지 확인해볼 수 있음.
    // 실행 명령: java Employee_04
    public static void main(String[] args) {
        System.out.println(">>> Employee_04 단위 테스트 메인 실행");

        Employee_04 e = new Employee_04("Romeo", 50000);
        e.setId();

        System.out.println(e.getName() + " " + e.getSalary());
        System.out.println("테스트 완료.");
    }
}