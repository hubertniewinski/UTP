/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomersPurchaseSortFind {
    private ArrayList<Purchase> data = new ArrayList<>();

    public void readFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                data.add(new Purchase(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public void showSortedBy(String key) {

        SortAction sortAction = null;
        try {
            sortAction = tryParse(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println(sortAction);
        switch(sortAction){
            case Nazwiska: {
                data.stream()
                        .sorted(Comparator.comparing(Purchase::getName).thenComparing(Purchase::getId))
                        .forEach(x -> System.out.println(x));
                break;
            }
            case Koszty: {
                data.stream()
                        .sorted(Comparator.comparing(Purchase::getCost).reversed().thenComparing(Purchase::getId))
                        .forEach(x -> System.out.println(x.toStringWithCost()));
                break;
            }
        }
    }

    public void showPurchaseFor(String id) {
        System.out.println();
        System.out.println("Klient " + id);
        data.stream()
                .filter(x -> x.getId().equals(id))
                .forEach(x -> System.out.println(x));
    }

    private SortAction tryParse(String key) throws Exception {
        for(SortAction action: SortAction.values()){
            if(key.toLowerCase().equals(action.toString().toLowerCase())){
                return action;
            }
        }

        throw new Exception("Can't bind to sort action");
    }
}
