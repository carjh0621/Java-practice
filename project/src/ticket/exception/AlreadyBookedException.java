package ticket.exception;

public class AlreadyBookedException extends ReservationException{
    public AlreadyBookedException(){
        super("이미 예약된 좌석입니다.");
    }
    public AlreadyBookedException(String message){
        super("message");
    }
}
