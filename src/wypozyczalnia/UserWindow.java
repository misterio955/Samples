package wypozyczalnia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author Basian
 */
public class UserWindow extends JFrame implements ActionListener {

    private final JButton btnSearch, btnBorrow;
    private final JTextField txtFld_Name, txtFld_LastName, txtFld_ID;
    private final JLabel l_Name, l_LastName, l_ID, l_Time, l_Token, l_Availability, l_Date, l_Place, l_tableID;
    private final ButtonGroup btnGrp_Time;
    private final JRadioButton rb_opt1, rb_opt2, rb_opt3, rb_opt4;
    private final JSeparator separator;
    public JTable table;

    private String calendar_time;
    private int numDays;
    private final String textFile = "C:/java/wypozyczenie.txt";
    Metody method = new Metody();

    public UserWindow() {

        setSize(920, 450);
        setTitle("Wypozyczalnia");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        table = new JTable();
        table.setBounds(10, 100, 690, 310);
        table.setFillsViewportHeight(true);
        add(table);

        l_tableID = new JLabel("ID przedmiotu");
        add(l_tableID);
        l_tableID.setBounds(35, 75, 100, 20);

        l_Token = new JLabel("Nazwa");
        add(l_Token);
        l_Token.setBounds(195, 75, 100, 20);

        l_Availability = new JLabel("Dostepnosc");
        add(l_Availability);
        l_Availability.setBounds(320, 75, 100, 20);

        l_Place = new JLabel("Miejsce");
        add(l_Place);
        l_Place.setBounds(465, 75, 100, 20);

        l_Date = new JLabel("Do kiedy");
        add(l_Date);
        l_Date.setBounds(600, 75, 100, 20);

        btnSearch = new JButton("Pokaz wszystkie przedmioty");
        btnSearch.setBounds(300, 20, 300, 30);
        add(btnSearch);
        btnSearch.addActionListener(this);

        btnBorrow = new JButton("Wypozycz");
        btnBorrow.setBounds(730, 380, 150, 30);
        add(btnBorrow);
        btnBorrow.addActionListener(this);

        separator = new JSeparator(SwingConstants.HORIZONTAL);
        add(separator);
        separator.setBounds(0, 62, 940, 2);

        txtFld_Name = new JTextField();
        txtFld_Name.setBounds(785, 120, 100, 20);
        add(txtFld_Name);

        l_Name = new JLabel("Imie: ");
        l_Name.setBounds(750, 120, 100, 20);
        add(l_Name);

        txtFld_LastName = new JTextField();
        txtFld_LastName.setBounds(785, 160, 100, 20);
        add(txtFld_LastName);

        l_LastName = new JLabel("Nazwisko: ");
        l_LastName.setBounds(720, 160, 100, 20);
        add(l_LastName);

        txtFld_ID = new JTextField();
        txtFld_ID.setBounds(785, 200, 100, 20);
        add(txtFld_ID);

        l_ID = new JLabel("ID przedmiotu: ");
        l_ID.setBounds(702, 200, 100, 20);
        add(l_ID);

        l_Time = new JLabel("Czas Wypozyczenia: ");
        add(l_Time);
        l_Time.setBounds(730, 240, 150, 20);

        btnGrp_Time = new ButtonGroup();
        rb_opt1 = new JRadioButton("1 tydzień", true);
        rb_opt1.setBounds(785, 260, 100, 20);
        rb_opt2 = new JRadioButton("2 tygodnie", false);
        rb_opt2.setBounds(785, 290, 100, 20);
        rb_opt3 = new JRadioButton("3 tygodnie", false);
        rb_opt3.setBounds(785, 320, 100, 20);
        rb_opt4 = new JRadioButton("1 miesiąc", false);
        rb_opt4.setBounds(785, 350, 100, 20);
        btnGrp_Time.add(rb_opt1);
        btnGrp_Time.add(rb_opt2);
        btnGrp_Time.add(rb_opt3);
        btnGrp_Time.add(rb_opt4);
        add(rb_opt1);
        add(rb_opt2);
        add(rb_opt3);
        add(rb_opt4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == btnSearch) {
            try {
                method.connect();
                method.execSearch(table,"SELECT idprzedmiotu,nazwa,dostepny,nazwamiejsca,dokiedy FROM przedmioty,miejsca "
                        + "WHERE przedmioty.idmiejsca = miejsca.idmiejsca ORDER BY nazwa");
                method.disconnect();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == btnBorrow) {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String currentDate = "Dzisiejsza data: " + dateFormat.format(calendar.getTime());

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String czasZ = dateFormat1.format(calendar.getTime());

            if (rb_opt1.isSelected()) {
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                calendar_time = dateFormat.format(calendar.getTime());
                numDays = 7;
                czasZ = dateFormat1.format(calendar.getTime());

            } else if (rb_opt2.isSelected()) {
                calendar.add(Calendar.DAY_OF_MONTH, 14);
                calendar_time = dateFormat.format(calendar.getTime());
                numDays = 14;
                czasZ = dateFormat1.format(calendar.getTime());
            } else if (rb_opt3.isSelected()) {
                calendar.add(Calendar.DAY_OF_MONTH, 21);
                calendar_time = dateFormat.format(calendar.getTime());
                numDays = 21;
                czasZ = dateFormat1.format(calendar.getTime());
            } else if (rb_opt4.isSelected()) {
                calendar.add(Calendar.MONTH, 1);
                calendar_time = dateFormat.format(calendar.getTime());
                numDays = 30;
                czasZ = dateFormat1.format(calendar.getTime());
            }

            String imie = "Imie: " + txtFld_Name.getText();
            String nazwisko = "Nazwisko: " + txtFld_LastName.getText();
            String id = "Id przedmiotu: " + txtFld_ID.getText();
            String czas = "Czas wypozyczenia: " + numDays + " dni";
            String termin = "Termin oddania: " + calendar_time;
            int intId = Integer.parseInt(txtFld_ID.getText());

            try {
                BufferedWriter reader = new BufferedWriter(new FileWriter(new File(textFile)));
                method.makeNote(reader, imie, nazwisko, id, currentDate, czas, termin);

                try {
                    method.connect();
                    method.changeDate("UPDATE przedmioty SET dokiedy = '" + czasZ + "' , dostepny ='0' WHERE idprzedmiotu =" + intId + ";");
                    method.disconnect();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Przedmiot został wypozyczony.");

                txtFld_Name.setText("");
                txtFld_LastName.setText("");
                txtFld_ID.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Blad podczas zapisu do pliku!");
            }
        }

    }

}
