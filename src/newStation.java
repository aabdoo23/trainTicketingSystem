import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newStation extends JFrame{
    private JTextField tfID;
    private JTextField tfName;
    private JTextField tfCity;
    private JTextField tfCountry;
    private JButton saveButton;
    private JPanel mainPanel;

    public newStation() {
        setContentPane(mainPanel);
        setTitle("Sign up");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(350, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        int id=globals.createNewID(globals.stationsIDs);
        tfID.setText(Integer.toString(id));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globals.stationLinkedList.add(new Station(id,tfName.getText().trim(),tfCity.getText().trim(),tfCountry.getText().trim()));
                JOptionPane.showMessageDialog(mainPanel, "Station registered");

                dispose();
            }
        });
    }
}
