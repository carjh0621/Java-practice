package ticket.interfaces;

import ticket.exception.ReservationException;
import ticket.exception.SeatException;

public interface Reservable {
    boolean isBooked();
    void book(String name) throws ReservationException;
    int getPrice();
    String getInfo();
}
