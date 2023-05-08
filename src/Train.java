import java.time.LocalTime;
import java.util.LinkedList;

public class Train {
    private int ID,numberOfSeats,bookedSeats;
    private double basePrice;
    private Station sourceStation,destinationStation;
    private String trainModel;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public Train(int id,double basePrice, String trainModel, Station sourceStation, Station destinationStation, LocalTime arrivalTime, LocalTime departureTime,int numberOfSeats) {
        this.ID = id;
        this.basePrice=basePrice;
        this.trainModel = trainModel;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.numberOfSeats=numberOfSeats;
        this.bookedSeats=0;
    }

    public Train() {

    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setSourceStation(Station sourceStation) {
        this.sourceStation = sourceStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public Station getSourceStation() {
        return sourceStation;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setTrainModel(String trainName) {
        this.trainModel = trainName;
    }
    public String getTrainModel() {
        return trainModel;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "Train" +
                "\n, ID=" + ID +
                "\n, numberOfSeats=" + numberOfSeats +
                "\n, bookedSeats=" + bookedSeats +
                "\n, basePrice=" + basePrice +
                "\n, sourceStation=" + sourceStation.getStationName() +
                "\n, destinationStation=" + destinationStation.getStationName() +
                "\n, trainModel=" + trainModel +
                "\n, arrivalTime=" + arrivalTime +
                "\n, departureTime=" + departureTime +
                '\n';
    }
}
