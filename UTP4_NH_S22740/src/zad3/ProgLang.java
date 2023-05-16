package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang <TKey, TValue> {
    private LinkedHashMap<TKey, ArrayList<TValue>> dictData = new LinkedHashMap<>();
    public ProgLang(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split("\t");
                TKey key = (TKey) values[0];

                for(int i=1; i < values.length; i++){
                    if(dictData.containsKey(key)){
                        dictData.get(key).add((TValue) values[i]);
                    } else {
                        ArrayList<TValue> valuesList = new ArrayList<>();
                        valuesList.add((TValue) values[i]);
                        dictData.put(key, valuesList);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public LinkedHashMap<TKey, LinkedHashSet<TValue>> getLangsMap() {
        return dictData.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new LinkedHashSet<>(e.getValue()),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public LinkedHashMap<TKey, LinkedHashSet<TValue>> getLangsMapSortedByNumOfProgs() {
        return getLangsMapSorted(Comparator.comparingInt((Map.Entry<TKey, ArrayList<TValue>> x) -> x.getValue().size())
                        .reversed()
                        .thenComparing(x -> x.getKey().toString()));
    }

    public LinkedHashMap<TKey, LinkedHashSet<TValue>> getLangsMapSorted(Comparator<Map.Entry<TKey, ArrayList<TValue>>> comparator) {
        return dictData.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new LinkedHashSet<>(e.getValue()),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public LinkedHashMap<TValue, LinkedHashSet<TKey>> getProgsMap() {
        return dictData.entrySet().stream()
                .flatMap(e -> e.getValue().stream().map(v -> new AbstractMap.SimpleEntry<>(v, e.getKey())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new LinkedHashSet<>(Collections.singletonList(e.getValue())),
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        },
                        LinkedHashMap::new
                ));
    }

    public HashMap<TValue, LinkedHashSet<TKey>> getProgsMapSortedByNumOfLangs() {
        return getProgsMapSorted(Comparator.comparingInt((Map.Entry<TValue, LinkedHashSet<TKey>> x) -> x.getValue().size()).reversed()
                        .thenComparing(x -> x.getKey().toString()));
    }

    public HashMap<TValue, LinkedHashSet<TKey>> getProgsMapSorted(Comparator<Map.Entry<TValue, LinkedHashSet<TKey>>> comparator) {
        return getProgsMap()
                .entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        k -> k.getKey(),
                        v -> v.getValue(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public LinkedHashMap<TValue, LinkedHashSet<TKey>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return getProgsMapFiltered(x -> x.getValue().size() > i);
    }

    public LinkedHashMap<TValue, LinkedHashSet<TKey>> getProgsMapFiltered(Predicate<Map.Entry<TValue, LinkedHashSet<TKey>>> predicate) {
        return getProgsMap().entrySet().stream().filter(predicate).collect(
                Collectors.toMap(
                        k -> k.getKey(),
                        v -> v.getValue(),
                        (a, b) -> a,
                        LinkedHashMap::new
                )
        );
    }
}
