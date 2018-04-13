package wypozyczalnia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Basian
 */
public class LoginWindow extends JFrame implements ActionListener {

    private final JButton btn_Login, btn_Exit;
    private final JCheckBox chkBox_Admin;
    private final JTextField nick;
    private final JPasswordField password;
    private final JLabel l_nick, l_password, l_tipTop, l_tipBot;

    public LoginWindow() {

        setSize(300, 270);
        setTitle("LOGOWANIE");
        setLayout(null);
        setLocation(500, 250);

        chkBox_Admin = new JCheckBox("ADMINISTRATOR");
        chkBox_Admin.setBounds(10, 20, 200, 25);
        chkBox_Admin.addActionListener(this);
        add(chkBox_Admin);

        btn_Login = new JButton("Loguj");
        btn_Login.setBounds(40, 150, 100, 25);
        add(btn_Login);
        btn_Login.addActionListener(this);

        btn_Exit = new JButton("Wyjscie");
        btn_Exit.setBounds(160, 150, 100, 25);
        add(btn_Exit);
        btn_Exit.addActionListener(this);

        l_nick = new JLabel("Login: ");
        add(l_nick);
        l_nick.setBounds(10, 70, 50, 30);

        nick = new JTextField();
        add(nick);
        nick.setBounds(60, 70, 200, 30);

        l_password = new JLabel("Haslo: ");
        add(l_password);
        l_password.setBounds(10, 100, 50, 30);

        l_tipTop = new JLabel("W celach testowych: Uzytkownik: L: user H: user");
        add(l_tipTop);
        l_tipTop.setBounds(10, 206, 300, 20);

        l_tipBot = new JLabel("Admin: L: admin H: admin");
        add(l_tipBot);
        l_tipBot.setBounds(133, 220, 300, 20);

        password = new JPasswordField();
        add(password);
        password.setBounds(60, 100, 200, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == btn_Login && chkBox_Admin.isSelected() == false) {
            String takeNick = nick.getText();
            String takeHaslo = password.getText();

            if (takeNick.equals("user") && takeHaslo.equals("user")) {
                new UserWindow().show();
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadzono nieodpowiednie dane!");
            }
        } else if (source == btn_Login && chkBox_Admin.isSelected() == true) {

            String takeNick = nick.getText();
            String takeHaslo = password.getText();
            if (takeNick.equals("admin") && takeHaslo.equals("admin")) {
                new AdminWindow().show();
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadzono nieodpowiednie dane!");
            }
        } else if (source == btn_Exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        LoginWindow log = new LoginWindow();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setVisible(true);
        log.setResizable(false);
    }
}
