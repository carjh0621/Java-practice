package ticket.gateway;

import ticket.exception.PaymentException;

// 외부 결제 시스템과 통신하기 위한 규격
public interface PaymentGateway {
    void pay(String userIdentifier, int amount) throws PaymentException;
    void refund(String userIdentifier, int amount) throws PaymentException;
}