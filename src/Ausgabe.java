import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ausgabe {
    // KONSTANTEN
    String[] options = Mitarbeiter.SPALTEN_TABELLE;
    String text;

    // FELDER
    private final Verwaltung verwaltung;
    private MitarbeiterTableModel model;
    private JPanel controlPanel;

    // Frame & Table
    private final JFrame frame;
    private final JTable table;

    // Konstruktor

    public Ausgabe(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
        model = new MitarbeiterTableModel(verwaltung);

        // Tabelle
        table = new JTable(model) {
            // Farbe Anwesenheit (Bestimme Status anwesend und setze Farbe auf grün)
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                boolean anwesend = (Boolean) getModel().getValueAt(row, Mitarbeiter.STATUS);
                if (isRowSelected(row)) {
                    c.setBackground(anwesend ? new Color(150, 220, 150) : new Color(230, 160, 160));
                } else {
                    c.setBackground(anwesend ? new Color(200, 255, 200) : new Color(255, 210, 210));

                }
                c.setForeground(Color.BLACK);
                return c;
            }
        };
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Titel im Fenster
        JLabel titel = new JLabel("Personalverwaltung", SwingConstants.CENTER);
        titel.setFont(new Font("SansSerif", Font.BOLD, 35));
        titel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Control panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Frame
        frame = new JFrame();
        frame.add(titel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setSize(1600, 400);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons
        showButton();

        // Dieser Satz immer als Abschluss sonst ist nichts sichtbar
        frame.setVisible(true);   // immer als Letztes

    }


    public void showButton() {
        //resources folder should be inside SWING folder.
        JButton neuButton = new JButton("Neuer Mitarbeiter anmelden");
        neuButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton aendernButton = new JButton("Mitarbeiter Daten ändern");
        aendernButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton loeschButton = new JButton("Mitarbeiter löschen");
        loeschButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);

        neuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text = "Füllen Sie Bitte folgende Felder aus:";
                Mitarbeiter neu = Eingabe.anmelden(options, verwaltung, text, Abteilung.getAbteilungen(), frame);
                Eingabe.mitarbeiterHinzufuegen(neu, verwaltung, frame);
                model.fireTableDataChanged();
            }
        });
        aendernButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Eingabe.mitarbeiterAendern(options, verwaltung, frame, ausgewaehlteId());
                model.fireTableDataChanged();
            }
        });
        loeschButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Eingabe.mitarbeiterEntfernen(verwaltung, frame, ausgewaehlteId());
                model.fireTableDataChanged();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        controlPanel.add(neuButton);
        controlPanel.add(aendernButton);
        controlPanel.add(loeschButton);
        controlPanel.add(cancelButton);
    }

    public String ausgewaehlteId() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        return verwaltung.getMitarbeiterListe().get(row).getId();
    }

}
