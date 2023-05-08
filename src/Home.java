import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class Home extends JFrame {
    private JPanel mainPanel;
    private JTextField tfID;
    private JButton signUpButton;
    private JButton adminButton;
    private JButton signInButton;
    private JPasswordField pfPW;
    private JButton saveButton;

    Home() {
        setContentPane(mainPanel);
        setTitle("Attendance Report");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420,180);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newUser(false);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminGUI();
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfID.getText().isEmpty()||pfPW.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(Objects.equals(tfID.getText().trim(), "admin") && Objects.equals(pfPW.getText().trim(), "admin")){
                    new AdminGUI();
                    tfID.setText("");
                    pfPW.setText("");
                    return;
                }

                for (User user: globals.userLinkedList) {
                    if (user.getID() == Integer.parseInt(tfID.getText().trim()) && Objects.equals(user.getPassword(), pfPW.getText())) {
                        new userMenu(user);
                        tfID.setText("");
                        pfPW.setText("");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Error: Credentials don't match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DB();
                try {
                    DB.setAllUsers();
                    DB.setAllStations();
                    DB.setAllTrains();
                    DB.setAllReservations();
                    DB.setAllTickets();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }




}
