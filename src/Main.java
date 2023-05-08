public class Main  {
    public static void main(String[] args) throws Exception {
        new DB();
        globals.userLinkedList=DB.getAllUsers();
        globals.stationLinkedList=DB.getAllStations();
        globals.reservationsLinkedList=DB.getAllReservations();
        globals.trainLinkedList=DB.getAllTrains();
        for (User user:globals.userLinkedList){
            user.setTickets(DB.getUserTickets(user));
            user.setReservations(DB.getUserReservations(user));
        }

        new Home();
    }

}