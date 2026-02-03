package ticket.exception;

public class InsufficientBalanceException extends PaymentException{
    public InsufficientBalanceException(){
        super("잔액이 부족합니다.");
    }
    public InsufficientBalanceException(String message){
        super(message);
    }
}
