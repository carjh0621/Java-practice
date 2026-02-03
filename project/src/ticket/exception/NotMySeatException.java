package ticket.exception;

public class NotMySeatException extends ReservationException{
    public NotMySeatException(){
        super("본인이 예약한 좌석이 아닙니다.");
    }
    public NotMySeatException(String message){
        super(message);
    }
}
