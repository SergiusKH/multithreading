package lesson12;

/**
 * Created by Sergius on 29.06.2015.
 */
public interface Reducer<T> {
    public T reduce(T left, T right);
}
