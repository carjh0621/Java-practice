package ticket.interfaces;

import ticket.exception.PaymentException;
import ticket.model.User;

public interface Payable{

    void pay(User user, int amount) throws PaymentException;
}