package zad1;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class OfferDbTable extends AbstractTableModel  {
    private String[] columnNames;
    private String tableName = "";
    private List<OfferDatabaseModel> rows;

    public OfferDbTable(String[] columnNames, List<OfferDatabaseModel> rows) {
        this.columnNames = columnNames;
        this.rows = rows;
        fireTableChanged(null);
    }

    public String getColumnName(int column) {
        if (columnNames[column] != null) return columnNames[column];
        else return "";
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int r, int c) {
        OfferDatabaseModel offer = rows.get(r);

        switch(c){
            case 0:
                return offer.getId();
            case 1:
                return offer.getContractorLocale();
            case 2:
                return offer.getContractorTranslatedCountry(Locale.getDefault());
            case 3:
                return offer.getFormattedDepartureDate();
            case 4:
                return offer.getFormattedReturnDate();
            case 5:
                return offer.getTranslatedPlaceKey();
            case 6:
                return offer.getFormattedPrice();
            case 7:
                return offer.getCurrency();
            default:
                throw new RuntimeException("Unknown column type");
        }
    }
}