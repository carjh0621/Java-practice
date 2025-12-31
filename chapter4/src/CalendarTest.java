import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarTest {
    public static void main(String[] args) {
        // 1. 현재 날짜 정보 가져오기
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();    // 현재 월
        int today = date.getDayOfMonth();    // 오늘 날짜

        // 2. 이번 달의 1일로 이동
        // date 객체는 불변이므로, 반환된 새 객체를 다시 date 변수에 할당해야 함
        date = date.minusDays(today - 1);

        // 3. 1일의 요일 구하기 (월=1, ... 일=7)
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        // 4. 첫 주 들여쓰기 (1일이 수요일(3)이면 월, 화 두 칸 비워야 함)
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }

        // 5. 달력 출력 루프
        while (date.getMonthValue() == month) {

            // 날짜 출력 (3칸 확보)
            System.out.printf("%3d", date.getDayOfMonth());

            // 오늘 날짜면 * 표시, 아니면 공백
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }

            // 날짜 하루 증가
            // Mutator가 아니므로 date.plusDays(1)만 호출하면 아무 일도 안 일어남.
            // 반드시 반환값을 다시 할당해야 함.
            date = date.plusDays(1);

            // 월요일(1)이 되면 줄바꿈 (일요일(7) 다음 날이 월요일)
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }

        // 마지막 줄 바꿈 처리
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}