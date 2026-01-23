package ticket.payment;

import ticket.interfaces.Payable;
import ticket.model.User;


public class BankTransfer implements Payable{
    @Override
    public boolean pay(User user,int amount){
        if(user.pay(amount)){
            System.out.println("계좌 이체 승인: ["+amount+"]원");
            return true;
        }
        else{
            System.out.println("계좌 이체 실패: 잔액 부족");
            return false;
        }
    }


}
