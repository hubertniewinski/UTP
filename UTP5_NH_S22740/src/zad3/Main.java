/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    try {
      URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
      URLConnection connection = url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      Map<String, List<String>> anagramMap = reader.lines().collect(Collectors.groupingBy(Main::sortString));

      int maxAnagrams = anagramMap.values().stream().mapToInt(List::size).max().orElse(0);

      anagramMap.entrySet().stream().filter(s -> s.getValue().size() == maxAnagrams).forEach(x -> {
        System.out.print(x.getKey() + " ");
        x.getValue().forEach(v -> System.out.print(v + " "));
        System.out.println();
      });

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String sortString(String t) {
    char[] chars = t.toCharArray();
    java.util.Arrays.sort(chars);
    return new String(chars);
  }
}
