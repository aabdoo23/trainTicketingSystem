import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class newUser extends JFrame {
    private JTextField tfID;
    private JTextField tfEmail;
    private JTextField tfFN;
    private JTextField tfPN;
    private JButton saveButton;
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JCheckBox addCreditCardCheckBox;
    private JTextField tfCN;
    private JTextField tfED;
    private JTextField tfCHN;
    private JTextField tfCVV;

    newUser(User user) {
        setContentPane(mainPanel);
        setTitle("Edit");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(850, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        int id = user.getID();
        tfID.setText(Integer.toString(id));
        tfFN.setText(user.getName());
        tfPN.setText(user.getPhoneNumber());
        tfEmail.setText(user.getEmail());
        passwordField1.setText(user.getPassword());
        passwordField2.setText(user.getPassword());
        tfCN.setText(user.getCard().getCardNumber());
        tfCHN.setText(user.getCard().getHolderName());
        tfCVV.setText(Integer.toString(user.getCard().getCVV()));
        tfED.setText(user.getCard().getExpDate());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String first = tfFN.getText();
                String pn = tfPN.getText();
                String pw = passwordField1.getText();
                if (tfEmail.getText().isEmpty() || tfFN.getText().isEmpty() || tfPN.getText().isEmpty() || pw.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Please fill all information", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!Objects.equals(passwordField1.getText(), passwordField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Error: Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(addCreditCardCheckBox.isSelected()){
                    if (tfCN.getText().isEmpty() ||tfCN.getText().length()!=16|| tfCHN.getText().isEmpty() || tfCVV.getText().isEmpty() || tfED.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Please fill all card information and check your info", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                globals.userLinkedList.remove(user);
                User editedEmployee = new User(id, first, pw, email, pn);
                if(addCreditCardCheckBox.isSelected()){
                    editedEmployee.setCard(new CreditCard(tfCN.getText(),Integer.parseInt(tfCVV.getText()),tfED.getText(),tfCHN.getName()));
                }
                globals.userLinkedList.add(editedEmployee);
                JOptionPane.showMessageDialog(mainPanel, "Info updated");
                dispose();
            }
        });
        addCreditCardCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addCreditCardCheckBox.isSelected()){
                    tfCN.setEnabled(true);
                    tfED.setEnabled(true);
                    tfCVV.setEnabled(true);
                    tfCHN.setEnabled(true);
                }
                else if(!addCreditCardCheckBox.isSelected()){
                    tfCN.setEnabled(false);
                    tfED.setEnabled(false);
                    tfCVV.setEnabled(false);
                    tfCHN.setEnabled(false);
                }
            }
        });
    }
    newUser(boolean admin) {
        setContentPane(mainPanel);
        setTitle("Sign up");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(850, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        int id = globals.createNewID(globals.usersIDs);
        tfID.setText(Integer.toString(id));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String first = tfFN.getText();
                String pn = tfPN.getText();
                String pw = passwordField1.getText();
                if (tfEmail.getText().isEmpty() || tfFN.getText().isEmpty() || tfPN.getText().isEmpty() || pw.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Please fill all information", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!Objects.equals(passwordField1.getText(), passwordField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Error: Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(addCreditCardCheckBox.isSelected()){
                    if (tfCN.getText().isEmpty() ||tfCN.getText().length()!=16|| tfCHN.getText().isEmpty() || tfCVV.getText().isEmpty() || tfED.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Please fill all card information and check your info", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                User user = new User(id, first, email, pw, pn);
                if(addCreditCardCheckBox.isSelected()){
                    user.setCard(new CreditCard(tfCN.getText(),Integer.parseInt(tfCVV.getText()),tfED.getText(),tfCHN.getName()));
                }
                globals.userLinkedList.add(user);
                JOptionPane.showMessageDialog(mainPanel, "User Registered");

                dispose();
            }
        });
        addCreditCardCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addCreditCardCheckBox.isSelected()){
                    tfCN.setEnabled(true);
                    tfED.setEnabled(true);
                    tfCVV.setEnabled(true);
                    tfCHN.setEnabled(true);
                }
                else if(!addCreditCardCheckBox.isSelected()){
                    tfCN.setEnabled(false);
                    tfED.setEnabled(false);
                    tfCVV.setEnabled(false);
                    tfCHN.setEnabled(false);
                }
            }
        });
    }
}
