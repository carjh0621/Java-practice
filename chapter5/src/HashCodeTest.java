import java.util.Objects;

public class HashCodeTest {
    public static void main(String[] args) {
        // 1. String과 StringBuilder의 차이
        String s = "Ok";
        StringBuilder sb = new StringBuilder(s);

        String t = new String("Ok");
        StringBuilder tb = new StringBuilder(t);

        System.out.println("=== String vs StringBuilder 해시코드 차이 ===");
        // String은 내용 기반이라 해시코드가 같음
        System.out.println("String s hash: " + s.hashCode());
        System.out.println("String t hash: " + t.hashCode());

        // StringBuilder는 주소 기반이라 해시코드가 다름
        System.out.println("StringBuilder sb hash: " + sb.hashCode());
        System.out.println("StringBuilder tb hash: " + tb.hashCode());
        System.out.println("------------------------------------------");

        // 2. 제대로 구현한 객체 테스트
        SmartPhone p1 = new SmartPhone("Galaxy S24", 1000);
        SmartPhone p2 = new SmartPhone("Galaxy S24", 1000); // p1과 내용은 같음
        SmartPhone p3 = new SmartPhone("iPhone 15", 1200);

        System.out.println("=== SmartPhone(직접 구현) 테스트 ===");
        // equals가 true면 hashCode도 같아야 한다!
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1 hash: " + p1.hashCode());
        System.out.println("p2 hash: " + p2.hashCode());

        if (p1.equals(p2) && p1.hashCode() == p2.hashCode()) {
            System.out.println("-> 검증 성공: equals가 true이고 해시코드도 같습니다.");
        } else {
            System.out.println("-> 검증 실패: 뭔가 잘못됨.");
        }

        System.out.println("p3 hash: " + p3.hashCode());
    }
}

class SmartPhone {
    private String model;
    private double price;

    public SmartPhone(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;

        SmartPhone other = (SmartPhone) otherObject;

        // 모델명과 가격이 같으면 같은 폰으로 취급
        return Double.compare(price, other.price) == 0 &&
                Objects.equals(model, other.model);
    }

    @Override
    public int hashCode() {
        //equals에서 사용한 필드(model, price)를 그대로 사용
        // Objects.hash()가 가변 인자를 받아서 알아서 해시코드 조합
        return Objects.hash(model, price);


    }
}