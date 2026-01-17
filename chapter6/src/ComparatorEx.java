import java.util.Arrays;
import java.util.Comparator;
import static java.util.Comparator.*; // 정적 임포트 (코드를 짧게 쓰기 위해)

public class ComparatorEx {

    static class Person {
        String firstName;
        String lastName;
        Integer age; // null 가능성을 위해 Integer 사용

        public Person(String first, String last, Integer age) {
            this.firstName = first;
            this.lastName = last;
            this.age = age;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public Integer getAge() { return age; }

        @Override
        public String toString() { return firstName + " " + lastName + "(" + age + ")"; }
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("John", "Doe", 30),
                new Person("Jane", "Doe", 25), // 성이 같음 -> 이름으로 비교 필요
                new Person("Alice", "Smith", null), // 나이가 null
                new Person("Bob", "Brown", 40)
        };

        // 1. comparing + thenComparing (체이닝)
        // 성(LastName)으로 먼저 비교하고, 같으면 이름(FirstName)으로 비교
        Arrays.sort(people, comparing(Person::getLastName)
                .thenComparing(Person::getFirstName));

        System.out.println("1. 성, 이름 순 정렬:");
        System.out.println(Arrays.toString(people));


        // 2. comparingInt (기본형 최적화) + 커스텀 키
        // 이름의 '길이'로 정렬
        Arrays.sort(people, comparingInt(p -> p.getFirstName().length()));

        System.out.println("\n2. 이름 길이 순 정렬:");
        System.out.println(Arrays.toString(people));


        // 3. nullsFirst (Null 안전 처리)
        // 나이(Age)로 정렬하되, 나이가 없는(null) 사람을 맨 앞으로 보냄
        // naturalOrder()는 Integer의 기본 정렬 기준(오름차순)을 의미
        Arrays.sort(people, comparing(Person::getAge, nullsFirst(naturalOrder())));

        System.out.println("\n3. 나이 순 정렬 (Null 먼저):");
        System.out.println(Arrays.toString(people));
    }
}