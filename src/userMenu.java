import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class userMenu extends JFrame {
    private JPanel mainPanel;
    private JTextField tfID;
    private JTextField tfEmail;
    private JTextField tfName;
    private JTextField tfPN;
    private JTextField tfCardInfo;
    private JButton refreshButton;
    private JTabbedPane tabbedPane1;
    private JComboBox<String> fromCB;
    private JComboBox<String> toCB;
    private JButton showTrainsButton;
    private JList<String> fromToList;
    private JButton bookTripButton;
    private JButton trainPathFinderButton;
    User user;

    void updateDisplay() {
        tfID.setText(Integer.toString(user.getID()));
        tfEmail.setText(user.getEmail());
        tfName.setText(user.getName());
        tfPN.setText(user.getPhoneNumber());
        if(user.getCard()!=null)
            tfCardInfo.setText(user.getCard().discreteToString());
        globals.makeList(globals.stationLinkedList, fromCB);
        globals.makeList(globals.stationLinkedList, toCB);
    }
    LinkedList<Train>trains=new LinkedList<>();


    userMenu(User user1) {
        setContentPane(mainPanel);
        setTitle("User Menu");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1222, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        this.user = user1;
        updateDisplay();
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });

        showTrainsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fromCB.getSelectedIndex()==toCB.getSelectedIndex()) {
                    JOptionPane.showMessageDialog(null, "Can't pick departure station same as arrival", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                trains=new LinkedList<>();
                for (Train train:globals.trainLinkedList){
                    if(train.getSourceStation()==globals.stationLinkedList.get(fromCB.getSelectedIndex())&&
                            train.getDestinationStation()==globals.stationLinkedList.get(toCB.getSelectedIndex())){
                        trains.add(train);
                    }
                }
                if(trains!=null)globals.makeList(trains,fromToList);
            }
        });

        bookTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind=fromToList.getSelectedIndex();
                Train train1=trains.get(ind);
                for (Train train:globals.trainLinkedList){
                    if (train==train1){
                        new newReservation(user,train);
                    }
                }
            }
        });
        trainPathFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainPathFinder(user1);
            }
        });
    }

}
