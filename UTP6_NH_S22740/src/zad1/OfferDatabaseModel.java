package zad1;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class OfferDatabaseModel {
    private static final String BASE_DATE_FORMAT = "yyyy-MM-dd";
    private Integer id;
    private String contractorLocale;
    private String country;
    private Date departureDate;
    private Date returnDate;
    private String placeKey;
    private double price;
    private String currency;

    public OfferDatabaseModel(Integer id, String contractorLocale, String country, Date departureDate, Date returnDate, String placeKey, double price, String currency) {
        this.id = id;
        this.contractorLocale = contractorLocale;
        this.country = country;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        setPlaceKey(placeKey);
        this.price = price;
        this.currency = currency;
    }

    public void setPlaceKey(String placeKey) {
        ResourceBundle resourceBundle = ResourceBundleProvider.getKeyResourceBundle(Helpers.getLocaleFromLanguageTag(contractorLocale));
        this.placeKey = resourceBundle.getString(placeKey);
    }

    public Integer getId() {
        return id;
    }

    public String getContractorLocale() {
        return contractorLocale;
    }

    public String getFormattedDepartureDate() {
        return getSimpleDateFormat().format(departureDate);
    }

    public String getFormattedReturnDate() {
        return getSimpleDateFormat().format(returnDate);
    }

    public String getTranslatedPlaceKey() {
        ResourceBundle resourceBundle = ResourceBundleProvider.getCommonResourceBundle(Locale.getDefault());
        return resourceBundle.getString(placeKey);
    }

    public String getFormattedPrice() {
        return getDecimalFormat().format(price);
    }

    public String getCurrency() {
        return currency;
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

    private SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(BASE_DATE_FORMAT, Locale.getDefault());
    }

    private DecimalFormat getDecimalFormat() {
        return (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
    }
}
