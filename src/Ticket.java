import java.time.LocalTime;

public class Ticket {
    private int ID;
    private String passengerName;
    private double price;
    private Train train;
    private int seatID;
    private LocalTime departureTime,arrivalTime,issueTime;
    private User user;
    Ticket(Reservation reservation){
        this.ID=globals.createNewID(globals.ticketsIDs);
        this.passengerName=reservation.getUser().getName();
        this.train=reservation.getTrain();
        this.seatID=1;
        this.price=reservation.getPrice();
        this.departureTime=reservation.getTrain().getDepartureTime();
        this.arrivalTime=reservation.getTrain().getArrivalTime();
        this.issueTime=LocalTime.now();
        this.user=reservation.getUser();
    }

    public Ticket() {

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setIssueTime(LocalTime issueTime) {
        this.issueTime = issueTime;
    }

    public LocalTime getIssueTime() {
        return issueTime;
    }

    public Ticket(int ticketNumber, String passengerName, Train train, int seatNumber, LocalTime departureTime, LocalTime arrivalTime) {
        this.ID = ticketNumber;
        this.passengerName = passengerName;
        this.train = train;
        this.seatID = seatNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTrain(Train trainID) {
        this.train = trainID;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }
    public int getSeatID() {
        return seatID;
    }
    public int getID() {
        return ID;
    }
    public Train getTrain() {
        return train;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Ticket" +
                "\n, ID=" + ID +
                "\n, issueTime=" + issueTime +
                "\n, passengerName=" + passengerName +
                "\n, trainID=" + train +
                "\n, seatID=" + seatID +
                "\n, departureTime=" + departureTime +
                "\n, arrivalTime=" + arrivalTime +
                '\n';
    }
}
