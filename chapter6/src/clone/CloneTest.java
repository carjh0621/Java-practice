package clone;

public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee original = new Employee("John Q. Public", 50000);
            original.setHireDay(2000, 1, 1);

            // 복제 수행
            Employee copy = original.clone();

            // 복제본 수정
            copy.raiseSalary(10); // 연봉 인상
            copy.setHireDay(2002, 12, 31); // 날짜 변경

            // 결과 확인: 원본은 변하지 않아야 함
            System.out.println("original=" + original);
            System.out.println("copy    =" + copy);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}