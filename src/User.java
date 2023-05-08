import java.security.PublicKey;
import java.util.LinkedList;

public class User {
    private int ID;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private CreditCard card;
    private LinkedList<Reservation>reservations=new LinkedList<>();
    private LinkedList<Ticket>tickets=new LinkedList<>();

    public LinkedList<Reservation> getReservations() {
        return reservations;
    }

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }

    public void setReservations(LinkedList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setTickets(LinkedList<Ticket> tickets) {
        this.tickets = tickets;
    }
    public void addToTickets(Ticket ticket){
        tickets.add(ticket);
    }
    public void addToReservations(Reservation ticket){
        reservations.add(ticket);
    }
    public void removeFromTickets(Ticket ticket) {
        tickets.remove(ticket);
    }
    public void removeFromReservations(Reservation ticket){
        reservations.remove(ticket);
    }

    User(){}
    public User(int id, String name, String email, String password, String phoneNumber) {
        this.ID = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public User(int id, String name, String email, String password, String phoneNumber, CreditCard CreditCard) {
        this.ID = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        card=CreditCard;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }
    public CreditCard getCard() {
        return card;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User" +
                "\n, ID=" + ID +
                "\n, name=" + name +
                "\n, email=" + email +
                "\n, password=" + password +
                "\n, phoneNumber=" + phoneNumber +
                "\n, card=" + card +
                '\n';
    }
}
