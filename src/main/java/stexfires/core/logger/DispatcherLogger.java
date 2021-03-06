package stexfires.core.logger;

import stexfires.core.Record;
import stexfires.core.filter.RecordFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class DispatcherLogger<T extends Record> implements RecordLogger<T> {

    protected final Object lock = new Object();

    protected final BiPredicate<Integer, ? super T> predicate;
    protected final List<RecordLogger<? super T>> recordLoggers;

    public DispatcherLogger(BiPredicate<Integer, ? super T> predicate,
                            List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(recordLoggers);
        this.predicate = predicate;
        this.recordLoggers = new ArrayList<>(recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> all(List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record)
                -> true;
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byRecord(Function<? super T, Integer> recordIndexFunction,
                                                                  List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(recordIndexFunction);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                Objects.equals(recordIndexFunction.apply(record), index);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byCategory(Function<String, Integer> categoryIndexFunction,
                                                                    List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(categoryIndexFunction);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                Objects.equals(categoryIndexFunction.apply(record.getCategory()), index);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byRecordId(Function<Long, Integer> recordIdIndexFunction,
                                                                    List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(recordIdIndexFunction);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                Objects.equals(recordIdIndexFunction.apply(record.getRecordId()), index);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> bySize(List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                record.size() == index;
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byIndexPredicate(IntPredicate indexPredicate,
                                                                          List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(indexPredicate);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                indexPredicate.test(index);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byIndexSupplier(Supplier<Integer> indexSupplier,
                                                                         List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(indexSupplier);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                Objects.equals(indexSupplier.get(), index);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byBooleanSupplier(Supplier<Boolean> booleanSupplier,
                                                                           List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(booleanSupplier);
        Objects.requireNonNull(recordLoggers);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                booleanSupplier.get();
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    public static <T extends Record> DispatcherLogger<T> byFilters(List<RecordFilter<? super T>> recordFilters,
                                                                   List<RecordLogger<? super T>> recordLoggers) {
        Objects.requireNonNull(recordFilters);
        Objects.requireNonNull(recordLoggers);
        List<RecordFilter<? super T>> localFilters = new ArrayList<>(recordFilters);
        BiPredicate<Integer, T> predicate = (Integer index, T record) ->
                index < localFilters.size() && localFilters.get(index).isValid(record);
        return new DispatcherLogger<>(predicate, recordLoggers);
    }

    @Override
    public void log(T record) {
        synchronized (lock) {
            for (int index = 0; index < recordLoggers.size(); index++) {
                if (predicate.test(index, record)) {
                    recordLoggers.get(index).log(record);
                }
            }
        }
    }

}
