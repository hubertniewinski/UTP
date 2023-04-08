package zad1;

import java.util.function.Function;

public class InputConverter<TInput extends Object> {
    private final TInput input;

    public InputConverter(TInput input) {
        this.input = input;
    }

    public <TResult extends Object> TResult convertBy(Function<?, ? extends Object>... functions) {
        TResult result = null;

        for(Function<? extends Object, ? extends Object> func: functions){
            if(result == null){
                result = ((Function<TInput, TResult>)func).apply(input);
            }
            else{
                result = (TResult) ((Function<TResult, ?>)func).apply(result);
            }
        }

        return result;
    }
}