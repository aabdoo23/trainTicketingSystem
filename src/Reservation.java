import java.util.LinkedList;

public class Reservation {
    private int reservationID;
    private double price;
    private User user;
    private Train train;

    public Reservation(int reservationID,double price, User user, Train train) {
        this.reservationID = reservationID;
        this.price=price;
        this.user = user;
        this.train = train;
    }

    public Reservation() {

    }


    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getReservationID() {
        return reservationID;
    }



    public void setTrain(Train train) {
        this.train = train;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }



    public Train getTrain() {
        return train;
    }

    @Override
    public String toString() {
        return "Reservation" +
                "\n, reservationID=" + reservationID +
                "\n, user=" + user +
                "\n, train=" + train +
                '\n';
    }
}