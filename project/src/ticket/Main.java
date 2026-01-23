package ticket;

import ticket.interfaces.Payable;
import ticket.model.User;
import ticket.payment.BankTransfer;
import ticket.payment.CreditCard;
import ticket.service.ConcertHall;
import ticket.utils.PayUtils;
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
                case -100:
                    //관리자 모드
                    break;
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

                    int price=hall.reserveSeat(r,c,UserUtils.UNametoRName(users,userNo));
                    if(price<0){
                        continue;
                    }
                    System.out.println("좌석 가격: " + price + "원");
                    //결제 방법 선택
                    System.out.print("결제 방법 선택 (1. 카드, 2. 계좌): ");
                    int payOption = scanner.nextInt();
                    scanner.nextLine();
                    Payable payMethod = PayUtils.choosePayMethod(payOption);
                    if(payMethod==null){
                        System.out.println("잘못된 결제 방법입니다.");
                        hall.cancelSeat(r, c, UserUtils.UNametoRName(users, userNo));
                        continue;
                    }

                    //실제 결제 진행
                    boolean paid = payMethod.pay(users.get(userNo), price);
                    if(!paid){
                        //잔액 부족 -> 예약 취소
                        if(!hall.cancelSeat(r,c,UserUtils.UNametoRName(users,userNo)))
                            continue;
                    }
                    //System.out.println("결제가 완료되었습니다.");
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
                    // 본인 인증 -> 이름, 좌석 좌표 출력
                    // 없으면 예매 안했다 출력
                    System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                    userNo=scanner.nextInt();
                    pwd= scanner.next();
                    if(!UserUtils.verifyUserwithPwd(users,userNo,pwd)){
                        continue;
                    }
                    scanner.nextLine();
                    hall.findSeat(UserUtils.UNametoRName(users,userNo));
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