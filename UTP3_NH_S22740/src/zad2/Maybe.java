package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T value;
    public Maybe(T value) {
        this.value = value;
    }
    public static <T> Maybe<T> of(T value) {
        return new Maybe(value);
    }

    @Override
    public String toString() {
        return value != null ? "Maybe has value "+value : "Maybe is empty";
    }

    public void ifPresent(Consumer<T> cons) {
        if(value == null){
            return;
        }

        cons.accept(value);
    }

    public <TTo> Maybe<TTo> map(Function<T, TTo> func) {
        if(value == null){
            return new Maybe<>(null);
        }

        TTo newValue = func.apply(value);
        return new Maybe(newValue);
    }

    public T get() {
        if(value == null){
            throw new NoSuchElementException();
        }

        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    T orElse(T defVal){
        if(value == null){
            return defVal;
        }

        return value;
    }

    Maybe<T> filter(Predicate<T> pred) {
        return pred.test(value) ? this : new Maybe(null);
    }
}
