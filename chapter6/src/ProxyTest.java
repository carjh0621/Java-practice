import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class TraceHandler implements InvocationHandler {
    private Object target; // 실제 객체 (예: Integer 500)

    public TraceHandler(Object t) {
        target = t;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // 1. 암묵적 매개변수(target) 출력
        System.out.print(target);
        // 2. 메서드 이름 출력
        System.out.print("." + m.getName() + "(");
        // 3. 인자들 출력
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");

        // 4. 실제 메서드 실행 (target 객체의 메서드 호출)
        return m.invoke(target, args);
    }
}
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        // 1~1000까지의 Integer를 프록시로 감싸서 배열에 저장
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);

            // 프록시 생성: Comparable 인터페이스를 구현하도록 함
            Object proxy = Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    new Class[] { Comparable.class },
                    handler
            );
            elements[i] = proxy;
        }

        // 찾을 키 값 생성 (랜덤)
        Integer key = (int) (Math.random() * elements.length) + 1;

        // 이진 검색 수행
        // Arrays.binarySearch는 내부적으로 elements[i].compareTo(key)를 호출함
        int result = Arrays.binarySearch(elements, key);

        // 결과 출력
        if (result >= 0) System.out.println(elements[result]);
    }
}