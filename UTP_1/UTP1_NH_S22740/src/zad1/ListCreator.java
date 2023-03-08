/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad1;


import java.util.List;
import java.util.stream.Collectors;

public class ListCreator<T> {
    private List<T> _list;
    public ListCreator(List<T> list){
        this._list = list;
    }

    public static ListCreator collectFrom(List<?> list){
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Selector<T> selector){
        _list = _list.stream().filter(x -> selector.select(x)).collect(Collectors.toList());
        return this;
    }

    public List mapEvery(Mapper<T, ?> mapper){
        return _list.stream().map(x -> mapper.map(x)).collect(Collectors.toList());
    }
}  
