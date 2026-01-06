package ticket.system;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConcertHall hall = new ConcertHall();
        boolean isRun = true;

        while (isRun){
            System.out.println("\n--- 예매 시스템 ---");
            System.out.println("1.홀 조회  2.예약  3.취소  4.예매 조회  5.종료");
            System.out.print("입력: ");
            int menu = scanner.nextInt();

            String name;
            int r,c;

            switch (menu) {
                case 1:
                    hall.showSeats(); // 전체 좌석 출력
                    break;
                case 2:
                    // 입력 받기 로직 (System.out.print... scanner.nextInt...)
                    System.out.print("본인의 이름과 예약할 좌석의 행, 열 입력 (name, row, column): ");
                    name=scanner.next();
                    r=scanner.nextInt();
                    c=scanner.nextInt();
                    scanner.nextLine();
                    // hall.reserveSeat(row, col, name);
                    hall.reserveSeat(r,c,name);
                    break;
                case 3:
                    // 취소 로직
                    // hall.cancelSeat(row, col,name);
                    System.out.print("본인의 이름과 취소할 좌석의 행, 열 입력 (name, row, column): ");
                    name=scanner.next();
                    r=scanner.nextInt();
                    c=scanner.nextInt();
                    scanner.nextLine();
                    hall.cancelSeat(r,c,name);
                    break;
                case 4:
                    // 예매 확인 로직
                    // 본인 이름 입력 -> 이름, 좌석 좌표 출력
                    // 없으면 예매 안했다 출력
                    System.out.print("본인의 이름 입력 (name): ");
                    name = scanner.next();
                    scanner.nextLine();
                    hall.findSeat(name);
                    break;
                case 5:
                    isRun = false; // 루프 종료
                    System.out.println("시스템을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }

        }
    }
}