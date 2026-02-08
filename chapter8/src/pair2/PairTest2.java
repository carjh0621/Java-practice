package pair2;

import java.time.*;

/**
 * 교재 Listing 8.2의 내용을 실행 가능하도록 통합한 코드입니다.
 * - Pair 클래스 (8.2절 내용 포함)
 * - ArrayAlg 클래스 (minmax 메서드에 <T extends Comparable> 적용)
 * - PairTest2 메인 클래스
 */

// 1. Pair 클래스 (제네릭 컨테이너)
class Pair<T> {
    private T first;
    private T second;

    public Pair() { first = null; second = null; }
    public Pair(T first, T second) { this.first = first; this.second = second; }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }
}

// 2. 알고리즘 클래스
class ArrayAlg {
    /**
     * T 타입의 배열에서 최솟값과 최댓값을 구합니다.
     * 단, T는 반드시 Comparable 인터페이스를 구현해야 합니다.
     * * @param a T 타입의 객체 배열
     * @return min, max 값을 가진 Pair 객체 (배열이 null이거나 비었으면 null)
     */
    // 핵심: <T extends Comparable>을 사용하여 T가 비교 가능한 타입임을 보장
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;

        T min = a[0];
        T max = a[0];

        for (int i = 1; i < a.length; i++) {
            // T가 Comparable을 상속(구현)했으므로 compareTo 메서드 호출 가능
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }

        return new Pair<>(min, max);
    }
}

// 3. 메인 실행 클래스
public class PairTest2 {
    public static void main(String[] args) {
        // LocalDate는 ChronoLocalDate를 통해 Comparable을 구현하고 있음
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),  // Grace Hopper
                LocalDate.of(1815, 12, 10), // Ada Lovelace
                LocalDate.of(1903, 12, 3),  // John von Neumann
                LocalDate.of(1910, 6, 22),  // Konrad Zuse
        };

        // LocalDate 배열을 넘겨서 min, max를 구함
        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);

        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}