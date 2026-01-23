package ticket.model;


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
    public boolean book(String name){
        if (this.isBooked) return false;
        this.reserverName=name;
        this.isBooked=true;
        return true; //예매 성공
    }

    public int cancel(String name){
        if (!this.isBooked) return -1;
        if (!this.reserverName.equals(name)) return 0;
        else{
            this.reserverName="";
            this.isBooked=false;
            return 1;
        }
    }



    @Override
    public int getPrice() {
        if(isVIP) {
            return 10000;
        }
        else{
            return 20000;
        }
    }

    @Override
    public boolean isBooked(){
        return isBooked;
    }

    public boolean bookperson(String name){
        return this.reserverName.equals(name);
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
}

