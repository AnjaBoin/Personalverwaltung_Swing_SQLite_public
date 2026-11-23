import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Schriftart vom JOptionPane anpassen
        UIManager.put("OptionPane.messageFont", new Font("SansSerif", Font.PLAIN, 16)); // die Frage/Meldung
        UIManager.put("OptionPane.buttonFont", new Font("SansSerif", Font.BOLD, 14));  // Ja / Nein / OK / Abbrechen
        UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 16)); // Eingabefeld bei showInputDialog
        UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 16)); // Dropdown (Abteilung, Felder)

        Verwaltung verwaltung = new Verwaltung();
        System.out.println(new java.io.File("Daten/outputMitarbeiter.txt").getAbsolutePath());

        new Ausgabe(verwaltung);
    }
}