import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DB {
    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Statement stmt;
    String url = "jdbc:mysql://localhost:3306/ticketingsystem";
    String username = "root";
    String pass = "";

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public static void truncateTrains() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.executeUpdate("TRUNCATE TABLE train");
    }
    public static void truncateUsers() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.executeUpdate("TRUNCATE TABLE user");
    }
    public static void truncateStations() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.executeUpdate("TRUNCATE TABLE station");
    }
    public static void truncateTickets() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.executeUpdate("TRUNCATE TABLE ticket");
    }
    public static void truncateReservations() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.executeUpdate("TRUNCATE TABLE reservation");
    }
    public static LinkedList<User> getAllUsers() throws SQLException {
        LinkedList<User> users = new LinkedList<>();
        String query = "SELECT * FROM user";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setID(rs.getInt("ID"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                CreditCard card = new CreditCard();
                if(rs.getString("cardNumber")==null){
                    card=null;
                }
                else{
                    card.setCardNumber(rs.getString("cardNumber"));
                    card.setHolderName(rs.getString("cardHolderName"));
                    card.setExpDate(rs.getString("expirationDate"));
                    card.setCVV(rs.getInt("cvv"));
                }

                user.setCard(card);
                user.setTickets(getUserTickets(user));
                user.setReservations(getUserReservations(user));
                users.add(user);
            }
        }
        return users;
    }
    public static LinkedList<Train> getAllTrains() throws SQLException {
        LinkedList<Train> trains = new LinkedList<>();
        String query = "SELECT * FROM train";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Train train = new Train();
                train.setID(rs.getInt("ID"));
                train.setNumberOfSeats(rs.getInt("numberOfSeats"));
                train.setBookedSeats(rs.getInt("bookedSeats"));
                train.setBasePrice(rs.getDouble("basePrice"));
                globals.stationLinkedList=DB.getAllStations();
                Station sourceStation = new Station();
                for (Station station:globals.stationLinkedList){
                    if(rs.getInt("sourceStationID")==station.getStationId()){
                        sourceStation=station;
                        break;
                    }
                }
                Station destinationStation = new Station();
                for (Station station:globals.stationLinkedList){
                    if(rs.getInt("destinationStationID")==station.getStationId()){
                        destinationStation=station;
                        break;
                    }
                }
                train.setSourceStation(sourceStation);
                train.setDestinationStation(destinationStation);
                train.setTrainModel(rs.getString("trainModel"));
                train.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
                train.setDepartureTime(rs.getTime("departureTime").toLocalTime());
                trains.add(train);
            }
        }
        return trains;
    }
    public static LinkedList<Station> getAllStations() throws SQLException {
        LinkedList<Station> stations = new LinkedList<>();
        String query = "SELECT * FROM station";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Station station = new Station();
                station.setStationId(rs.getInt("stationId"));
                station.setStationName(rs.getString("stationName"));
                station.setCity(rs.getString("city"));
                station.setCountry(rs.getString("country"));
                stations.add(station);
            }
        }
        return stations;
    }
    public static LinkedList<Reservation> getAllReservations() throws SQLException {
        LinkedList<Reservation> reservations = new LinkedList<>();
        String query = "SELECT * FROM reservation";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationID(rs.getInt("reservationID"));
                reservation.setPrice(rs.getDouble("price"));
                User user = new User();
                user.setID(rs.getInt("userID"));
                reservation.setUser(user);
                globals.trainLinkedList=getAllTrains();
                Train train = new Train();
                for (Train train1:globals.trainLinkedList){
                    if(train1.getID()==rs.getInt("trainID")){
                        train=train1;
                        break;
                    }
                }
                reservation.setTrain(train);
                reservations.add(reservation);
            }
        }
        return reservations;
    }
    public static void setAllUsers() throws SQLException {
        truncateUsers();
        String query;
        for (User user : globals.userLinkedList) {
            if (user.getCard() == null) {
                query = "INSERT INTO user (ID, name, email, password, phoneNumber) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, email=?, password=?, phoneNumber=?";
                try (PreparedStatement ptmt = conn.prepareStatement(query)) {
                    ptmt.setInt(1, user.getID());
                    ptmt.setString(2, user.getName());
                    ptmt.setString(3, user.getEmail());
                    ptmt.setString(4, user.getPassword());
                    ptmt.setString(5, user.getPhoneNumber());
                    ptmt.setString(6, user.getName());
                    ptmt.setString(7, user.getEmail());
                    ptmt.setString(8, user.getPassword());
                    ptmt.setString(9, user.getPhoneNumber());
                    ptmt.executeUpdate();
                }
            } else {
                query = "INSERT INTO user (ID, name, email, password, phoneNumber, cardNumber, cardHolderName, expirationDate, cvv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, email=?, password=?, phoneNumber=?, cardNumber=?, cardHolderName=?, expirationDate=?, cvv=?";
                try (PreparedStatement ptmt = conn.prepareStatement(query)) {
                    ptmt.setInt(1, user.getID());
                    ptmt.setString(2, user.getName());
                    ptmt.setString(3, user.getEmail());
                    ptmt.setString(4, user.getPassword());
                    ptmt.setString(5, user.getPhoneNumber());
                    ptmt.setString(6, user.getCard().getCardNumber());
                    ptmt.setString(7, user.getCard().getHolderName());
                    ptmt.setString(8, user.getCard().getExpDate());
                    ptmt.setInt(9, user.getCard().getCVV());
                    ptmt.setString(10, user.getName());
                    ptmt.setString(11, user.getEmail());
                    ptmt.setString(12, user.getPassword());
                    ptmt.setString(13, user.getPhoneNumber());
                    ptmt.setString(14, user.getCard().getCardNumber());
                    ptmt.setString(15, user.getCard().getHolderName());
                    ptmt.setString(16, user.getCard().getExpDate());
                    ptmt.setInt(17, user.getCard().getCVV());
                    ptmt.executeUpdate();
                }
            }
        }
    }
    public static void setAllReservations() throws SQLException {
        truncateReservations();
        String insertQuery = "INSERT INTO reservation (reservationID, price, userID, trainID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (Reservation reservation : globals.reservationsLinkedList) {
                pstmt.setInt(1, reservation.getReservationID());
                pstmt.setDouble(2, reservation.getPrice());
                pstmt.setInt(3, reservation.getUser().getID());
                pstmt.setInt(4, reservation.getTrain().getID());
                pstmt.executeUpdate();
            }
        }
    }
    public static void setAllTickets() throws SQLException {
        truncateTickets();

        String insertQuery = "INSERT INTO ticket (ID, passengerName, price, trainID, seatID, departureTime, arrivalTime, issueTime,userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (User user:globals.userLinkedList){
                for (Ticket ticket : user.getTickets()) {
                    pstmt.setInt(1, ticket.getID());
                    pstmt.setString(2, ticket.getPassengerName());
                    pstmt.setDouble(3, ticket.getPrice());
                    pstmt.setInt(4, ticket.getTrain().getID());
                    pstmt.setInt(5, ticket.getSeatID());
                    pstmt.setTime(6, Time.valueOf(ticket.getDepartureTime()));
                    pstmt.setTime(7, Time.valueOf(ticket.getArrivalTime()));
                    pstmt.setTime(8, Time.valueOf(ticket.getIssueTime()));
                    pstmt.setInt(9,user.getID());
                    pstmt.executeUpdate();
                }

            }
        }
    }
    public static void setAllTrains() throws SQLException {
        truncateTrains();
        String insertQuery = "INSERT INTO Train (ID, numberOfSeats, bookedSeats, basePrice, sourceStationID, destinationStationID, trainModel, arrivalTime, departureTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (Train train : globals.trainLinkedList) {
                pstmt.setInt(1, train.getID());
                pstmt.setInt(2, train.getNumberOfSeats());
                pstmt.setInt(3, train.getBookedSeats());
                pstmt.setDouble(4, train.getBasePrice());
                pstmt.setInt(5, train.getSourceStation().getStationId());
                pstmt.setInt(6, train.getDestinationStation().getStationId());
                pstmt.setString(7, train.getTrainModel());
                pstmt.setTime(8, Time.valueOf(train.getArrivalTime()));
                pstmt.setTime(9, Time.valueOf(train.getDepartureTime()));
                pstmt.executeUpdate();
            }
        }
    }
    public static void setAllStations() throws SQLException {
        truncateStations();
        String insertQuery = "INSERT INTO Station (stationId, stationName, city, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (Station station : globals.stationLinkedList) {
                pstmt.setInt(1, station.getStationId());
                pstmt.setString(2, station.getStationName());
                pstmt.setString(3, station.getCity());
                pstmt.setString(4, station.getCountry());
                pstmt.executeUpdate();
            }
        }
    }


    public static LinkedList<Ticket> getUserTickets(User user) throws SQLException {
        LinkedList<Ticket> tickets = new LinkedList<>();
        String query = "SELECT * FROM Ticket WHERE userID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user.getID());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setID(rs.getInt("ID"));
                    ticket.setPassengerName(rs.getString("passengerName"));
                    ticket.setPrice(rs.getDouble("price"));
                    Train train = new Train();
                    for (Train train1:globals.trainLinkedList){
                        if(rs.getInt("trainID")==train1.getID()){
                            train=train1;
                            break;
                        }
                    }
                    ticket.setUser(user);
                    ticket.setTrain(train);
                    ticket.setSeatID(rs.getInt("seatID"));
                    ticket.setDepartureTime(rs.getTime("departureTime").toLocalTime());
                    ticket.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
                    ticket.setIssueTime(rs.getTime("issueTime").toLocalTime());
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }
    public static LinkedList<Reservation> getUserReservations(User user) throws SQLException {
        LinkedList<Reservation> reservations = new LinkedList<>();
        String query = "SELECT * FROM Reservation WHERE userID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user.getID());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservationID(rs.getInt("reservationID"));
                    reservation.setPrice(rs.getDouble("price"));
                    Train train = new Train();
                    for (Train train1:globals.trainLinkedList){
                        if(rs.getInt("trainID")==train1.getID()){
                            train=train1;
                            break;
                        }
                    }
                    reservation.setUser(user);
                    reservation.setTrain(train);
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

}
