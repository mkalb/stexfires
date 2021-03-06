package stexfires.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * A Record contains {@link Field}s with values.
 * <p>
 * It must be immutable and thread-safe.
 * The field index must start with 0.
 *
 * @author Mathias Kalb
 * @since 0.1
 */
public interface Record {

    Field[] arrayOfFields();

    List<Field> listOfFields();

    Stream<Field> streamOfFields();

    String getCategory();

    default boolean hasCategory() {
        return getCategory() != null;
    }

    default String getCategoryOrElse(String other) {
        return getCategory() != null ? getCategory() : other;
    }

    default Optional<String> getCategoryAsOptional() {
        return Optional.ofNullable(getCategory());
    }

    default Stream<String> streamOfCategory() {
        if (getCategory() == null) {
            return Stream.empty();
        }
        return Stream.of(getCategory());
    }

    Long getRecordId();

    default boolean hasRecordId() {
        return getRecordId() != null;
    }

    default Optional<Long> getRecordIdAsOptional() {
        return Optional.ofNullable(getRecordId());
    }

    default LongStream streamOfRecordId() {
        if (getRecordId() == null) {
            return LongStream.empty();
        }
        return LongStream.of(getRecordId());
    }

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isValidIndex(int index) {
        return (index >= 0) && (index < size());
    }

    Field getFieldAt(int index);

    default Field getFirstField() {
        return getFieldAt(0);
    }

    default Field getLastField() {
        return getFieldAt(size() - 1);
    }

    default String getValueAt(int index) {
        return isValidIndex(index) ? getFieldAt(index).getValue() : null;
    }

    default String getValueAtOrElse(int index, String other) {
        return getValueAt(index) != null ? getValueAt(index) : other;
    }

    default String getValueOfFirstField() {
        return isEmpty() ? null : getFirstField().getValue();
    }

    default String getValueOfLastField() {
        return isEmpty() ? null : getLastField().getValue();
    }

    /**
     * Compare class, category, recordId and all fields.
     */
    boolean equals(Object other);

    int hashCode();

    String toString();

}
