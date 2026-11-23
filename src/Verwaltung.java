import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Verwaltung {
    // hier wird das hinzufügen, ändern und löschen von Mitarbeitern geregelt

    // ArrayList mitarbeiterListe wird erstellt und speichert die Mitarbeiter als Objekte
    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
    private Speicher speicher = new Speicher();

    // Konstruktor
    public Verwaltung() {
        laden();
    }

    // returnt die mitarbeiterListe
    public List<Mitarbeiter> getMitarbeiterListe() {
        return mitarbeiterListe;
    }

    // mitarbeiterListe laden aus Speicher
    public void laden() {
        try {
            mitarbeiterListe.clear();
            mitarbeiterListe.addAll(speicher.laden());
        } catch (SQLException e) {
            System.out.println("SQLException Error occurred: " + e.getMessage());
        }
    }

    // mitarbeiterListe drucken
    public void drucken() {
        System.out.println(String.format(Mitarbeiter.FORMAT,
                (Object[]) Mitarbeiter.SPALTEN_DRUCKEN));
        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }
        System.out.println("-".repeat(140));
    }

    // neuer ID generieren
    public int getMaxId() {
        int maxId = 0;
        List<Mitarbeiter> liste = getMitarbeiterListe();
        for (Mitarbeiter m : liste) {
            if (Integer.parseInt(m.getId()) > maxId) maxId = Integer.parseInt(m.getId());
        }
        return maxId;
    }


    // Mitarbeiter hinzufügen
    public boolean addMitarbeiter(Mitarbeiter mitarbeiter) {
        try {
            speicher.einfuegen(mitarbeiter);
            laden();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException Error occurred: " + e.getMessage());
            return false;
        }
    }

    // Mitarbeiter suchen
    public Mitarbeiter searchMitarbeiter(String id) {
        for (Mitarbeiter m : mitarbeiterListe) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    // Nachname vom Mitarbeiter suchen
    public String searchName(String id) {
        for (Mitarbeiter m : mitarbeiterListe) {
            if (m.getId().equals(id)) return m.getNachname();
        }
        return null;
    }

    // Mitarbeiter entfernen
    public boolean deleteMitarbeiter(String id) {
        Mitarbeiter mitarbeiter = searchMitarbeiter(id);
        if (mitarbeiter == null) {
            return false;
        }

        try {
            speicher.loeschen(mitarbeiter);
            mitarbeiterListe.remove(mitarbeiter);
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException Error occurred: " + e.getMessage());
            return false;
        }


    }

    // Mitarbeiterdaten anpassen
    public boolean changeMitarbeiter(String id, int index, String aenderung) {
        Mitarbeiter m = searchMitarbeiter(id);
        if (m == null) return false;

        String feld = Mitarbeiter.SPALTEN_TABELLE[index];
        try {
            speicher.aendern(m, feld, aenderung);
            switch (index) {
                case 1 -> m.setNachname(aenderung);
                case 2 -> m.setVorname(aenderung);
                case 3 -> m.setAdresse(aenderung);
                case 4 -> m.setHausnummer(aenderung);
                case 5 -> m.setPlz(aenderung);
                case 6 -> m.setWohnort(aenderung);
                case 7 -> m.setTelefon(aenderung);
                case 8 -> m.setGeburtsdatum(aenderung);
                case 9 -> m.setStatus(aenderung);
                case 10 -> m.setAbteilung(aenderung);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException Error occurred: " + e.getMessage());
            return false;
        }
    }
}
