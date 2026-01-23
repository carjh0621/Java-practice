package ticket;

import ticket.model.User;
import ticket.service.ConcertHall;
import ticket.utils.UserUtils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;




public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConcertHall hall = new ConcertHall();
        boolean isRun = true;

        //User 생성
        ArrayList<User> users = new ArrayList<>();
        int nextID=0;
        int initbalance;
        String pwd;

        while (isRun){
            System.out.println("\n--- 예매 시스템 ---");
            System.out.println("0.User 생성  1.홀 조회  2.좌석 예매  3.예매 취소  4.예매 조회  5.시스템 종료");
            System.out.print("입력: ");
            int menu = scanner.nextInt();

            String name;
            int r,c;
            int userNo;

            switch (menu) {
                case 0:
                    // 유저 생성
                    System.out.print("User 이름, pwd, 초기 자산 (name, pwd, initbalance): ");
                    name=scanner.next();
                    pwd=scanner.next();
                    initbalance=scanner.nextInt();
                    scanner.nextLine();
                    User u=new User(name,nextID++,pwd,initbalance);
                    users.add(u);
                    System.out.println("your id,pwd is ("+(nextID-1)+", "+pwd+")");
                    break;
                case 1:
                    hall.showSeats(); // 전체 좌석 출력
                    break;
                case 2:
                    // 좌석 예매
                    System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                    userNo=scanner.nextInt();
                    pwd= scanner.next();
                    if(!UserUtils.verifyUserwithPwd(users,userNo,pwd)){
                        continue;
                    }
                    System.out.print("예매할 좌석의 행, 열 입력 (row, column): ");
                    r=scanner.nextInt();
                    c=scanner.nextInt();
                    scanner.nextLine();
                    // hall.reserveSeat(row, col, name);
                    hall.reserveSeat(r,c,UserUtils.UNametoRName(users,userNo));
                    break;
                case 3:
                    // 취소 로직
                    System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                    userNo=scanner.nextInt();
                    pwd= scanner.next();
                    if(!UserUtils.verifyUserwithPwd(users,userNo,pwd)){
                        continue;
                    }
                    //본인의 예매 내역 출력
                    hall.findSeat(UserUtils.UNametoRName(users,userNo));
                    System.out.print("취소할 좌석의 행, 열 입력 (row, column): ");
                    r=scanner.nextInt();
                    c=scanner.nextInt();
                    scanner.nextLine();
                    hall.cancelSeat(r,c,UserUtils.UNametoRName(users,userNo));
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