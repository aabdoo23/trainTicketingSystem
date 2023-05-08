import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class newReservation extends JFrame{
    private JPanel mainPanel;
    private JTextField fromTF;
    private JTextField toTF;
    private JTextField trainIDtf;
    private JTextField IDtf;
    private JComboBox<String> classCB;
    private JComboBox<String> paymentCB;
    private JButton confirmReservationButton;
    private JLabel priceLabel;
    private JButton updatePricingButton;
    double p,pp;
    void updatePricing(){
        switch (classCB.getSelectedIndex()) {
            case 0 -> p =pp* 2.8;
            case 1 -> p =pp* 2.5;
            case 2 -> p =pp* 2.2;
            case 3 -> p =pp* 2;
            case 4 -> p =pp* 1.8;
            case 5 -> p =pp* 1.2;
        }
        priceLabel.setText(Double.toString(p));

    }
    newReservation(User user,Train train){
        if(user.getCard()==null){
            paymentCB.setSelectedIndex(1);
            paymentCB.setEnabled(false);
        }
        setContentPane(mainPanel);
        setTitle("New reservation");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(750, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        int id = globals.createNewID(globals.reservationsIDs);
        IDtf.setText(Integer.toString(id));
        for (Station station:globals.stationLinkedList){
            if(station==train.getSourceStation()){
                fromTF.setText(station.toString());
                break;
            }
        }
        for (Station station:globals.stationLinkedList){
            if(station==train.getDestinationStation()){
                toTF.setText(station.toString());
                break;
            }
        }
        trainIDtf.setText(Integer.toString(train.getID()));
        pp=train.getBasePrice();
        p=pp*2.8;
        updatePricing();

        classCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePricing();
            }
        });

        confirmReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePricing();
                Reservation reservation=new Reservation(id,p,user,train);
                globals.reservationsLinkedList.add(reservation);
                user.addToReservations(reservation);
                JOptionPane.showMessageDialog(null, "Reservation complete, pending approval");

                dispose();
            }
        });
        updatePricingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePricing();
            }
        });
    }
}
