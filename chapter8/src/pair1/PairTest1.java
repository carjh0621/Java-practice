package pair1;

/**
 * 교재 Listing 8.1의 내용을 실행 가능하도록 통합한 코드입니다.
 * Pair 클래스는 제네릭을 사용하여 두 개의 객체를 저장합니다.
 */

// 1. 제네릭 클래스 Pair 정의
class Pair<T>
{
    private T first;
    private T second;

    public Pair() { first = null; second = null; }
    public Pair(T first, T second) { this.first = first; this.second = second; }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }
}

// 2. 알고리즘을 담은 유틸리티 클래스
class ArrayAlg
{
    /**
     * 문자열 배열에서 최솟값과 최댓값을 구합니다.
     * @param a 문자열 배열
     * @return 최솟값과 최댓값을 담은 Pair 객체 (배열이 null이거나 비었으면 null 반환)
     */
    public static Pair<String> minmax(String[] a)
    {
        if (a == null || a.length == 0) return null;

        String min = a[0];
        String max = a[0];

        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }

        return new Pair<>(min, max);
    }
}

// 3. 실행을 위한 메인 클래스
public class PairTest1
{
    public static void main(String[] args)
    {
        String[] words = { "Mary", "had", "a", "little", "lamb" };

        // minmax 메서드는 Pair<String>을 반환합니다.
        Pair<String> mm = ArrayAlg.minmax(words);

        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}