import java.util.Objects;


public class LooseEqualityTest {
    public static void main(String[] args) {
        LooseItem item = new LooseItem(1001, "노트북");
        LooseDiscountItem discountItem = new LooseDiscountItem(1001, "노트북", 0.1);

        System.out.println("=== 전략 2: instanceof 사용 (유연한 비교) ===");

        // 1. Item vs DiscountItem 비교
        // ID가 1001로 같으므로 둘은 같은 아이템으로 취급됨 (true)
        System.out.println("item.equals(discountItem): " + item.equals(discountItem));
        System.out.println("discountItem.equals(item): " + discountItem.equals(item));

        // 2. ID가 다른 경우
        LooseItem otherItem = new LooseItem(1002, "마우스");
        System.out.println("item.equals(otherItem): " + item.equals(otherItem));
    }
}

// 부모 클래스
class LooseItem {
    protected long id;
    protected String description;

    public LooseItem(long id, String description) {
        this.id = id;
        this.description = description;
    }

    // 핵심: final로 막아서 자식이 equals 규칙을 못 바꾸게 함
    @Override
    public final boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;

        // instanceof 사용 (자식 클래스도 허용)
        if (!(otherObject instanceof LooseItem other)) return false;

        // 오직 ID만 비교
        return id == other.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

// 자식 클래스
class LooseDiscountItem extends LooseItem {
    private double discountRate;

    public LooseDiscountItem(long id, String description, double discountRate) {
        super(id, description);
        this.discountRate = discountRate;
    }

    // equals 오버라이딩 불가 (final 때문) -> 부모의 로직(ID 비교)을 그대로 따름
}