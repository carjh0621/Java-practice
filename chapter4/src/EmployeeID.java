public class EmployeeID {
    public static void main(String[] args) {
        // 객체 생성 전에도 static 필드는 이미 존재함 (접근 가능)
        System.out.println("첫 입사자 예상 ID: " + Employee1.getNextId());

        // 첫 번째 직원 생성 (ID: 1 할당됨, nextId는 2가 됨)
        Employee1 harry = new Employee1("Harry");
        System.out.println(harry.getName() + "의 ID: " + harry.getId());

        // 두 번째 직원 생성 (ID: 2 할당됨, nextId는 3이 됨)
        Employee1 sally = new Employee1("Sally");
        System.out.println(sally.getName() + "의 ID: " + sally.getId());

        // 세 번째 직원 생성 (ID: 3 할당됨, nextId는 4가 됨)
        Employee1 bob = new Employee1("Bob");
        System.out.println(bob.getName() + "의 ID: " + bob.getId());

        // 결과 확인: 공유 변수 nextId는 계속 증가했음
        System.out.println("다음 입사자 예상 ID: " + Employee1.getNextId());
    }
}

class Employee1 {
    // [static 필드] 클래스 당 1개만 존재, 모든 인스턴스가 공유함
    private static int nextId = 1;

    // [인스턴스 필드] 각 객체마다 별도로 존재함
    private int id;
    private String name;

    public Employee1(String n) {
        name = n;
        id = nextId; // 공유 변수 값을 현재 객체의 id로 설정
        nextId++;    // 다음 객체를 위해 공유 변수 값 증가
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // static 필드에 접근하기 위한 static 메서드
    public static int getNextId() {
        return nextId;
    }
}