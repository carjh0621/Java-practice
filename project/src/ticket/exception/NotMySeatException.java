package ticket.exception;

public class NotMySeatException extends SeatException{
    public NotMySeatException(){
        super("본인이 예매한 좌석이 아닙니다.");
    }
    public NotMySeatException(String message){
        super(message);
    }
}
