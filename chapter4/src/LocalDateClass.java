import java.time.LocalDate;
import java.util.Date;

public class LocalDateClass {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("Date(time): "+ date);

        System.out.println("------------------");

        LocalDate newYearsEve = LocalDate.of(1999,12,31);
        LocalDate today = LocalDate.now();

        System.out.println("passdate: " + newYearsEve);
        System.out.println("todaydate: "+ today);

        System.out.println("------------------");

        int year = newYearsEve.getYear();
        int month= newYearsEve.getMonthValue();
        int day = newYearsEve.getDayOfMonth();
        System.out.printf("result: %dy %dm %dd \n",year,month,day);

        System.out.println("------------------");

        LocalDate aThousandDaysLater = newYearsEve.plusDays(1000);

        // 원본 객체(newYearsEve)는 변하지 않음
        System.out.println("원본 날짜: " + newYearsEve);
        System.out.println("1000일 뒤: " + aThousandDaysLater);

        // 계산된 날짜 확인
        System.out.printf("1000일 뒤는 %d년 %d월 %d일\n",
                aThousandDaysLater.getYear(),
                aThousandDaysLater.getMonthValue(),
                aThousandDaysLater.getDayOfMonth());
    }
}
