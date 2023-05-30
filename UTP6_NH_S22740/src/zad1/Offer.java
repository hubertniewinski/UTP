package zad1;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {
    private String contractorLocale;
    private String country;
    private Date departureDate;
    private Date returnDate;
    private String placeKey;
    private Number price;
    private String currency;

    public Offer(String line) {
        try {
            String[] data = line.split("\t");
            this.contractorLocale = data[0];
            this.country = data[1];
            SimpleDateFormat baseDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            this.departureDate = baseDateFormatter.parse(data[2]);
            this.returnDate = baseDateFormatter.parse(data[3]);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Keys", Helpers.getLocaleFromLanguageTag(contractorLocale));
            this.placeKey = resourceBundle.getString(data[4]);
            NumberFormat numberInputFormatter = NumberFormat.getNumberInstance(Helpers.getLocaleFromLanguageTag(contractorLocale));
            this.price = numberInputFormatter.parse(data[5]);
            this.currency = data[6];
        }
        catch(ParseException e){
            System.out.println("Error while parsing date");
        }
    }

    public String getDescription(ResourceBundle resourceBundle, String desiredLocale, String dateFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        StringBuilder builder = new StringBuilder();
        builder.append(this.getContractorCountry(desiredLocale)).append(" ");
        builder.append(dateFormatter.format(this.departureDate)).append(" ");
        builder.append(dateFormatter.format(this.returnDate)).append(" ");
        builder.append(resourceBundle.getString(placeKey)).append(" ");
        DecimalFormat decimalFormatter = (DecimalFormat)NumberFormat.getNumberInstance(Helpers.getLocaleFromLanguageTag(desiredLocale));
        builder.append(decimalFormatter.format(price)).append(" ");
        builder.append(currency);

        return builder.toString();
    }

    public String getContractorCountry(String desiredLocale) {
        for(Locale l: Locale.getAvailableLocales()) {
            if(l.getCountry().equals("")){
                continue;
            }
            if(l.getDisplayCountry(Helpers.getLocaleFromLanguageTag(this.contractorLocale)).equals(this.country)){
                return l.getDisplayCountry(Helpers.getLocaleFromLanguageTag(desiredLocale));
            }
        }

        return "";
    }
}
