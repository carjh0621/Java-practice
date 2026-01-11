import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzerTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        var squares = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++) {
            squares.add(i * i);
        }

        // ArrayList 객체 내부를 샅샅이 뒤져서 출력
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}

class ObjectAnalyzer {
    // 무한 재귀 호출(순환 참조)을 방지하기 위해 방문한 객체를 저장
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) throws ReflectiveOperationException {
        if (obj == null) return "null";

        // 이미 방문한 객체면 더 이상 파고들지 않음
        if (visited.contains(obj)) return "...";
        visited.add(obj);

        Class<?> cl = obj.getClass();

        // String은 그냥 출력
        if (cl == String.class) return (String) obj;

        // 배열인 경우 처리
        if (cl.isArray()) {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(obj, i);
                // 원시 타입이면 값 출력, 객체면 재귀 호출
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r + "}";
        }

        String r = cl.getName();
        // 현재 클래스와 모든 부모 클래스의 필드를 검사
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();

            // 모든 필드에 접근 허용 설정
            AccessibleObject.setAccessible(fields, true);

            for (Field f : fields) {
                // static 필드는 제외 (객체의 상태가 아니므로)
                if (!Modifier.isStatic(f.getModifiers())) {
                    if (!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";

                    Class<?> t = f.getType();
                    Object val = f.get(obj);

                    // 원시 타입이면 값 출력, 아니면 재귀 호출
                    if (t.isPrimitive()) r += val;
                    else r += toString(val);
                }
            }
            r += "]";
            cl = cl.getSuperclass(); // 부모 클래스로 이동
        } while (cl != null);

        return r;
    }
}