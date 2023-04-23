/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private List<String> words = new ArrayList<>();

    public Anagrams(String path) {
        try {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String[] lineWords = scanner.nextLine().split("\\s+");
                for (String word : lineWords) {
                    words.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public List<List<String>> getSortedByAnQty() {
        HashMap<String, List<String>> results = new HashMap<>();

        for(String word: words){
            String sortedString = word.chars()
                    .mapToObj(c -> (char) c)
                    .sorted()
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();

            if(results.containsKey(sortedString)){
                results.get(sortedString).add(word);
            }
            else{
                results.put(sortedString, new ArrayList<String>(){{
                    add(word);
                }});
            }
        }

        List<List<String>> sortedResults = new ArrayList<>(results.values());

        Collections.sort(sortedResults, (a, b) -> {
            int countA = a.size();
            int countB = b.size();

            if (countA == countB) {
                String firstWordA = a.get(0);
                String firstWordB = b.get(0);
                return firstWordA.compareTo(firstWordB);
            }

            return Integer.compare(countB, countA);
        });

        return sortedResults;
    }

    public String getAnagramsFor(String next){
        List<List<String>> sorted = getSortedByAnQty();
        List<String> value = sorted.stream().filter(x -> x.stream().anyMatch(val -> val.equals(next)))
                .findFirst().orElse(null);

        return next + ": " + (value != null ? value.stream().filter(x -> !x.equals(next)).collect(Collectors.toList()) : null);
    }
}
