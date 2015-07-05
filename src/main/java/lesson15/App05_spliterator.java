package lesson15;

import java.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.stream.StreamSupport;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App05_spliterator {

    public static void main(String[] args) {
        //seq: Collection -> Iterator
        //par: Spliterator -> Stream

        LongRange longRange = new LongRange(0, 1_000_000);

        long result = StreamSupport
                .stream(longRange, true)
                .parallel()
                .filter(x -> x % 3 != 0)
                .filter(x -> x % 5 != 0)
                .reduce(0L, (x, y) -> x + y);
        System.out.println(result);
    }
}

class LongRange implements Spliterator.OfLong {
    private long from;
    private long to;

    public LongRange(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public LongRange trySplit() {
        if (to - from > 1_000_000) {  //new: [from, mid] old: [mid, to]
            return new LongRange(from, from = ((from - to) >>> 1));
        } else {
            return null;
        }
    }

    @Override
    public long estimateSize() {
        return to - from;
    }

    @Override
    public int characteristics() {
        return 0
//                | Spliterator.CONCURRENT
                | Spliterator.DISTINCT
                | Spliterator.IMMUTABLE
                | Spliterator.NONNULL
//                | Spliterator.ORDERED
                | Spliterator.SIZED
//                | Spliterator.SORTED
                | Spliterator.SUBSIZED;
    }

    @Override
    public boolean tryAdvance(LongConsumer action) {
        if (to > from) {
            action.accept(from++);
            return true;
        } else {
            return false;
        }
    }
}