package zad1;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUIBuilder extends JFrame {
    private JTable table = new JTable();
    private JTextArea ta = new JTextArea(3,40);

    public GUIBuilder(List<String> availableLanguages, List<String> availableRegions) {
        super("Baza danych ofert");
        setDefaultCloseOperation(3);

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setPreferredSize(new Dimension(600, 400));
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        ta.setLineWrap(true);

        JPanel languageWrapper = new JPanel();
        languageWrapper.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        JLabel languageLabel = new JLabel(ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault()).getString("language"));
        labelPanel.add(languageLabel, "Center");

        languageWrapper.add(labelPanel, "North");

        JPanel languageButtonsPanel = new JPanel();

        languageWrapper.add(languageButtonsPanel, "Center");

        p.add(languageWrapper, "West");

        JPanel regionsWrapper = new JPanel();
        regionsWrapper.setLayout(new BorderLayout());

        JPanel regionLabelPanel = new JPanel();
        JLabel regionLabel = new JLabel(ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault()).getString("country"));
        regionLabelPanel.add(regionLabel, "Center");

        regionsWrapper.add(regionLabelPanel, "North");

        JPanel regionButtonsPanel = new JPanel();

        for(String language: availableLanguages){
            JButton languageButton = new JButton(language);
            languageButton.addActionListener(e -> {
                Locale.setDefault(new Locale(language, Locale.getDefault().getCountry()));
                languageLabel.setText(ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault()).getString("language"));
                regionLabel.setText(ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault()).getString("country"));
                languageChanged();
            });
            languageButton.setPreferredSize(new Dimension(100, 30));
            languageButtonsPanel.add(languageButton, "West");
        }

        for(String region: availableRegions){
            JButton regionButton = new JButton(region);
            regionButton.addActionListener(e -> {
                Locale.setDefault(new Locale(Locale.getDefault().getLanguage(), region));
                languageChanged();
            });
            regionButton.setPreferredSize(new Dimension(100, 30));
            regionButtonsPanel.add(regionButton, "West");
        }

        regionsWrapper.add(regionButtonsPanel, "Center");

        p.add(regionsWrapper, "East");

        getContentPane().add(scrollPanel, "Center");
        getContentPane().add(p, "South");

        pack();
        setVisible(true);
    }

    void languageChanged() {
        for(int i = 0; i < table.getColumnCount(); i++) {
            String header = table.getColumnName(i);
            ResourceBundle resourceBundle = ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault());
            table.getColumnModel().getColumn(i).setHeaderValue(resourceBundle.getString(header));
        }
        table.updateUI();
    }

    void addOffers(List<OfferDatabaseModel> offers, String[] columnNames) {
        OfferDbTable dbt = new OfferDbTable(columnNames, offers);
        table.setModel(dbt);
        languageChanged();
    }
}