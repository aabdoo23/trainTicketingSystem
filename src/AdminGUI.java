import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.LinkedList;

public class AdminGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel userPanel;
    private JPanel depoPanel;
    private JList<String> usersList;
    private JList<String> stationsList;
    private JButton newUserButton;
    private JButton dropUserButton;
    private JButton closeButton;
    private JButton refreshButton;
    private JButton newStationButton;
    private JList<String> reservationsList;
    private JButton selectUserButton;
    private JButton dropReservationButton;
    private JButton editButton;
    private JList<String> trainsList;
    private JButton editTrainButton;
    private JButton dropTrain;
    private JButton newTrain;
    private JButton rejectReservation;
    private JButton approveReservation;
    private JList<String> pendingReservationsList;
    private JButton printUserTicket;
    private JButton dropStationButton;
    private JList<String> userTicketsList;
    private JButton approveReservationButton;
    private JButton dropUserTicketButton;
    User selectedUser=null;
    void updateAll() {
        if(selectedUser!=null){
            globals.makeList(selectedUser.getReservations(),reservationsList);
            globals.makeList(selectedUser.getTickets(),userTicketsList);
        }
        globals.makeList(globals.trainLinkedList,trainsList);
        globals.makeList(globals.stationLinkedList,stationsList);
        globals.makeList(globals.reservationsLinkedList,pendingReservationsList);
        globals.makeList(globals.userLinkedList,usersList);
    }

    AdminGUI() {
        setContentPane(mainPanel);
        setTitle("Admin panel");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        updateAll();

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usersList.getSelectedIndex() != -1 && usersList.getModel().getSize() > 0) {
                    new newUser(globals.userLinkedList.get(usersList.getSelectedIndex()));
                    updateAll();
                } else
                    JOptionPane.showMessageDialog(mainPanel, "Please select a user.", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newUser(true);
                updateAll();
            }
        });
        dropUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usersList.getSelectedIndex() != -1 && usersList.getModel().getSize() > 0) {
                    User user=globals.userLinkedList.get(usersList.getSelectedIndex());
                    globals.userLinkedList.remove(user);
                    globals.reservationsLinkedList.removeIf(reservation -> reservation.getUser() == user);
                }
                JOptionPane.showMessageDialog(mainPanel, "User Removed");
            }
        });
        selectUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usersList.getSelectedIndex() != -1 && usersList.getModel().getSize() > 0)
                    selectedUser = globals.userLinkedList.get(usersList.getSelectedIndex());
                updateAll();
            }
        });
        dropReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUser!= null){
                    globals.reservationsLinkedList.removeIf(
                            reservation ->
                                    selectedUser.getReservations().get(reservationsList.getSelectedIndex())
                                            == reservation);

                    selectedUser.removeFromReservations(selectedUser.getReservations().get(reservationsList.getSelectedIndex()));
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a user.");

                }
                updateAll();
                JOptionPane.showMessageDialog(mainPanel, "Reservation dropped.");

            }
        });
        approveReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUser!= null&&reservationsList.getSelectedIndex() != -1 && reservationsList.getModel().getSize() > 0) {
                    Reservation selectedRes = selectedUser.getReservations().get(reservationsList.getSelectedIndex());
                    selectedUser.addToTickets(new Ticket(selectedRes));
                    selectedRes.getTrain().setBookedSeats(selectedRes.getTrain().getBookedSeats()+1);
                    selectedUser.removeFromReservations(selectedRes);
                    globals.reservationsLinkedList.remove(selectedRes);
                    updateAll();
                    JOptionPane.showMessageDialog(mainPanel, "Ticket issued.");
                }
                else{
                    JOptionPane.showMessageDialog(mainPanel, "User or ticket not selected.","Error",JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        dropUserTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUser!= null&&userTicketsList.getSelectedIndex() != -1 && userTicketsList.getModel().getSize() > 0){
                    selectedUser.removeFromTickets(selectedUser.getTickets().get(userTicketsList.getSelectedIndex()));
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a user and a ticket.");

                }
                updateAll();
                JOptionPane.showMessageDialog(mainPanel, "Reservation dropped.");
            }
        });
        //print user ticket button

        editTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trainsList.getSelectedIndex() != -1 && trainsList.getModel().getSize() > 0) {
                    Train train = globals.trainLinkedList.get(trainsList.getSelectedIndex());
                    new newTrain(train);
                    updateAll();
                } else
                    JOptionPane.showMessageDialog(mainPanel, "Please select a train.", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
        dropTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (trainsList.getSelectedIndex() != -1 && trainsList.getModel().getSize() > 0) {
                    Train train = globals.trainLinkedList.get(trainsList.getSelectedIndex());
                    globals.trainLinkedList.remove(train);
                    JOptionPane.showMessageDialog(mainPanel, "Train Removed");
                }
                else{
                    JOptionPane.showMessageDialog(mainPanel, "Please select a train.","ERROR",JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        newTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newTrain();
                updateAll();
            }
        });

        newStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newStation();
            }
        });
        dropStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stationsList.getModel().getSize()>0&&stationsList.getSelectedIndex()!=-1){
                    globals.stationLinkedList.remove(stationsList.getSelectedIndex());
                    JOptionPane.showMessageDialog(mainPanel, "Reservation dropped.");
                    updateAll();
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a station.");

                }
            }
        });

        rejectReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservation res=globals.reservationsLinkedList.get(pendingReservationsList.getSelectedIndex());
                if(pendingReservationsList.getModel().getSize()>0&&pendingReservationsList.getSelectedIndex()!=-1){
                    globals.reservationsLinkedList.remove(res);
                    res.getUser().removeFromReservations(res);
                    JOptionPane.showMessageDialog(mainPanel, "Reservation dropped.");
                    updateAll();
                }
                else
                    JOptionPane.showMessageDialog(mainPanel, "Please select a reservation.");
            }
        });
        approveReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pendingReservationsList.getSelectedIndex() != -1 && pendingReservationsList.getModel().getSize() > 0) {
                    Reservation selectedRes = globals.reservationsLinkedList.get(pendingReservationsList.getSelectedIndex());
                    selectedRes.getUser().addToTickets(new Ticket(selectedRes));
                    selectedRes.getTrain().setBookedSeats(selectedRes.getTrain().getBookedSeats()+1);

                    selectedRes.getUser().removeFromReservations(selectedRes);
                    globals.reservationsLinkedList.remove(selectedRes);

                    updateAll();
                    JOptionPane.showMessageDialog(mainPanel, "Ticket issued.");
                }
                else{
                    JOptionPane.showMessageDialog(mainPanel, "User or ticket not selected.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAll();
            }
        });

        printUserTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUser != null) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    // Set the printable object
                    Ticket selectedT=selectedUser.getTickets().get(userTicketsList.getSelectedIndex());
                    PrintTicket printable = new PrintTicket(selectedT);
                    job.setPrintable(printable);

                    // Show the print dialog and print the document
                    if (job.printDialog()) {
                        try {
                            job.print();
                            JOptionPane.showMessageDialog(mainPanel, "Print successful.");
                            return;
                        } catch (PrinterException x) {
                            JOptionPane.showMessageDialog(mainPanel, Arrays.toString(x.getStackTrace()), "ERROR", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(mainPanel, "Please select a user.", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
    }
}

