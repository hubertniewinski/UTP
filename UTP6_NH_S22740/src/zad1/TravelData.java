package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Common", Helpers.getLocaleFromLanguageTag(locale));
        return offers.stream().map(x -> x.getDescription(resourceBundle, locale, dateFormat)).collect(Collectors.toList());
    }
}
