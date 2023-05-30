package zad1;

import java.util.Locale;

public class Helpers {
    public static Locale getLocaleFromLanguageTag(String languageTag) {
        String[] parts = languageTag.split("_");
        if (parts.length == 1) {
            if(parts[0].equals("pl")){
                return new Locale(parts[0], "PL");
            }
            return new Locale(parts[0]);
        } else if (parts.length == 2) {
            return new Locale(parts[0], parts[1]);
        } else if (parts.length == 3) {
            return new Locale(parts[0], parts[1], parts[2]);
        } else {
            throw new IllegalArgumentException("Invalid language tag: " + languageTag);
        }
    }
}
