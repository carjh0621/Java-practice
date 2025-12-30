import java.util.Scanner;
import java.util.Arrays;

public class LotteryDrawing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("몇 개의 숫자를 뽑을까요? (k): ");
        int k = in.nextInt();

        System.out.print("전체 숫자는 몇 개입니까? (n): ");
        int n = in.nextInt();

        // 1. 1부터 n까지 배열 채우기
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i + 1;

        // 2. 추첨 결과를 담을 배열
        int[] result = new int[k];

        // 3. k번 뽑기
        for (int i = 0; i < k; i++) {
            // 0 ~ (n-1) 사이의 랜덤 인덱스 생성
            int r = (int) (Math.random() * n);

            // 결과 저장
            result[i] = numbers[r];

            // 뽑은 자리를 맨 뒤의 숫자로 메우고, 범위를 1 줄임
            numbers[r] = numbers[n - 1];
            n--;
        }

        // 4. 정렬해서 출력
        Arrays.sort(result);
        System.out.println("추첨 결과:");
        for (int r : result)
            System.out.println(r);
    }
}
