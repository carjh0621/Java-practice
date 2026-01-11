import java.util.*;
import java.lang.reflect.*;

public class ClassAnalyzer {
    public static void main(String[] args) {
        // 사용자로부터 분석할 클래스 이름 입력받기
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.print("분석할 클래스 이름을 입력하세요 (예: java.util.ArrayList): ");
            name = in.next();
        }

        try {
            // 1. 클래스 객체 가져오기
            Class<?> cl = Class.forName(name);

            // 2. 상속 관계 및 클래스 선언부 출력
            Class<?> superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());

            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);

            // 상속받은 클래스가 있고 Object가 아니라면 출력
            if (superCl != null && superCl != Object.class) {
                System.out.print(" extends " + superCl.getName());
            }
            System.out.println("\n{");

            // 3. 내부 구성요소 출력 (생성자, 메서드, 필드)
            printConstructors(cl);
            System.out.println();

            printMethods(cl);
            System.out.println();

            printFields(cl);
            System.out.println("}");

        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        }
    }

    /**
     * 모든 생성자 정보를 출력하는 메서드
     */
    public static void printConstructors(Class<?> cl) {
        // getDeclaredConstructors: private을 포함한 이 클래스의 모든 생성자
        Constructor<?>[] constructors = cl.getDeclaredConstructors();

        for (Constructor<?> c : constructors) {
            String name = c.getName();
            String modifiers = Modifier.toString(c.getModifiers());

            System.out.print("   "); // 들여쓰기
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            // 파라미터 타입들 출력
            Class<?>[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 모든 메서드 정보를 출력하는 메서드
     */
    public static void printMethods(Class<?> cl) {
        // getDeclaredMethods: 상속받은 건 제외하고, 이 클래스에 정의된 모든 메서드
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class<?> retType = m.getReturnType();
            String name = m.getName();
            String modifiers = Modifier.toString(m.getModifiers());

            System.out.print("   ");
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            // 리턴타입 + 메서드이름
            System.out.print(retType.getName() + " " + name + "(");

            // 파라미터 타입들 출력
            Class<?>[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 모든 필드(변수) 정보를 출력하는 메서드
     */
    public static void printFields(Class<?> cl) {
        // getDeclaredFields: private을 포함한 모든 필드
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class<?> type = f.getType();
            String name = f.getName();
            String modifiers = Modifier.toString(f.getModifiers());

            System.out.print("   ");
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}