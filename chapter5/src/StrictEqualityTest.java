import java.util.Objects;


public class StrictEqualityTest {
    public static void main(String[] args) {
        StrictEmployee alice = new StrictEmployee("Alice", 50000);
        StrictManager aliceMgr = new StrictManager("Alice", 50000, 0);

        System.out.println("=== getClass() 사용 (엄격한 비교) ===");

        // 1. reflectexample.Employee vs reflectexample.Manager 비교
        // 내용은 같지만 클래스가 다르므로 false가 나와야 정상 (대칭성 확보)
        System.out.println("alice.equals(aliceMgr): " + alice.equals(aliceMgr));
        System.out.println("aliceMgr.equals(alice): " + aliceMgr.equals(alice));

        // 2. 같은 Manager끼리 비교
        StrictManager boss1 = new StrictManager("Boss", 80000, 5000);
        StrictManager boss2 = new StrictManager("Boss", 80000, 5000);
        System.out.println("boss1.equals(boss2): " + boss1.equals(boss2));
    }
}

// 부모 클래스
class StrictEmployee {
    private String name;
    private double salary;

    public StrictEmployee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;

        //클래스 타입이 정확히 일치해야 함
        if (getClass() != otherObject.getClass()) return false;

        StrictEmployee other = (StrictEmployee) otherObject;
        return Double.compare(salary, other.salary) == 0
                && Objects.equals(name, other.name); // 객체 비교
    }
    @Override
    public int hashCode() {
        return Objects.hash(salary,name);
    }
}

// 자식 클래스
class StrictManager extends StrictEmployee {
    private double bonus;

    public StrictManager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object otherObject) {
        // 부모 쪽 필드(name, salary) 검사 + 클래스 타입 검사
        if (!super.equals(otherObject)) return false;

        StrictManager other = (StrictManager) otherObject;
        return Double.compare(bonus, other.bonus) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),bonus);
    }
}