import java.util.Arrays;

public class ToStringTest {
    public static void main(String[] args) {
        // 1. 부모 클래스 객체 출력
        Book book = new Book("자바의 정석", "남궁성");
        System.out.println("=== 부모 클래스 출력 ===");
        System.out.println(book);
        // getClass().getName() 덕분에 "Book[...]"으로 출력됨

        // 2. 자식 클래스 객체 출력
        EBook ebook = new EBook("이펙티브 자바", "조슈아 블로크", 15.4);
        System.out.println("\n=== 자식 클래스 출력 (상속 활용) ===");
        System.out.println(ebook);
        // 부모의 toString을 재사용하여 "EBook[...][...]" 형태가 됨

        // 3. 배열 출력의 함정과 해결책
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("\n=== 배열 출력 테스트 ===");
        System.out.println("그냥 출력: " + numbers);
        System.out.println("Arrays.toString: " + Arrays.toString(numbers));
    }
}

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        // "Book"이라고 직접 쓰지 않고 getClass().getName() 사용
        // 이렇게 하면 자식 클래스(EBook)가 이 메서드를 호출했을 때 "EBook"이라고 뜸
        return getClass().getName() + "[title=" + title + ", author=" + author + "]";
    }
}

class EBook extends Book {
    private double fileSizeMB; // 전자책 용량

    public EBook(String title, String author, double fileSizeMB) {
        super(title, author);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String toString() {
        // 부모의 toString()을 호출하여 기본 정보를 가져온 뒤 내 정보를 덧붙임
        //  EBook[title=..., author=...][fileSizeMB=...]
        return super.toString() + "[fileSizeMB=" + fileSizeMB + "]";
    }
}