/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Function<String, List<String>> flines = (String value) -> {
      try {
        return Files.lines(Paths.get(value)).collect(Collectors.toList());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
    Function<List<String>, String> join = (List<String> values) -> String.join("", values);
    Function<String, List<Integer>> collectInts = (String value) ->
            Pattern.compile("\\D+")
                    .splitAsStream(value)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
    Function<List<Integer>, Integer> sum = (List<Integer> values) -> values.stream().mapToInt(Integer::intValue).sum();

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
