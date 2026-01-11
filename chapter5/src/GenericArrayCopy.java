import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericArrayCopy {

    public static void main(String[] args) {
        // 1. String 배열 테스트 (참조 타입)
        String[] b = { "Tom", "Dick", "Harry" };
        System.out.println("원본 String 배열: " + Arrays.toString(b));

        // goodCopyOf 사용 (성공)
        b = (String[]) goodCopyOf(b, 5);
        System.out.println("확장된 String 배열: " + Arrays.toString(b));

        // 2. int 배열 테스트 (원시 타입)
        int[] a = { 1, 2, 3 };
        System.out.println("\n원본 int 배열: " + Arrays.toString(a));

        // goodCopyOf는 int[]도 처리가 가능함 (매개변수가 Object이기 때문)
        a = (int[]) goodCopyOf(a, 5);
        System.out.println("확장된 int 배열: " + Arrays.toString(a));

        // 3. 실패 케이스 테스트 (ClassCastException 확인)
        System.out.println("\n[badCopyOf 테스트 시작]");
        try {
            // badCopyOf는 Object[]를 리턴하므로 String[]으로 캐스팅 불가능
            b = (String[]) badCopyOf(b, 10);
        } catch (ClassCastException e) {
            System.out.println("예외 발생! Object[]는 String[]으로 변환할 수 없습니다.");
            e.printStackTrace();
        }
    }

    /**
     * 잘못된 구현: 무조건 Object[]를 생성해서 반환함.
     * 원본이 String[]이어도 결과는 Object[]가 되어버림.
     */
    public static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    /**
     * 올바른 구현: 리플렉션을 사용하여 원본과 동일한 타입의 배열을 생성함.
     * * @param a 배열 객체 (int[] 등 원시 타입 배열도 받기 위해 Object 타입 사용)
     * @param newLength 새로운 길이
     * @return 크기가 변경된 새 배열 (원본과 같은 타입)
     */
    public static Object goodCopyOf(Object a, int newLength) {
        Class<?> cl = a.getClass();

        // 1. 배열인지 확인
        if (!cl.isArray()) return null;

        // 2. 배열의 구성 요소 타입(Component Type) 확인 (예: int, String)
        Class<?> componentType = cl.getComponentType();

        // 3. 현재 길이 확인 (리플렉션 사용)
        int length = Array.getLength(a);

        // 4. 해당 타입으로 새 배열 생성 (핵심!)
        Object newArray = Array.newInstance(componentType, newLength);

        // 5. 배열 내용 복사
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));

        return newArray;
    }
}