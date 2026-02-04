package ticket.payment;

import ticket.exception.PaymentException;
import ticket.interfaces.Payable;
import ticket.model.User;


public class BankTransfer implements Payable{
    @Override
    public void pay(User user,int amount) throws PaymentException {
        user.pay(amount);
        System.out.println("계좌 이체 완료");
    }


}
