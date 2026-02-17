package ticket.domain;


import ticket.exception.AlreadyBookedException;
import ticket.exception.NotMySeatException;
import ticket.exception.SeatNotBookedException;
import ticket.interfaces.Reservable;

public class Seat implements Reservable {
    private boolean isBooked;
    private String reserverName;
    private boolean isVIP;
    private int row;
    private int col;

    public Seat(int row, int col, boolean isVIP){
        this.isBooked=false;
        this.reserverName="";
        this.isVIP=isVIP;
        this.row=row;
        this.col=col;
    }
    //특정 좌석에 대해서 예매를 하는것
    //n x m 좌석 배열 중 한 좌석에 대한 예매
    @Override
    public void book(String name) throws AlreadyBookedException {
        if (this.isBooked) {
            throw new AlreadyBookedException("이미 예약된 좌석입니다.");
        }
        this.isBooked=true;
        this.reserverName=name;
    }

    public void cancel(String name) throws SeatNotBookedException, NotMySeatException {
        if (!this.isBooked) {
            throw new SeatNotBookedException("예매된 좌석이 아닙니다.");
        }
        if (!this.reserverName.equals(name)){
            throw new NotMySeatException("본인이 예매한 좌석이 아닙니다.");
        }
        else{
            this.reserverName="";
            this.isBooked=false;
        }
    }

    @Override
    public int getPrice() {
        if(isVIP) {
            return 20000;
        }
        else{
            return 10000;
        }
    }

    @Override
    public boolean isBooked(){
        return isBooked;
    }

    public String getSymbol(){
        if(isVIP){
            return "[V]";
        }
        else{
            return "[o]";
        }
    }

    @Override
    public String getInfo(){
        return getSymbol() + row + "행, " +col +"열";
    }

    public String getReserverName(){
        return  reserverName;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
}

