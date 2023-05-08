import java.util.LinkedList;

public class TrainPath implements Comparable<TrainPath> {
    private LinkedList<Train> trains;
    private double cost;
    private int numberOfTrains;

    public TrainPath() {
        this.trains = new LinkedList<>();
        this.cost = 0;
        this.numberOfTrains=0;
    }

    public TrainPath(TrainPath other) {
        this.trains = new LinkedList<>(other.getTrains());
        this.cost = other.getCost();
        this.numberOfTrains=other.getNumberOfTrains();
    }

    public TrainPath(LinkedList<Train> nextPath) {
        this.trains=nextPath;
        this.numberOfTrains=nextPath.size();
        for (Train train:nextPath){
            this.cost+=train.getBasePrice();
        }
    }

    public int getNumberOfTrains() {
        return numberOfTrains;
    }

    public void setNumberOfTrains(int numberOfTrains) {
        this.numberOfTrains = numberOfTrains;
    }

    public LinkedList<Train> getTrains() {
        return trains;
    }

    public double getCost() {
        return cost;
    }

    public void setTrains(LinkedList<Train> trains) {
        this.trains = trains;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addTrain(Train train) {
        trains.add(train);
        cost += train.getBasePrice();
    }

    public Train getLastTrain() {
        if (trains.isEmpty()) {
            return null;
        }
        return trains.getLast();
    }

    public Station getLastStation() {
        if (trains.isEmpty()) {
            return null;
        }
        return trains.getLast().getDestinationStation();
    }

    @Override
    public int compareTo(TrainPath other) {
        return Double.compare(this.getCost(), other.getCost());
    }

    @Override
    public String toString() {
        return "TrainPath" +
                "\n, cost=" + cost +
                "\n, numberOfTrains=" + numberOfTrains +
                '\n';
    }
}
