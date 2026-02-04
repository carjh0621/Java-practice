package ticket.utils;

import ticket.exception.InvalidPaymentOptionException;
import ticket.interfaces.Payable;
import ticket.payment.BankTransfer;
import ticket.payment.CreditCard;

public class PayUtils {
    /**
     * 사용자가 선택한 번호에 따라 결제 방식을 반환한다.
     */
    public static Payable choosePayMethod(int option) throws InvalidPaymentOptionException {
        switch (option) {
            case 1: return new CreditCard();
            case 2: return new BankTransfer();
            default: throw new InvalidPaymentOptionException();
        }
    }
}
