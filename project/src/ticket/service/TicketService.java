package ticket.service;

import ticket.domain.Account;
import ticket.exception.PaymentException;
import ticket.gateway.PaymentGateway;

public class TicketService {
    private final ConcertHall concertHall;
    private final PaymentGateway paymentGateway;

    public TicketService(ConcertHall concertHall, PaymentGateway paymentGateway) {
        this.concertHall = concertHall;
        this.paymentGateway = paymentGateway;
    }

    public void reserveSeat(Account loggedInAccount, int r, int c) throws Exception {
        //권한/상태 검증
        if (loggedInAccount == null) {
            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
        }

        // 좌석 선점 (임시 예약)
        // 기존 ConcertHall의 파라미터에 맞추어 loginId를 예약자 이름으로 사용
        // loginId는 중복 x,
        // SeatException 발생 가능
        int price = concertHall.reserveSeat(r, c, loggedInAccount.getLoginId());

        // 외부 시스템에 결제 요청
        try {
            paymentGateway.pay(loggedInAccount.getOwnerSsn(), price);
            System.out.println("예매가 완료되었습니다! (좌석: " + r + "행 " + c + "열)");
        }
        catch (PaymentException e){
            //결제 실패 시 보상 트랜잭션 (롤백)
            concertHall.cancelSeat(r, c, loggedInAccount.getLoginId());
            System.out.println("잔액이 부족하여 결제에 실패했습니다. 선점된 좌석을 취소합니다.");
            throw e;
        }

    }

    // 취소 기능도 비슷한 패턴으로 구성 (권한 검증 -> 좌석 취소 -> 결제 시스템 refund 호출)
    // TicketService.java 내부에 추가
    public void cancelSeat(Account loggedInAccount, int r, int c) throws Exception {
        if (loggedInAccount == null) {
            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
        }

        // 1. 사전 검증: 유효한 좌석인지, 본인이 예매한 것이 맞는지 확인
        concertHall.checkSeatOwner(r, c, loggedInAccount.getLoginId());

        // 검증을 통과했으므로 환불할 금액을 조회합니다.
        int refundAmount = concertHall.getSeatPrice(r, c);

        // 2. 환불 요청 (외부 결제 시스템 먼저 호출!)
        // 여기서 PaymentException이 터지면, 아래 코드는 실행되지 않으므로 좌석은 안전하게 유지됩니다.
        paymentGateway.refund(loggedInAccount.getOwnerSsn(), refundAmount);

        // 3. 내부 상태 변경 (좌석 취소)
        // 외부 환불이 성공적으로 끝났을 때만 우리 시스템의 좌석을 비웁니다.
        concertHall.cancelSeat(r, c, loggedInAccount.getLoginId());

        System.out.println("예매가 정상적으로 취소되었으며, " + refundAmount + "원이 환불되었습니다.");
    }
}