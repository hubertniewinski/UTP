/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = (Integer arg) -> arg < 10;

    Mapper<Integer, Integer> map = (Integer arg) -> arg + 10;
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = (String arg) -> arg.length() > 3;
    Mapper<String, Integer> map = (String arg) -> arg.length() + 10;
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
