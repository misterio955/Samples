package wypozyczalnia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Basian
 */
public class Metody extends JFrame{

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    public void connect() throws ClassNotFoundException, SQLException {

        Class sterownik = Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wypozyczalnia", "root", "");

    }

    public void disconnect() throws SQLException {
        resultset.close();
        statement.close();
        connection.close();

    }

    
    public void changeDate(String sql) throws SQLException {
        try {
            statement = connection.createStatement();
            int wynikUpdate = statement.executeUpdate(sql);

       
        } catch (SQLException ex) {
            String komunikat9 = "Wystąpił błąd podczas próby aktualizacji bazy danych";
            JOptionPane.showMessageDialog(null, komunikat9);
         
        }
    }
    
    public void execAdd(String sql) throws SQLException {
        try {
            statement = connection.createStatement();
            int wynikUpdate = statement.executeUpdate(sql);

            if (wynikUpdate > 0) {
                String komunikat1 = "Dodano nowy rekord.";
                JOptionPane.showMessageDialog(null, komunikat1);
            } else {
                String komunikat2 = "Nie dodano zadnego rekordu";
                JOptionPane.showMessageDialog(null, komunikat2);
            }

        } catch (SQLException ex) {
            String komunikat3 = "Wystąpił błąd podczas próby aktualizacji bazy danych";
            JOptionPane.showMessageDialog(null, komunikat3);
        }
    }

    public void execChange(String sql) throws SQLException {
        try {
            statement = connection.createStatement();
            int wynikUpdate = statement.executeUpdate(sql);

            if (wynikUpdate > 0) {
                String komunikat4 = "Zmodyfikowano rekord.";
                JOptionPane.showMessageDialog(null, komunikat4);
            } else {
                String komunikat5 = "Nie zmodyfikowano zadnego rekordu";
                JOptionPane.showMessageDialog(null, komunikat5);
            }

        } catch (SQLException ex) {
            String komunikat6 = "Wystąpił błąd podczas próby aktualizacji bazy danych";
            JOptionPane.showMessageDialog(null, komunikat6);
        }
    }

    public void execDelete(String sql) throws SQLException {
        try {
            statement = connection.createStatement();
            int wynikUpdate = statement.executeUpdate(sql);

            if (wynikUpdate > 0) {
                String komunikat7 = "Usunieto rekord.";
                JOptionPane.showMessageDialog(null, komunikat7);
            } else {
                String komunikat8 = "Nie usunieto zadnego rekordu";
                JOptionPane.showMessageDialog(null, komunikat8);
            }

        } catch (SQLException ex) {
            String komunikat9 = "Wystąpił błąd podczas próby aktualizacji bazy danych";
            JOptionPane.showMessageDialog(null, komunikat9);
        }
    }

    /**
     *
     * @param table
     * @param sql
     * @throws SQLException
     */
    public void execSearch(JTable table,String sql) throws SQLException {

        String rezultat = "";
        statement = connection.createStatement();
        resultset = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultset.getMetaData();

        int liczbaKol = rsmd.getColumnCount();

        String[] colNames = new String[liczbaKol];
        for (int i = 1; i <= liczbaKol; i++) {
            colNames[i - 1] = rsmd.getColumnName(i);
        }

        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        table.setModel(model);

        while (resultset.next()) {

            String[] row = new String[liczbaKol];

            String linia = new String();
            for (int i = 1; i <= liczbaKol; i++) {

                if (i > 1) {
                    linia = linia + " ";
                }

                String makapaka = resultset.getString(i);
                if (makapaka.equals("1")) {
                    linia = linia + "Dostępny";
                } else {
                    linia = linia + resultset.getString(i);
                }
                row[i - 1] = resultset.getString(i);

            }
            model.addRow(row);
            rezultat += linia + "\n";
        }

    }
    
    public void makeNote(BufferedWriter reader, String imie, String nazwisko, String id, String currentDate, String czas,String termin) throws IOException{
        
                reader.write("~~~~~~~~   WYPOZYCZENIE   ~~~~~~~~~");
                reader.newLine();
                reader.newLine();
                reader.write(imie);
                reader.newLine();
                reader.write(nazwisko);
                reader.newLine();
                reader.write(id);
                reader.newLine();
                reader.write(currentDate);
                reader.newLine();
                reader.write(czas);
                reader.newLine();
                reader.write(termin);
                reader.newLine();
                reader.newLine();
                reader.write("PODPIS:       ......................");
                reader.close();
    }
    
    
}
