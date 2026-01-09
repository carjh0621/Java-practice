package reflectexample;

import java.lang.reflect.Constructor;
import java.util.Random;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // 1. 객체로부터 Class 객체 얻기
            Random generator = new Random();
            Class<?> cl1 = generator.getClass();
            System.out.println("1. Name from instance: " + cl1.getName());

            // 2. 문자열로 Class 객체 얻기 (동적 로딩)
            String className = "java.util.Random";
            Class<?> cl2 = Class.forName(className);
            System.out.println("2. Name from String: " + cl2.getName());

            // 3. 타입 리터럴(.class)로 얻기
            Class<?> cl3 = Random.class;
            System.out.println("3. Name from type literal: " + cl3.getName());

            // 4. 배열 이름 확인 (주의 사항)
            System.out.println("4. Array names:");
            System.out.println("   Double[]: " + Double[].class.getName());
            System.out.println("   int[]:    " + int[].class.getName());

            // 5. 타입 비교 (== vs instanceof)
            // Manager는 Employee를 상속받았다고 가정
            Object mgr = new Manager();
            System.out.println("5. Type Comparison:");
            System.out.println("   mgr.getClass() == reflectexample.Employee.class: " + (mgr.getClass() == Employee.class)); // false (정확한 타입 체크)
            System.out.println("   mgr instanceof reflectexample.Employee:          " + (mgr instanceof Employee));       // true (상속 관계 포함)

            // 6. 동적 객체 생성 (newInstance)
            System.out.println("6. Dynamic Instantiation:");
            // 기본 생성자가 있어야 함
            Constructor<?> cons = cl2.getConstructor();
            Object obj = cons.newInstance();
            System.out.println("   Created object: " + obj.toString());

        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Employee {}
class Manager extends Employee {}