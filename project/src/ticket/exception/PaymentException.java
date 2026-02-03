package ticket.exception;

public class PaymentException extends ReservationException{
    public PaymentException(){
        super("결제 관련 예외");
    }
    public PaymentException(String message){
        super(message);
    }
}
