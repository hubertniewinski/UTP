package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TravelData {
    private List<Offer> offers = new ArrayList<>();
    public TravelData(File dataDir) {
        try (Stream<Path> paths = Files.walk(dataDir.getAbsoluteFile().toPath())) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(x -> {
                        try {
                            Files.lines(x).forEach(y -> {
                                offers.add(new Offer(y));
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        Locale localization = Helpers.getLocaleFromLanguageTag(locale);
        ResourceBundle resourceBundle = ResourceBundleProvider.getCommonResourceBundle(localization);

        return offers.stream().map(x -> {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

            StringBuilder builder = new StringBuilder();
            builder.append(x.getContractorTranslatedCountry(localization)).append(" ");
            builder.append(dateFormatter.format(x.getDepartureDate())).append(" ");
            builder.append(dateFormatter.format(x.getReturnDate())).append(" ");
            builder.append(resourceBundle.getString(x.getPlaceKey())).append(" ");
            DecimalFormat decimalFormatter = (DecimalFormat) NumberFormat.getNumberInstance(localization);
            builder.append(decimalFormatter.format(x.getPrice())).append(" ");
            builder.append(x.getCurrency());

            return builder.toString();
        }).collect(Collectors.toList());
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
