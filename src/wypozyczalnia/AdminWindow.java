package wypozyczalnia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Basian
 */
public class AdminWindow extends JFrame implements ActionListener {

    private final JButton btn_Search, btn_Change, btn_Add, btn_Delete;
    private final JTextField txtFld_ID, txtFld_Name, txtFld_Availability, txtFld_Place, txtFld_Date, txtFld_Delete;
    private final JLabel l_ID, l_Name, l_Availability, l_Place, l_Date, l_IDU, l_tableID, l_tableName, l_tableAvail, l_tablePlace, l_tableDate;
    private final JSeparator separatorTop, separatorBot;
    public JTable table;

    Metody method = new Metody();

    public AdminWindow() {

        setSize(920, 450);
        setTitle("Wypozyczalnia");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        btn_Search = new JButton("Pokaz wszystkie przedmioty");
        btn_Search.setBounds(350, 20, 300, 30);
        add(btn_Search);
        btn_Search.addActionListener(this);

        separatorTop = new JSeparator(SwingConstants.HORIZONTAL);
        add(separatorTop);
        separatorTop.setBounds(0, 64, 900, 2);

        separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
        add(separatorBot);
        separatorBot.setBounds(560, 300, 330, 2);

        table = new JTable();
        table.setFillsViewportHeight(true);
        add(table);
        table.setBounds(10, 100, 550, 330);

        l_tableID = new JLabel("ID przedmiotu");
        add(l_tableID);
        l_tableID.setBounds(25, 75, 100, 20);

        l_tableName = new JLabel("Nazwa");
        add(l_tableName);
        l_tableName.setBounds(145, 75, 100, 20);

        l_tableAvail = new JLabel("Dostepnosc");
        add(l_tableAvail);
        l_tableAvail.setBounds(250, 75, 100, 20);

        l_tablePlace = new JLabel("Miejsce");
        add(l_tablePlace);
        l_tablePlace.setBounds(365, 75, 100, 20);

        l_tableDate = new JLabel("Do kiedy");
        add(l_tableDate);
        l_tableDate.setBounds(470, 75, 100, 20);

        l_ID = new JLabel("ID: ");
        add(l_ID);
        l_ID.setBounds(600, 100, 20, 20);

        txtFld_ID = new JTextField();
        txtFld_ID.setBounds(620, 100, 100, 20);
        add(txtFld_ID);

        l_Name = new JLabel("Nazwa:");
        l_Name.setBounds(750, 100, 60, 20);
        add(l_Name);

        txtFld_Name = new JTextField();
        txtFld_Name.setBounds(800, 100, 100, 20);
        add(txtFld_Name);

        l_Place = new JLabel("ID miejsca: ");
        l_Place.setBounds(565, 140, 50, 20);
        add(l_Place);

        txtFld_Place = new JTextField();
        txtFld_Place.setBounds(620, 140, 100, 20);
        add(txtFld_Place);

        l_Availability = new JLabel("Dostepnosc: ");
        l_Availability.setBounds(727, 140, 100, 20);
        add(l_Availability);

        txtFld_Availability = new JTextField();
        txtFld_Availability.setBounds(800, 140, 100, 20);
        add(txtFld_Availability);

        l_Date = new JLabel("Data(YYYY-MM-DD): ");
        l_Date.setBounds(620, 180, 150, 20);
        add(l_Date);

        txtFld_Date = new JTextField();
        txtFld_Date.setBounds(730, 180, 100, 20);
        add(txtFld_Date);

        btn_Change = new JButton("Edytuj");
        btn_Change.setBounds(640, 220, 100, 25);
        add(btn_Change);
        btn_Change.addActionListener(this);

        btn_Add = new JButton("Dodaj");
        btn_Add.setBounds(750, 220, 100, 25);
        add(btn_Add);
        btn_Add.addActionListener(this);

        l_IDU = new JLabel("ID:");
        l_IDU.setBounds(600, 350, 20, 20);
        add(l_IDU);

        txtFld_Delete = new JTextField();
        txtFld_Delete.setBounds(620, 350, 100, 20);
        add(txtFld_Delete);

        btn_Delete = new JButton("Usun");
        btn_Delete.setBounds(760, 340, 100, 30);
        add(btn_Delete);
        btn_Delete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == btn_Search) {

            try {
                method.connect();
                method.execSearch(table,"SELECT idprzedmiotu,nazwa,dostepny,nazwamiejsca,dokiedy FROM przedmioty,miejsca "
                        + "WHERE przedmioty.idmiejsca = miejsca.idmiejsca ORDER BY nazwa");
                method.disconnect();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (source == btn_Add) {
            String tfID = txtFld_ID.getText();
            String tfNazwa = txtFld_Name.getText();
            String tfDost = txtFld_Availability.getText();
            String tfMiejsce = txtFld_Place.getText();
            String tfData = txtFld_Date.getText();

            try {
                method.connect();
                method.execAdd("INSERT INTO przedmioty VALUES('" + tfID + "','" + tfNazwa + "','" + tfDost + "','" + tfMiejsce + "','" + tfData + "')");
                method.disconnect();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (source == btn_Delete) {

            String tffID = txtFld_Delete.getText();
            try {
                method.connect();
                method.execDelete("DELETE FROM przedmioty WHERE idprzedmiotu =" + tffID);
                method.disconnect();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == btn_Change) {

            String tfID = txtFld_ID.getText();
            String tfNazwa = txtFld_Name.getText();
            String tfDost = txtFld_Availability.getText();
            String tfMiejsce = txtFld_Place.getText();
            String tfData = txtFld_Date.getText();

            try {

                method.connect();
                method.execChange("UPDATE przedmioty SET nazwa = '" + tfNazwa + "' , dostepny = '" + tfDost
                        + "' , idmiejsca ='" + tfMiejsce + "' , dokiedy = '" + tfData + "' WHERE idprzedmiotu ='" + tfID + "';");
                method.disconnect();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
