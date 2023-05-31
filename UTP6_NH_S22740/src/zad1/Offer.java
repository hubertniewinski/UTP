package zad1;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {
    private static final String BASE_DATE_FORMAT = "yyyy-MM-dd";
    private String contractorLocale;
    private String country;
    private Date departureDate;
    private Date returnDate;
    private String placeKey;
    private double price;
    private String currency;

    public Offer(String line) {
        try {
            String[] data = line.split("\t");
            this.contractorLocale = data[0];
            this.country = data[1];
            SimpleDateFormat baseDateFormatter = new SimpleDateFormat(BASE_DATE_FORMAT);
            this.departureDate = baseDateFormatter.parse(data[2]);
            this.returnDate = baseDateFormatter.parse(data[3]);
            setPlaceKey(data[4]);
            NumberFormat numberInputFormatter = NumberFormat.getNumberInstance(Helpers.getLocaleFromLanguageTag(contractorLocale));
            this.price = numberInputFormatter.parse(data[5]).doubleValue();
            this.currency = data[6];
        }
        catch(ParseException e){
            System.out.println("Error while parsing date");
        }
    }

    public void setPlaceKey(String placeKey) {
        ResourceBundle resourceBundle = ResourceBundleProvider.getKeyResourceBundle(Helpers.getLocaleFromLanguageTag(contractorLocale));
        this.placeKey = resourceBundle.getString(placeKey);
    }

    public String getContractorTranslatedCountry(Locale locale) {
        for(Locale l: Locale.getAvailableLocales()) {
            if(l.getCountry().equals("")){
                continue;
            }
            if(l.getDisplayCountry(Helpers.getLocaleFromLanguageTag(contractorLocale)).equals(country)){
                return l.getDisplayCountry(locale);
            }
        }

        throw new RuntimeException("Country not found!");
    }

    public String getContractorLocale() {
        return contractorLocale;
    }

    public String getCountry() {
        return country;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getPlaceKey() {
        return placeKey;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
