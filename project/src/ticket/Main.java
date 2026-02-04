package ticket;

import ticket.exception.InsufficientBalanceException;
import ticket.exception.PaymentException;

import ticket.exception.SeatException;
import ticket.interfaces.Payable;
import ticket.model.Seat;
import ticket.model.User;

import ticket.service.ConcertHall;
import ticket.utils.PayUtils;
import ticket.utils.UserManager;
import ticket.utils.UserUtils;


import java.util.List;
import java.util.Scanner;




public class Main {



    public static void main(String[] args) {
        // 세팅
        Scanner scanner = new Scanner(System.in);
        ConcertHall hall = new ConcertHall();
        UserManager userManager = new UserManager();
        boolean isRun = true; //시스템 작동 중인지
        // 관련 변수들
        int r=0, c=0; //좌석 좌표
        String reservedName = null; //예약자 이름
        String name = null;
        String pwd = null;
        int userNo=0;

        while (isRun){
            System.out.println("\n--- 예매 시스템 ---");
            System.out.println("-100.관리자 모드 0.User 생성  1.홀 조회  2.좌석 예매  3.예매 취소  4.예매 조회  5.시스템 종료");
            System.out.print("입력: ");
            int menu = scanner.nextInt();


            switch (menu) {
                case -100:
                    // 관리자 모드: 가격순/이름순으로 예약 좌석 출력
                    System.out.println("1.가격순  2.이름순");
                    int adminChoice = scanner.nextInt();
                    List<Seat> reservedSeats = (adminChoice == 1)
                            ? hall.getReservedSeatsSortedByPrice()
                            : hall.getReservedSeatsSortedByName();
                    hall.printReservedSeats(reservedSeats);
                    break;
                case 0:
                    // 유저 생성
                    System.out.print("User 이름, pwd, 초기 자산 (name, pwd, initbalance): ");
                    name=scanner.next();
                    pwd=scanner.next();
                    int initbalance=scanner.nextInt();
                    scanner.nextLine();
                    User u=userManager.addUser(name,pwd,initbalance);
                    System.out.println("your id,pwd is ("+u.getUserID()+", "+pwd+")");
                    break;
                case 1:
                    hall.showSeats(); // 전체 좌석 출력
                    break;
                case 2:
                    // 좌석 예매
                    try{
                        //1. 인증
                        System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                        userNo=scanner.nextInt();
                        pwd = scanner.next();
                        if (!userManager.verifyUser(userNo, pwd)) {
                            continue; // 인증 실패
                        }
                        //2. 좌석 예매
                        System.out.print("예매할 좌석의 행, 열 입력 (row, column): ");
                        r = scanner.nextInt();
                        c = scanner.nextInt();
                        scanner.nextLine();
                        reservedName = UserUtils.UNametoRName(userManager.getUsers(), userNo);
                        int price = hall.reserveSeat(r, c, reservedName);
                        System.out.println("좌석 가격: " + price + "원");
                        //3. 결제 방법 선택
                        System.out.print("결제 방법 선택 (1. 카드, 2. 계좌): ");
                        int payOption = scanner.nextInt();
                        scanner.nextLine();
                        Payable payMethod = PayUtils.choosePayMethod(payOption);

                        //4. 결제 진행
                        User reservingUser = userManager.findUserById(userNo);
                        payMethod.pay(reservingUser, price);

                        //완료
                        System.out.println("예매가 완료되었습니다.");
                    }
                    catch (SeatException e){
                        System.out.println("예매 실패: " + e.getMessage());
                    }
                    catch (InsufficientBalanceException e){
                        System.out.println("결제 실패:" + e.getMessage());
                        System.out.println("-> 예매를 취소합니다.");
                        try {
                            int price=hall.cancelSeat(r, c, reservedName);
                        } catch (Exception ex) {
                            System.err.println("좌석 취소 실패: " + ex.getMessage());
                        }
                    }
                    catch (PaymentException e){
                        System.out.println("결제 실패:" + e.getMessage());
                    }
                    catch (Exception e) {
                        // 그 외 예상치 못한 에러
                        System.out.println("시스템 오류: " + e.getMessage());
                    }
                    break;
                case 3:
                    // 취소 로직
                    try{
                        // 1. 본인 인증
                        System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                        userNo=scanner.nextInt();
                        pwd= scanner.next();
                        if(!userManager.verifyUser(userNo,pwd)){
                            continue;
                        }
                        //2. 본인의 예매 내역 출력
                        reservedName=UserUtils.UNametoRName(userManager.getUsers(),userNo);
                        hall.findSeat(reservedName);
                        // 3. 취소할 좌석 선택
                        System.out.print("취소할 좌석의 행, 열 입력 (row, column): ");
                        r=scanner.nextInt();
                        c=scanner.nextInt();
                        scanner.nextLine();
                        // 4. 취소
                        int refundAmount=hall.cancelSeat(r,c,reservedName);
                        // 5. 환불
                        User currentUser = userManager.findUserById(userNo);
                        currentUser.deposit(refundAmount);
                        // 완료
                        System.out.println("취소 성공: " + refundAmount +"원이 환불되었습니다.");
                    }
                    catch (SeatException e){
                        System.out.println("취소 실패: "+e.getMessage());
                    }

                    break;
                case 4:
                    // 예매 확인 로직
                    // 본인 인증 -> 이름, 좌석 좌표 출력
                    // 없으면 예매 안했다 출력
                    System.out.print("본인의 유저 번호와 패스워드 입력 (user#, pwd): ");
                    userNo=scanner.nextInt();
                    pwd= scanner.next();
                    if(!userManager.verifyUser(userNo,pwd)){
                        continue;
                    }
                    scanner.nextLine();
                    hall.findSeat(UserUtils.UNametoRName(userManager.getUsers(),userNo));
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