package ticket.exception;

public class InvalidSeatException extends ReservationException{
    public InvalidSeatException(){
        super("존재하지 않는 좌석(좌표 오류)입니다.");
    }
    public InvalidSeatException(String message){
        super(message);
    }
}
