package ticket.exception;

public class InvalidPaymentOptionException extends PaymentException{
    public InvalidPaymentOptionException(){
        super("카드와 현금결제 중에서 골라주세요.");
    }
    public InvalidPaymentOptionException(String message){
        super(message);
    }
}
