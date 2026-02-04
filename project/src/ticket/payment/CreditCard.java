package ticket.payment;

import ticket.exception.PaymentException;
import ticket.interfaces.Payable;
import ticket.model.User;

public class CreditCard implements Payable {
    @Override
    public void pay(User user, int amount) throws PaymentException {
        user.pay(amount);
        System.out.println("카드 결제 승인: "+ amount);
    }
}
