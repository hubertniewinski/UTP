package zad1;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListCreator<TFrom> {
    private List<TFrom> _from;

    public ListCreator(List<TFrom> from){
        _from = from;
    }

    public static <TFrom> ListCreator<TFrom> collectFrom(List<TFrom> from){
        return new ListCreator<>(from);
    }

    public ListCreator<TFrom> when(Predicate<TFrom> predicate){
        _from = _from.stream().filter(predicate).collect(Collectors.toList());
        return this;
    }

    public <TTo> List<TTo> mapEvery(Function<TFrom, TTo> func){
        return _from.stream().map(func).collect(Collectors.toList());
    }
}
