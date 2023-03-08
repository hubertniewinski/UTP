/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad1;


public interface Mapper<TFrom, TTo> {
    TTo map(TFrom from);
}  
