import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorReferenceEx {

    // 테스트용 Person 클래스
    static class Person {
        String name;
        public Person(String name) { // 생성자
            this.name = name;
            System.out.println("Person 생성됨: " + name);
        }
        @Override
        public String toString() { return "Person(" + name + ")"; }
    }

    public static void main(String[] args) {
        List<String> names = List.of("Kim", "Lee", "Park");

        System.out.println("1. 스트림과 생성자 참조로 객체 리스트 만들기");
        // Person::new -> String을 받아 Person을 생성하는 Function<String, Person> 역할
        List<Person> peopleList = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println("리스트 결과: " + peopleList);

        System.out.println("\n2. 배열 생성자 참조로 배열 만들기");
        // Person[]::new -> int(길이)를 받아 Person 배열을 생성하는 IntFunction<Person[]> 역할
        // toArray는 이 레시피를 이용해 Person[] 배열을 만들어 데이터를 채워줍니다.
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println("배열 결과: " + Arrays.toString(peopleArray));
    }
}