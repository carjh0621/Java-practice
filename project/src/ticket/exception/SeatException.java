package ticket.exception;

public class SeatException extends ReservationException {
    public SeatException(){
        super("좌석 관련 예외");
    }
    public SeatException(String message){
        super(message);
    }
}
