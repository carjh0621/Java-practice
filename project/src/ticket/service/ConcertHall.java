package ticket.service;


import ticket.model.Seat;



public class ConcertHall {
    int row_n=10, col_n=10;
    Seat[][] seats;

    public ConcertHall(){
        seats = new Seat[row_n][col_n];
        for (int i = 0; i < row_n; i++) {
            for (int j = 0; j < col_n; j++) {
                if (i<2){
                    seats[i][j] = new Seat(i,j,true); // 앞의 두 행은 vip 좌석
                }
                else {
                    seats[i][j] = new Seat(i,j,false);    // 여기서 ticket.system.model.Seat 객체 생성
                }
            }
        }
    }

    public void showSeats(){
        //x: booked o: available
        System.out.println("x: booked, o: available, V: vip seat(currently available)");
        System.out.println("first two rows are vip seats");
        System.out.println("regular seat: 10000원, vip seat: 20000원");
        System.out.print("  ");
        for(int i=0;i<col_n;i++){
            System.out.print(" "+i+" ");
        }System.out.println();
        for(int i=0;i<row_n;i++){
            System.out.print(i+" ");
            for(int j=0;j<col_n;j++){
                boolean cur_book_info = seats[i][j].isBooked();
                if(!cur_book_info)
                    //System.out.print("[x]");
                    System.out.print(seats[i][j].getSymbol());
                    //getClass 사용에서 override 활용으로
                else
                    System.out.print("[x]");
            }
            System.out.println();
        }
    }

    public int reserveSeat(int r, int c, String name){
        if(r>=row_n || r<0 || c>=col_n || c<0) {
            System.out.println("out of index, reserve fail");
            return -1;
        }
        else if (seats[r][c].isBooked()){
            System.out.println("this seat is already booked, reserve fail");
            return -1;
            }
        else{
            if(seats[r][c].book(name)) {
                System.out.println("reserve success");
                return seats[r][c].getPrice();
            }
            else {
                System.out.println("reserve fail");
                return -1;
            }
        }
    }

    public void cancelSeat(int r, int c, String name){
        if(r>=row_n || r<0 || c>=col_n || c<0)
            System.out.println("out of index, cancel fail");
        else if (!seats[r][c].isBooked()) {
            System.out.println("this seat is not booked, cancel fail");
        }
        else{
            if(seats[r][c].cancel(name)==1)
                System.out.println("cancel success");
            else if(seats[r][c].cancel(name)==-1)
                System.out.println("this seat is not booked, cancel fail");
            else
                System.out.println("this seat is not booked by you. cancel fail");
        }
    }

    public void findSeat(String name){
        int count=0;
        for(int i=0;i<row_n;i++){
            for(int j=0;j<col_n;j++){
                if(seats[i][j].bookperson(name)) {
                    count++;
                    System.out.printf("%d번째 좌석 좌표(row,col): (%d,%d)\n", count, i, j);
                }
            }
        }
        if(count==0){
            System.out.println("예매한 좌석 없음");
        }
    }


}
