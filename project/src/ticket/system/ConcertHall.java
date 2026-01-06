package ticket.system;

class ConcertHall {
    int row_n=10, col_n=10;
    Seat[][] seats;

    public ConcertHall(){
        seats = new Seat[row_n][col_n];
        for (int i = 0; i < row_n; i++) {
            for (int j = 0; j < col_n; j++) {
                seats[i][j] = new Seat();    // 여기서 ticket.system.Seat 객체 생성
            }
        }
    }

    public void showSeats(){
        //x: booked o: available
        System.out.println("x: booked, o: available");
        System.out.print("  ");
        for(int i=0;i<col_n;i++){
            System.out.print(" "+i+" ");
        }System.out.println();
        for(int i=0;i<row_n;i++){
            System.out.print(i+" ");
            for(int j=0;j<col_n;j++){
                boolean cur_book_info = seats[i][j].isBooked();
                if(cur_book_info)
                    System.out.print("[x]");
                else
                    System.out.print("[o]");
            }
            System.out.println();
        }
    }

    public void reserveSeat(int r, int c, String name){
        if(r>=row_n || r<0 || c>=col_n || c<0)
            System.out.println("out of index, reserve fail");
        else if (seats[r][c].isBooked())
            System.out.println("this seat is already booked, reserve fail");
        else{
            if(seats[r][c].book(name)==1)
                System.out.println("reserve success");
            else
                System.out.println("reserve fail");
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
            else if(seats[r][c].cancel(name)==0)
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
