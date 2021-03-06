package stexfires.core.filter;

import stexfires.core.Record;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * A RecordFilter is a filter (boolean-valued function) for a {@link Record}.
 * <p>
 * It must be <code>thread-safe</code> and <code>non-interfering</code>.
 * It should be <code>immutable</code> and <code>stateless</code>.
 * <p>
 * This is a functional interface whose functional method is {@link #isValid(Record)}.
 *
 * @author Mathias Kalb
 * @see java.util.function.Predicate
 * @see java.util.stream.Stream#filter(Predicate)
 * @since 0.1
 */
@FunctionalInterface
public interface RecordFilter<T extends Record> {

    static <T extends Record> RecordFilter<T> of(Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    static <T extends Record> RecordFilter<T> concatAnd(RecordFilter<? super T> firstRecordFilter,
                                                        RecordFilter<? super T> secondRecordFilter) {
        Objects.requireNonNull(firstRecordFilter);
        Objects.requireNonNull(secondRecordFilter);
        return (T record) -> firstRecordFilter.isValid(record) && secondRecordFilter.isValid(record);
    }

    static <T extends Record> RecordFilter<T> concatOr(RecordFilter<? super T> firstRecordFilter,
                                                       RecordFilter<? super T> secondRecordFilter) {
        Objects.requireNonNull(firstRecordFilter);
        Objects.requireNonNull(secondRecordFilter);
        return (T record) -> firstRecordFilter.isValid(record) || secondRecordFilter.isValid(record);
    }

    boolean isValid(T record);

    default Predicate<T> asPredicate() {
        return this::isValid;
    }

    default RecordFilter<T> and(RecordFilter<? super T> other) {
        Objects.requireNonNull(other);
        return (T record) -> isValid(record) && other.isValid(record);
    }

    default RecordFilter<T> negate() {
        return (T record) -> !isValid(record);
    }

    default RecordFilter<T> or(RecordFilter<? super T> other) {
        Objects.requireNonNull(other);
        return (T record) -> isValid(record) || other.isValid(record);
    }

}
