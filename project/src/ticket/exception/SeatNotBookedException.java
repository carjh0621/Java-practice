package ticket.exception;

public class SeatNotBookedException extends SeatException{
    public SeatNotBookedException(){
        super("예매된 좌석이 아닙니다.");
    }
    public SeatNotBookedException(String message){
        super(message);
    }
}
