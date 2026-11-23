import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Speicher {
    // hier wird das Laden und Speichern von Mitarbeitern geregelt

    // KONSTANTEN
    private static final String TRENNZEICHEN = ",";
    private static final String DATENBANK = "personalverwaltung";
    private static final String DATEI_PFAD = "jdbc:sqlite:Daten/personalverwaltung.db";
    private static final String SQL_INSERT = "INSERT INTO " + DATENBANK +
            "(" + String.join(", ", Mitarbeiter.FELDER_SQL) + ") VALUES (" + "?, ".repeat(Mitarbeiter.FELDER_SQL.length - 1) + "?" + ")";
    private static final String SQL_DELETE = "DELETE FROM " + DATENBANK + " WHERE id = ?";
    private static final String SQL_QUERY = "SELECT * FROM " + DATENBANK;

    // Konstruktor
    public Speicher() {
        try (Connection conn = verbinden();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTable());
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }

    // verbinden mit der Datenbank
    private Connection verbinden() throws SQLException {
        return DriverManager.getConnection(DATEI_PFAD);
    }

    // verbinden mit der Datenbank
    private String createTable() {
        StringBuilder createTable = new StringBuilder("CREATE TABLE IF NOT EXISTS " + DATENBANK + " (Id INTEGER PRIMARY KEY");
        for (String feld : Mitarbeiter.FELDER_SQL) {
            createTable.append(", ");
            createTable.append(feld);
            createTable.append(" TEXT");
        }
        createTable.append(")");
        return createTable.toString();
    }

    // neuer Mitarbeiter einfügen
    public boolean einfuegen(Mitarbeiter m) throws SQLException {
        try (Connection conn = verbinden();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
            String[] werte = m.alsZeile();
            for (int i = 1; i < werte.length; i++) {
                pstmt.setString(i, werte[i]);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    // Mitarbeiter löschen / entfernen
    public boolean loeschen(Mitarbeiter m) throws SQLException {
        try (Connection conn = verbinden();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {
            String id = m.getId();
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Daten vom Mitarbeiter ändern
    public boolean aendern(Mitarbeiter m, String feld, String wert) throws SQLException {
        // Check ob die Spaltenname vorkommt in der Datenbank
        boolean gefunden = false;
        for (String spalte : Mitarbeiter.FELDER_SQL) {
            if (spalte.equals(feld)) {
                gefunden = true;
                break;
            }
        }
        if (!gefunden) return false;

        String sqlUpdate = "UPDATE " + DATENBANK + " SET " + feld + " = ? WHERE id = ?";
        try (Connection conn = verbinden();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, wert);
            pstmt.setString(2, m.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    // SQL Datenbank in einer ArrayList umsetzen
    public List<Mitarbeiter> laden() throws SQLException {
        List<Mitarbeiter> liste = new ArrayList<>();
        try (Connection conn = verbinden();
             PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY);
             ResultSet res = pstmt.executeQuery()
        ) {
            while (res.next()) {
                String[] werte = new String[Mitarbeiter.SPALTEN_TABELLE.length];
                for (int i = 0; i < Mitarbeiter.SPALTEN_TABELLE.length; i++) {
                    werte[i] = res.getString(i + 1);
                }
                liste.add(Mitarbeiter.ausZeile(werte));
            }
            return liste;
        }
    }

    public static void main(String[] args) {


    }
}
