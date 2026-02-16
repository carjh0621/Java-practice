package genericReflection;

import java.lang.reflect.*;
import java.util.*;

/**
 * 리플렉션을 사용하여 제네릭 클래스와 메서드의 원래 선언 형태를 복원하여 출력하는 프로그램입니다.
 */
public class GenericReflectionTest {

    public static void main(String[] args) {
        // 커맨드 라인 인자가 없으면 스캐너로 입력받음
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            try (var in = new Scanner(System.in)) {
                // 테스트해 볼 클래스 이름 입력 (예: java.util.Collections 또는 pair3.Pair)
                System.out.println("Enter class name (e.g., java.util.Collections): ");
                name = in.next();
            }
        }

        try {
            // 클래스 정보 가져오기
            Class<?> cl = Class.forName(name);

            // 1. 클래스의 제네릭 선언부 출력
            printClass(cl);

            // 2. 클래스 내의 모든 메서드의 제네릭 선언부 출력
            for (Method m : cl.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + name);
        }
    }

    // --- 클래스 선언부 분석 ---
    public static void printClass(Class<?> cl) {
        System.out.print(cl.getName()); // 모디파이어(public 등) 생략, 이름만 출력

        // 타입 파라미터 출력 (예: <T, U>)
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);

        // 제네릭 부모 클래스 정보 출력 (예: extends Object)
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }

        // 제네릭 인터페이스 정보 출력 (예: implements Comparable<T>)
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    // --- 메서드 선언부 분석 ---
    public static void printMethod(Method m) {
        String name = m.getName();

        // 모디파이어 출력 (public static 등)
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");

        // 메서드의 독자적인 타입 파라미터 출력 (예: <T extends Comparable>)
        printTypes(m.getTypeParameters(), "<", ", ", "> ", true);

        // 리턴 타입 출력
        printType(m.getGenericReturnType(), false);
        System.out.print(" ");

        // 메서드 이름 및 파라미터 출력
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    // --- Type 배열을 순회하며 포맷팅하는 헬퍼 메서드 ---
    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        // 부모가 단순히 Object인 경우는 출력 생략 (extends Object 지우기)
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class })) {
            return;
        }

        if (types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) System.out.print(sep);
            printType(types[i], isDefinition);
        }
        if (types.length > 0) System.out.print(suf);
    }

    // --- 핵심 로직: Type의 구체적 종류에 따라 캐스팅하여 분석 ---
    public static void printType(Type type, boolean isDefinition) {
        // 1. 일반 클래스인 경우 (Class)
        if (type instanceof Class t) {
            System.out.print(t.getName());
        }
        // 2. 타입 변수인 경우 (TypeVariable) - 예: T
        else if (type instanceof TypeVariable t) {
            System.out.print(t.getName());
            if (isDefinition) { // 정의부라면 상한 제한(extends)도 출력
                printTypes(t.getBounds(), " extends ", " & ", "", false);
            }
        }
        // 3. 와일드카드인 경우 (WildcardType) - 예: ? extends Comparable
        else if (type instanceof WildcardType t) {
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false); // 상한
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);   // 하한
        }
        // 4. 파라미터화된 타입인 경우 (ParameterizedType) - 예: List<String>
        else if (type instanceof ParameterizedType t) {
            Type owner = t.getOwnerType(); // 외부 클래스 (Map.Entry 같은 경우 Map)
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false); // 로 타입 출력 (List)
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false); // 실제 타입 인수 출력 (<String>)
        }
        // 5. 제네릭 배열인 경우 (GenericArrayType) - 예: T[]
        else if (type instanceof GenericArrayType t) {
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition); // 배열 요소의 타입 분석
            System.out.print("[]");
        }
    }
}