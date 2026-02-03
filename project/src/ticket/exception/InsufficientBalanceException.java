package ticket.exception;

public class InsufficientBalanceException extends ReservationException{
    public InsufficientBalanceException(){
        super("잔액이 부족합니다.");
    }
    public InsufficientBalanceException(String message){
        super(message);
    }
}
