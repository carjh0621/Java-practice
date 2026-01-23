package ticket.interfaces;

public interface Reservable {
    boolean isBooked();
    boolean book(String name);
    int getPrice();
    String getInfo();
}
