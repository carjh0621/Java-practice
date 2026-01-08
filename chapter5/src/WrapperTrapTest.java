import java.util.ArrayList;

public class WrapperTrapTest {
    public static void main(String[] args) {
        //오토박싱과 ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3); // 오토박싱: list.add(Integer.valueOf(3));
        list.add(1000);

        //비교와 Integer Cache (-128 ~ 127)
        Integer a = 100;
        Integer b = 100;
        System.out.println("=== 1. Integer Cache 테스트 (-128 ~ 127) ===");
        if (a == b) {
            System.out.println("100 == 100 : true (캐시된 객체라 주소 같음)");
        } else {
            System.out.println("100 == 100 : false");
        }

        Integer c = 1000;
        Integer d = 1000;
        System.out.println("\n=== 2. 범위 밖 숫자 비교 (1000) ===");
        if (c == d) {
            System.out.println("1000 == 1000 : true");
        } else {
            System.out.println("1000 == 1000 : false (새로 생성된 객체라 주소 다름!)");
        }
        System.out.println("해결책 c.equals(d): " + c.equals(d));

        //Null 언박싱
        System.out.println("\n=== 3. Null 언박싱 에러 테스트 ===");
        Integer nullInteger = null;
        try {
            // null을 int로 변환하려고 하면 터짐
            // 실제 동작: nullInteger.intValue()
            int value = nullInteger;
        } catch (NullPointerException e) {
            System.out.println("예외 발생: null을 언박싱 할 수 없습니다.");
        }

        //삼항 연산자 타입 승격
        System.out.println("\n=== 4. 삼항 연산자 타입 승격 ===");
        Integer intVal = 1;
        Double doubleVal = 2.0;
        // intVal이 double로 자동 변환되어 출력됨
        System.out.println("true ? 1 : 2.0  => " + (true ? intVal : doubleVal));

        //문자열 파싱
        String strNum = "12345";
        int parsedInt = Integer.parseInt(strNum); // int 반환
        Integer valueOfInt = Integer.valueOf(strNum); // Integer 객체 반환

        System.out.println("\n=== 5. 파싱 ===");
        System.out.println("parseInt: " + parsedInt);
        System.out.println("valueOf: " + valueOfInt);
    }
}