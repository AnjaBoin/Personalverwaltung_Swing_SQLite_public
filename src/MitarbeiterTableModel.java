import javax.swing.table.AbstractTableModel;
import java.util.*;

public class MitarbeiterTableModel extends AbstractTableModel {
    // KONSTANTEN


    // FELDER
    private final Verwaltung verwaltung;

    // KONSTRUKTOR
    public MitarbeiterTableModel(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;

    }

    public List<Mitarbeiter> mitarbeiterListe() {
        return verwaltung.getMitarbeiterListe();
    }

    @Override
    public String getColumnName(int col) {
        return Mitarbeiter.SPALTEN_TABELLE[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Mitarbeiter m = mitarbeiterListe().get(row);
        if (col == Mitarbeiter.STATUS) return Boolean.parseBoolean(m.alsZeile()[col]);
        return m.alsZeile()[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == Mitarbeiter.STATUS) return Boolean.class;
        return String.class;
    }


    @Override
    public int getRowCount() {
        return mitarbeiterListe().size();
    }

    @Override
    public int getColumnCount() {
        return Mitarbeiter.SPALTEN_TABELLE.length;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col != Mitarbeiter.STATUS) return;
        Mitarbeiter m = mitarbeiterListe().get(row);
        verwaltung.changeMitarbeiter(m.getId(), Mitarbeiter.STATUS, String.valueOf(value));
        fireTableRowsUpdated(row, row);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == Mitarbeiter.STATUS;
    }


}
