package zad1;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleProvider {

    private static final String commonResourceBundleName = "Common";
    private static final String keysResourceBundleName = "Keys";
    public static ResourceBundle getKeyResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(keysResourceBundleName, MapLocale(locale));
    }

    public static ResourceBundle getCommonResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(commonResourceBundleName, MapLocale(locale));
    }

    private static Locale MapLocale(Locale l){
        switch(l.getLanguage()){
            case "pl":
                return new Locale("pl", "PL");
            case "en":
                return new Locale("en", "GB");
        }

        throw new RuntimeException("Unsupported locale " + l.getCountry());
    }
}
