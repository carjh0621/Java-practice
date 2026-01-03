import java.util.Random;

// 파일명: InitBlockTest_07.java

public class InitBlockTest {
    public static void main(String[] args) {
        System.out.println(">>> 프로그램 시작 (클래스 로딩 전)");

        // 1. 첫 번째 객체 생성 (이때 클래스 로딩되면서 static 블록 실행됨)
        Employee_07 e1 = new Employee_07("Harry", 40000);
        System.out.println("e1 생성 완료: ID=" + e1.getId() + "\n");

        // 2. 두 번째 객체 생성 (static 블록은 다시 실행 안 됨)
        Employee_07 e2 = new Employee_07("Sally", 60000);
        System.out.println("e2 생성 완료: ID=" + e2.getId() + "\n");

        // 3. 파라미터 없는 생성자 호출
        Employee_07 e3 = new Employee_07();
        System.out.println("e3 생성 완료: ID=" + e3.getId());
    }
}

class Employee_07 {
    private static int nextId;

    private int id;
    private String name = ""; // 명시적 필드 초기화
    private double salary;

    // [정적 초기화 블록]
    // 클래스가 로딩될 때 딱 1번 실행됨. nextId를 난수로 초기화.
    static {
        System.out.println("[Static Block] 클래스 로딩 중... nextId 초기화");
        Random generator = new Random();
        nextId = generator.nextInt(1000); // 0 ~ 999 사이 난수
    }

    // [인스턴스 초기화 블록]
    // 객체 생성 때마다 생성자보다 '먼저' 실행됨.
    {
        System.out.println("[Object Block] 객체 초기화 블록 실행 (ID 할당)");
        id = nextId;
        nextId++;
    }

    // [생성자 1]
    public Employee_07(String n, double s) {
        System.out.println("[Constructor] (String, double) 생성자 실행");
        name = n;
        salary = s;
    }

    // [생성자 2]
    public Employee_07() {
        System.out.println("[Constructor] No-Arg 생성자 실행");
        name = "Unknown";
        salary = 0;
    }

    public int getId() {
        return id;
    }
}