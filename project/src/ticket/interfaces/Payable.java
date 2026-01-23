package ticket.interfaces;

import ticket.model.User;

public interface Payable{
    // 성공하면 true, 실패하면 false
    boolean pay(User user, int amount);
}