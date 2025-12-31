import java.util.ArrayList;

public class VarDemo {
    // 필드(인스턴스 변수)에는 var 사용 불가
    // var usage = "Not Allowed Here"; 

    // 메서드 파라미터에도 사용 불가
    // public void test(var x) { ... } 

    public static void main(String[] args) {

        // 긴 클래스명 줄일 때 아주 유용함 (Best Case)
        // ArrayList<String> list = new ArrayList<>();
        var list = new ArrayList<String>();

        // 객체 생성
        var harry = new Employeee("Harry", 50000);


        var i = 10;     // int로 추론됨
        var d = 10.0;   // double로 추론됨
        // var n = null; null만 있으면 무슨 타입인지 모름

        System.out.println("list의 타입: " + list.getClass().getName());
        System.out.println("harry의 타입: " + harry.getClass().getName());
    }
}

// 간단한 Employee 클래스
class Employeee {
    String name;
    double salary;
    public Employeee(String n, double s) { name = n; salary = s; }
}