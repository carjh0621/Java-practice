package ticket.service;


import ticket.exception.*;
import ticket.domain.Seat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


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

    public int reserveSeat(int r, int c, String name) throws SeatException {
        if(r>=row_n || r<0 || c>=col_n || c<0) {
            throw new InvalidSeatException("잘못된 좌석 좌표입니다: " + r + ", "+c);
        }
        seats[r][c].book(name);
        return seats[r][c].getPrice();
    }

    // 실제 상태를 변경하지 않고, 권한과 상태만 먼저 체크
    public void checkSeatOwner(int r, int c, String name) throws SeatException {
        if(r >= row_n || r < 0 || c >= col_n || c < 0) {
            throw new InvalidSeatException("잘못된 좌석 좌표입니다: " + r + ", " + c);
        }
        if(!seats[r][c].isBooked()) {
            throw new SeatNotBookedException("예매된 좌석이 아닙니다.");
        }
        if(!seats[r][c].getReserverName().equals(name)) {
            throw new NotMySeatException("본인이 예매한 좌석이 아닙니다.");
        }
    }

    public int cancelSeat(int r, int c, String name) throws SeatException{
        if(r>=row_n || r<0 || c>=col_n || c<0) {
            throw new InvalidSeatException("잘못된 좌석 좌표입니다: " + r + ", " + c);
        }
        seats[r][c].cancel(name);
        return seats[r][c].getPrice();
    }


    public int getSeatPrice(int r, int c){
        return seats[r][c].getPrice();
    }

    //  관리자 메서드
    // 가격순으로 예약된 좌석 정렬
    public List<Seat> getReservedSeatsSortedByPrice() {
        List<Seat> reserved = new ArrayList<>();
        for (int i = 0; i < row_n; i++) {
            for (int j = 0; j < col_n; j++) {
                Seat s = seats[i][j];
                if (s.isBooked()) {
                    reserved.add(s);
                }
            }
        }
        reserved.sort(Comparator.comparingInt(Seat::getPrice));
        return reserved;
    }

    // 예약자 이름순으로 예약된 좌석 정렬
    public List<Seat> getReservedSeatsSortedByName() {
        List<Seat> reserved = new ArrayList<>();
        for (int i = 0; i < row_n; i++) {
            for (int j = 0; j < col_n; j++) {
                Seat s = seats[i][j];
                if (s.isBooked()) {
                    reserved.add(s);
                }
            }
        }
        reserved.sort(Comparator
                .comparing(Seat::getReserverName)
                .thenComparingInt(Seat::getRow)
                .thenComparingInt(Seat::getCol));
        return reserved;
    }

    // 예약 좌석 리스트를 출력
    public void printReservedSeats(List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            System.out.println("예약된 좌석이 없습니다.");
            return;
        }
        for (Seat s : seats) {
            System.out.printf("예약자: %s / 좌표: (%d,%d) / 가격: %d원%n",
                    s.getReserverName(), s.getRow(), s.getCol(), s.getPrice());
        }
    }
}
