import javax.swing.*;

public class Eingabe {

    public Eingabe(){

    }

    // neuer Mitarbeiter anmelden
    public static Mitarbeiter anmelden(String[] options, Verwaltung verwaltung, String text, String[] optionsAbt, JFrame frame){
        String [] antworten = new String [options.length];
        for (int i = 1; i < antworten.length; i ++) {
            if (options[i].equals("Status")) antworten[i] = statusAbfragen(frame);
            else if (options[i].equals("Abteilung")) antworten[i] = abteilungAbfragen("Welche Abteilung?", optionsAbt, frame);
            else antworten[i] = JOptionPane.showInputDialog(frame, options[i]);
        }

        String id = String.valueOf(verwaltung.getMaxId() + 1);

        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(
                id,
                antworten[1],
                antworten[2],
                antworten[3],
                antworten[4],
                antworten[5],
                antworten[6],
                antworten[7],
                antworten[8],
                antworten[9],
                antworten[10]
        );
        return neuerMitarbeiter;
    }

    // Mitarbeiter hinzufuegen, nachdem er angemeldet wurde
    public static void mitarbeiterHinzufuegen(Mitarbeiter neu, Verwaltung verwaltung, JFrame frame){
        if (neu.getNachname() == null || neu.getNachname().isEmpty()) {
            String text = "Der Mitarbeiter " + neu.getId() + " hat keinen Namen und wird nicht hinzugefügt.";
            JOptionPane.showMessageDialog(frame, text);
            return;
        }
        verwaltung.addMitarbeiter(neu);
        String text = "Der Mitarbeiter " + neu.getNachname() + " ist hinzugefügt.";
        JOptionPane.showMessageDialog(frame, text);
    }

    // Status abfragen
    public static String statusAbfragen(JFrame frame) {
        int antwort = JOptionPane.showConfirmDialog(frame, "Ist der Mitarbeiter anwesend?",
                "Status", JOptionPane.YES_NO_OPTION);
        if (antwort == JOptionPane.CLOSED_OPTION) return null;
        return String.valueOf(antwort == JOptionPane.YES_OPTION);
    }

    // Abteilung abfragen
    public static String abteilungAbfragen(String text, String[] options, JFrame frame) {

        Object obj= JOptionPane.showInputDialog(
                frame,
                text,
                "Welcher Abteilung?",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );
        return (String) obj;
    }

    // Mitarbeiter ändern
    public static void mitarbeiterAendern(String[]options, Verwaltung verwaltung, JFrame frame){
        // erste Abfrage nach ID
        String text;
        text = "Welcher Mitarbeiter möchten Sie ändern? ID angeben bitte:";
        String id = JOptionPane.showInputDialog(frame, text);
        if (id == null) return;
        if (verwaltung.searchMitarbeiter(id) == null) {
            JOptionPane.showMessageDialog(frame, "Die ID " + id + " wurde nicht gefunden.");
            return;
        }

        // zweite Abfrage nach Datenfeld
        text = "Welche Daten möchten Sie ändern?";
        String[]optionsDropDownMenue = Mitarbeiter.FELDER_DDMENUE;
        Object aendern1= JOptionPane.showInputDialog(
                frame, // hier stond eerst null
                text,
                "Mitarbeiter ändern",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsDropDownMenue,
                options[1]
        );
        String indexText = (String)aendern1;
        if (indexText == null) return;
        int index = -1;
        for (int i = 1; i < options.length; i++) {
            if (indexText.equals(options[i])) index = i;
        }

        // Dritte Abfrage nach Änderung
        if (index == -1) return;
        String aendern;
        text = "Geben Sie den neuen Wert ein für " + indexText + ":";
        if (indexText.equals("Status")) aendern = Eingabe.statusAbfragen(frame);
        else if (indexText.equals("Abteilung")) aendern = Eingabe.abteilungAbfragen("Welche Abteilung?", Abteilung.getAbteilungen(), frame);
        else aendern = JOptionPane.showInputDialog(frame, text);
        if (aendern == null) return;

        // Änderung durchführen (oder nicht)
        if (verwaltung.changeMitarbeiter(id, index, aendern)) {
            text = "Die folgende Daten wurden geändert: " + indexText;
        } else {
            text = "Die Änderung wurde nicht durchgeführt.";
        }
        JOptionPane.showMessageDialog(frame, text);
    }

    public static void mitarbeiterEntfernen(Verwaltung verwaltung, JFrame frame){
        String text = "Welchen Mitarbeiter möchten Sie entfernen? ID angeben bitte:";
        String entfernen = JOptionPane.showInputDialog(frame, text);
        if (entfernen == null) return;
        Mitarbeiter m = verwaltung.searchMitarbeiter(entfernen);
        if (m == null) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Der Mitarbeiter mit ID " + entfernen + " wurde nicht gefunden.");
            return;
        }
        String name = m.getVorname() + " " + m.getNachname();
        int antwort = JOptionPane.showConfirmDialog(
                frame,
                name + " wird entfernt. Weitergehen?",
                "Mitarbeiter löschen bestätigen",
                JOptionPane.YES_NO_OPTION);
        if (antwort == JOptionPane.CLOSED_OPTION) return;
        if (antwort == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(
                    frame,
                    "Der Mitarbeiter " + name + " wurde nicht gelöscht.");
            return;
        }
        JOptionPane.showMessageDialog(frame, verwaltung.deleteMitarbeiter(entfernen)
                ? "Der Mitarbeiter " + name + " wurde gelöscht."
                : "Der Mitarbeiter " + name + " wurde nicht gefunden.");
    }
}
