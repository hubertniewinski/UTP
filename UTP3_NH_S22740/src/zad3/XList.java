package zad3;

import zad1.InputConverter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {
    public static <T> XList<T> of(T... values) {
        return new XList<>(values);
    }

    public static <T> XList<T> of(Collection<T> values) {
        return new XList<>(values);
    }

    public static XList<String> charsOf(String value) {
        XList<String> charsOf = new XList<>();

        for(char c : value.toCharArray()){
            charsOf.add(String.valueOf(c));
        }

        return charsOf;
    }

    public static XList<String> tokensOf(String str, String separator) {
        return new XList(str.split(separator));
    }

    public static XList<String> tokensOf(String str) {
        return tokensOf(str, "\\s+");
    }

    public XList(Collection<T> values){
        super(values);
    }

    public XList(T... values){
        super(Arrays.asList(values));
    }

    public XList<T> union(T... values) {
        return union(Arrays.asList(values));
    }

    public XList<T> union(Collection<? extends T> list2) {
        XList<T> result = new XList<T>(this);
        result.addAll(list2);
        return result;
    }

    public XList<T> diff(Collection<? extends T> list2) {
        XList<T> diffValues = new XList<T>();

        for(T value : this){
            if(!list2.contains(value)){
                diffValues.add(value);
            }
        }

        return diffValues;
    }

    public XList<T> unique() {
        return stream().distinct().collect(Collectors.toCollection(XList::new));
    }

    public <K> XList<K> collect(Function<T, K> mapper) {
        return stream().map(mapper).collect(Collectors.toCollection(XList::new));
    }

    public <K> XList<XList<K>> combine() {
        InputConverter<XList<T>> fileConv = new InputConverter<>(this);
        Function<XList<T>, XList<XList<K>>> map = (XList<T> values) -> {
            XList<XList<K>> result = new XList<>();
            for(T val: values){
                result.add(new XList<K>((Collection<K>) val));
            }
            return result;
        };
        XList<XList<K>> lists = fileConv.convertBy(map);
        XList<XList<K>> result = combine(lists);
        return result;
    }

    public static <T> XList<XList<T>> combine(XList<XList<T>> lists) {
        XList<XList<T>> result = new XList<>();
        int[] sizes = new int[lists.size()];
        int size = 1;
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = lists.get(i).size();
            size *= sizes[i];
        }
        for (int i = 0; i < size; i++) {
            XList<T> current = new XList<>();
            int temp = i;
            for (int j = 0; j < sizes.length; j++) {
                current.add(lists.get(j).get(temp % sizes[j]));
                temp /= sizes[j];
            }
            result.add(current);
        }
        return result;
    }

    public String join(String sep) {
        return stream().map(String::valueOf).collect(Collectors.joining(sep));
    }

    public String join() {
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for(int i=0; i<size(); i++) {
            consumer.accept(get(i), i);
        }
    }
}
