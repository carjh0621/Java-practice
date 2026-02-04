package ticket.interfaces;

import ticket.exception.SeatException;

public interface Reservable {
    boolean isBooked();
    void book(String name) throws SeatException;
    int getPrice();
    String getInfo();
}
