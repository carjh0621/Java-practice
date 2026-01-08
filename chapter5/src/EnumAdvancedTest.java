import java.util.Scanner;

public class EnumAdvancedTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //values()를 이용한 메뉴판 출력
        System.out.println("=== 카페 메뉴판 ===");
        for (CoffeeMenu menu : CoffeeMenu.values()) {
            System.out.printf("[%d] %s (%s) : %d원\n",
                    menu.ordinal(), menu, menu.getKoreanName(), menu.getPrice());
        }

        System.out.println("\n-----------------------------");
        System.out.print("주문하실 커피 이름을 영어로 입력하세요 (예: AMERICANO): ");
        String input = scanner.next().toUpperCase(); // 대소문자 무시를 위해 대문자 변환

        try {
            //valueOf() 문자열 -> Enum 객체 변환
            CoffeeMenu selectedMenu = CoffeeMenu.valueOf(input);

            System.out.println("주문 확인: " + selectedMenu.getKoreanName());

            //Enum 메서드 활용
            int originalPrice = selectedMenu.getPrice();
            int discountedPrice = selectedMenu.calculateDiscountPrice(10); // 10% 할인

            System.out.println("원가: " + originalPrice + "원");
            System.out.println("회원 할인가(10%): " + discountedPrice + "원");

            // 비교 (equals 안 써도 됨)
            if (selectedMenu == CoffeeMenu.ESPRESSO) {
                System.out.println("팁: 에스프레소는 아주 씁니다.");
            }

        } catch (IllegalArgumentException e) {
            // valueOf에 없는 이름 넣으면 예외
            System.out.println("죄송합니다. 그런 메뉴는 없습니다.");
        }
    }
}


enum CoffeeMenu {
    //상수 정의 (생성자 호출)
    AMERICANO("아메리카노", 4500),
    LATTE("카페라떼", 5000),
    ESPRESSO("에스프레소", 4000),
    CAPPUCCINO("카푸치노", 5500);

    //필드
    private final String koreanName;
    private final int price;

    //생성자
    CoffeeMenu(String koreanName, int price) {
        this.koreanName = koreanName;
        this.price = price;
    }

    //Getter
    public String getKoreanName() {
        return koreanName;
    }

    public int getPrice() {
        return price;
    }
    //
    public int calculateDiscountPrice(int percent) {
        return price * (100 - percent) / 100;
    }
}