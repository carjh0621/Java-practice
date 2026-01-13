package comparator;

import java.util.*;

public class ComparatorTest {
    public static void main(String[] args) {
        String[] friends = { "Peter", "Paul", "Mary", "Alexandra", "Sue" };

        // 1. 기본 정렬 (Comparable: 사전순)
        Arrays.sort(friends);
        System.out.println("사전순 정렬: " + Arrays.toString(friends));
        // 출력: [Alexandra, Mary, Paul, Peter, Sue]

        // 2. Comparator 사용 정렬 (길이순)
        // 비교를 담당할 객체(심판)를 생성해서 넘겨줌
        Arrays.sort(friends, new LengthComparator());
        System.out.println("길이순 정렬: " + Arrays.toString(friends));
        // 출력: [Sue, Paul, Mary, Peter, Alexandra]
        // (Paul, Mary는 길이가 같으므로 원래 순서 유지 혹은 알고리즘에 따라 달라짐)
    }
}

// 문자열의 길이를 비교하는 외부 비교자 클래스
class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String first, String second) {
        // 길이의 차이를 반환
        // first가 더 길면 양수 -> first가 뒤로 감 (오름차순)
        return first.length() - second.length();
    }
}