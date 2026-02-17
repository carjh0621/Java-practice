package simulation;

import ticket.domain.Account;
import ticket.exception.AuthException;
import ticket.exception.DuplicateUserException;
import ticket.repository.MemoryAccountRepository;
import ticket.service.AuthService;
import ticket.service.ConcertHall;
import ticket.service.TicketService;

public class ScenarioRunner {
    public static void main(String[] args) {
        System.out.println("--- 1. 외부 세계 세팅 ---");
        Person chulsoo = new Person("김철수", "900101-1234567");
        Bank bank = new Bank();
        bank.createBankAccount(chulsoo.getSsn(), 15000);

        System.out.println("--- 2. 티켓팅 시스템 부팅 및 조립 (DI) ---");
        MemoryAccountRepository accountRepo = new MemoryAccountRepository();
        AuthService authService = new AuthService(accountRepo);

        ConcertHall concertHall = new ConcertHall();
        BankPaymentGateway paymentGateway = new BankPaymentGateway(bank);
        TicketService ticketService = new TicketService(concertHall, paymentGateway);

        System.out.println("\n--- 3. 시나리오 시작 ---");

        //1. 철수가 티켓 시스템에 회원가입
        System.out.println("[가입 시도]");
        try {
            Account chulsooAccount = authService.register("chulsoo_id", "pwd123", chulsoo.getSsn());
            System.out.println("가입 성공! 로그인 ID: " + chulsooAccount.getLoginId());
        } catch (DuplicateUserException e) {
            System.out.println("예외 발생(정상 방어됨): " + e.getMessage());
        }


        // 2. 철수가 실수로 또 회원가입 시도 (주민번호 중복)
        System.out.println("\n[중복 가입 시도]");
        try {
            authService.register("another_id", "pwd456", chulsoo.getSsn());
        } catch (Exception e) {
            System.out.println("예외 발생(정상 방어됨): " + e.getMessage());
        }

        //3. 철수 로그인
        System.out.println("\n[로그인 시도]");
        Account loggedInSession = null;
        try {
            loggedInSession = authService.login("chulsoo_id", "pwd123");
            System.out.println("로그인 성공!");
        } catch (AuthException e) {
            System.out.println("예외 발생(정상 방어됨): " + e.getMessage());
        }

        //4. 로그인 없는 유저의 무단 접근 테스트
        System.out.println("\n[비로그인 예매 시도]");
        try {
            ticketService.reserveSeat(null, 5, 5);
        } catch (Exception e) {
            System.out.println("예외 발생(정상 방어됨): " + e.getMessage());
        }

        //5. VIP 좌석 예매 시도 (VIP는 20000원이므로 철수 잔장 15000원으로는 실패해야 함)
        System.out.println("\n[VIP석 예매 시도 - 잔액 부족]");
        try {
            ticketService.reserveSeat(loggedInSession, 0, 0);
        } catch (Exception e) {
            System.out.println("예외 발생(정상 롤백됨): " + e.getMessage());
        }

        // 6. 일반 좌석 예매 성공 상황
        System.out.println("\n[일반석 예매 시도 - 잔액 충분]");
        try {
            // (5, 5) 좌석 예매
            ticketService.reserveSeat(loggedInSession, 5, 5);
            System.out.println("결제 후 잔액: " + bank.getBalance(chulsoo.getSsn()) + "원");
            // 예상: 15000 - 10000 = 5000원

            // 7. 예매 취소 및 환불 테스트 (추가된 부분)
            System.out.println("\n[예매 취소 및 환불 시도]");
            ticketService.cancelSeat(loggedInSession, 5, 5);
            System.out.println("환불 후 잔액: " + bank.getBalance(chulsoo.getSsn()) + "원");
            // 예상: 5000 + 10000 = 15000원 (원상복구)

        } catch (Exception e) {
            System.out.println("예외: " + e.getMessage());
        }
    }
}