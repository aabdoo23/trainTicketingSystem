import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class newTrain extends JFrame {
    private JPanel mainPanel;
    private JTextField tfID;
    private JTextField tfPrice;
    private JComboBox<String> cbDeparture;
    private JComboBox<String> cbArrival;
    private JSpinner DepHrSpinner;
    private JSpinner DepMinSpinner;
    private JSpinner arrivalHrsSpinner;
    private JSpinner arrivalMinSpinner;
    private JButton saveButton;
    private JTextField tfModel;
    private JTextField tfNumberOfSeats;
    SpinnerNumberModel hourModelA = new SpinnerNumberModel(0, 0, 24, 1);
    SpinnerNumberModel hourModelB = new SpinnerNumberModel(0, 0, 24, 1);

    SpinnerNumberModel minModelA = new SpinnerNumberModel(0, 0, 60, 1);
    SpinnerNumberModel minModelB = new SpinnerNumberModel(0, 0, 60, 1);

    LocalTime arr = LocalTime.MIDNIGHT, dep = LocalTime.MIDNIGHT;

    newTrain() {
        setContentPane(mainPanel);
        setTitle("New Train");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        globals.makeList(globals.stationLinkedList,cbArrival);
        globals.makeList(globals.stationLinkedList,cbDeparture);
        int id = globals.createNewID(globals.trainsIDs);
        tfID.setText(Integer.toString(id));
        DepHrSpinner.setModel(hourModelA);
        DepMinSpinner.setModel(minModelA);
        arrivalHrsSpinner.setModel(hourModelB);
        arrivalMinSpinner.setModel(minModelB);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfPrice.getText().isEmpty()||tfModel.getText().isEmpty()||tfNumberOfSeats.getText().isEmpty()||cbArrival.getSelectedIndex()==-1||cbDeparture.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(mainPanel, "Please fill out all info","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(Integer.parseInt(tfNumberOfSeats.getText())<25){
                    JOptionPane.showMessageDialog(mainPanel, "Train can't have less than 25 seats","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                arr = arr.withHour((int) arrivalHrsSpinner.getValue());
                arr = arr.withMinute((int) arrivalMinSpinner.getValue());
                dep = dep.withHour((int) DepHrSpinner.getValue());
                dep = dep.withMinute((int) DepMinSpinner.getValue());
                Station src = globals.stationLinkedList.get(cbDeparture.getSelectedIndex()), arrv = globals.stationLinkedList.get(cbArrival.getSelectedIndex());
                Train train = new Train(id, Double.parseDouble(tfPrice.getText()), tfModel.getText(), src, arrv, arr, dep, Integer.parseInt(tfNumberOfSeats.getText()));
                globals.trainLinkedList.add(train);
                JOptionPane.showMessageDialog(mainPanel, "Train Registered");
                dispose();

            }
        });
    }

    newTrain(Train toBeEditedTrain) {
        setContentPane(mainPanel);
        setTitle("Edit Train");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        globals.makeList(globals.stationLinkedList,cbArrival);
        globals.makeList(globals.stationLinkedList,cbDeparture);
        DepHrSpinner.setModel(hourModelA);
        DepMinSpinner.setModel(minModelA);
        arrivalHrsSpinner.setModel(hourModelB);
        arrivalMinSpinner.setModel(minModelB);
        int id = toBeEditedTrain.getID();
        double oldPrice = toBeEditedTrain.getBasePrice();
        String oldModel = toBeEditedTrain.getTrainModel();
        Station oldSrc = toBeEditedTrain.getSourceStation();
        Station oldDes = toBeEditedTrain.getDestinationStation();
        LocalTime oldDepTime = toBeEditedTrain.getDepartureTime();
        LocalTime oldArrTime = toBeEditedTrain.getArrivalTime();
        int oldNumOfSeats = toBeEditedTrain.getNumberOfSeats();
        tfID.setText(Integer.toString(id));
        tfModel.setText(oldModel);
        tfPrice.setText(Double.toString(oldPrice));
        tfNumberOfSeats.setText(Integer.toString(oldNumOfSeats));
        cbDeparture.setSelectedIndex(globals.stationLinkedList.indexOf(oldSrc));
        cbArrival.setSelectedIndex(globals.stationLinkedList.indexOf(oldDes));
        arrivalHrsSpinner.setValue(oldArrTime.getHour());
        arrivalMinSpinner.setValue(oldArrTime.getMinute());
        DepHrSpinner.setValue(oldDepTime.getHour());
        DepMinSpinner.setValue(oldArrTime.getMinute());
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(tfNumberOfSeats.getText())<25){
                    JOptionPane.showMessageDialog(mainPanel, "Train can't have less than 25 seats","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(tfPrice.getText().isEmpty()||tfModel.getText().isEmpty()||tfNumberOfSeats.getText().isEmpty()||cbArrival.getSelectedIndex()==-1||cbDeparture.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(mainPanel, "Please fill out all info","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                arr = arr.withHour((int) arrivalHrsSpinner.getValue());
                arr = arr.withMinute((int) arrivalMinSpinner.getValue());
                dep = dep.withHour((int) DepHrSpinner.getValue());
                dep = dep.withMinute((int) DepMinSpinner.getValue());
                Station src = globals.stationLinkedList.get(cbDeparture.getSelectedIndex()), arrv = globals.stationLinkedList.get(cbArrival.getSelectedIndex());
                Train train = new Train(id, Double.parseDouble(tfPrice.getText()), tfModel.getText(), src, arrv, arr, dep, Integer.parseInt(tfNumberOfSeats.getText()));
                globals.trainLinkedList.add(train);
                globals.trainLinkedList.remove(toBeEditedTrain);
                JOptionPane.showMessageDialog(mainPanel, "Train Edited");
                dispose();
            }
        });
    }
}
