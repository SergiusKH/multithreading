package lesson12;

/**
 * Created by Sergius on 29.06.2015.
 */
// � java interface Function
public interface Mapper<T, R> {
    public R map(T args);
}
