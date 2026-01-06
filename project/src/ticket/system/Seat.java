package ticket.system;

class Seat {
    private boolean isBooked;
    private String reserverName;

    public Seat(){
        this.isBooked=false;
        this.reserverName="";
    }
    //특정 좌석에 대해서 예매를 하는것
    //n x m 좌석 배열 중 한 좌석에 대한 예매
    //좌석의 index는 ConcertHall에서 관리
    public int book(String name){
       this.reserverName=name;
       this.isBooked=true;
       return 1; //예매 성공
    }

    public int cancel(String name){
        if(!this.reserverName.equals(name)){
            return -1; //name not same
        }
        else if(!this.isBooked){
            return 0; //not booked
        }
        else{
            this.reserverName=null;
            this.isBooked=false;
            return 1;
        }

    }

    public boolean isBooked(){
        return isBooked;
    }

    public boolean bookperson(String name){
        return this.reserverName.equals(name);
    }


}
