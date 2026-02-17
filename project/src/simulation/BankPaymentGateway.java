package simulation;

import ticket.exception.InsufficientBalanceException;
import ticket.exception.PaymentException;
import ticket.gateway.PaymentGateway;

// Bank을 티켓 시스템의 PaymentGateway 규격에 맞게 연결해주는 구현체
public class BankPaymentGateway implements PaymentGateway {
    private final Bank bank;

    public BankPaymentGateway(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void pay(String userIdentifier, int amount) throws PaymentException {
        // 주민번호가 계좌번호인 상황
        boolean success= bank.withdraw(userIdentifier, amount);
        if(!success){
            throw new InsufficientBalanceException("결제 실패: 연동된 계좌의 잔액이 부족합니다.");
        }
    }

    @Override
    public void refund(String userIdentifier, int amount) throws PaymentException{
        bank.deposit(userIdentifier, amount);
    }
}