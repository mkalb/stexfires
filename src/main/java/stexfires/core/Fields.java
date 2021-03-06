package stexfires.core;

import stexfires.core.mapper.fieldvalue.FieldValueMapper;
import stexfires.core.mapper.fieldvalue.IdentityFieldValueMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class consists of {@code static} utility methods for operating on fields.
 *
 * @author Mathias Kalb
 * @since 0.1
 */
public final class Fields {

    public static final int FIRST_FIELD_INDEX = 0;
    public static final String DEFAULT_FIELD_VALUE_DELIMITER = ", ";

    private Fields() {
    }

    public static Field[] emptyArray() {
        return new Field[0];
    }

    public static Field[] newArray(Collection<String> values) {
        Field[] fields = new Field[values.size()];
        int index = FIRST_FIELD_INDEX;
        for (String value : values) {
            fields[index] = new Field(index, (index + 1) == values.size(), value);
            index++;
        }
        return fields;
    }

    public static Field[] newArray(Stream<String> values) {
        return newArray(values.collect(Collectors.toList()));
    }

    public static Field[] newArray(String... values) {
        Field[] fields = new Field[values.length];
        for (int index = FIRST_FIELD_INDEX; index < values.length; index++) {
            fields[index] = new Field(index, (index + 1) == values.length, values[index]);
        }
        return fields;
    }

    public static Field[] newArray(int length, Supplier<String> valueSupplier) {
        if (length < 0) {
            throw new IllegalArgumentException("Illegal length! length=" + length);
        }
        Objects.requireNonNull(valueSupplier);
        Field[] fields = new Field[length];
        for (int index = FIRST_FIELD_INDEX; index < length; index++) {
            fields[index] = new Field(index, (index + 1) == length, valueSupplier.get());
        }
        return fields;
    }

    public static List<String> collectValues(Record record) {
        return collectValues(record.streamOfFields(), new IdentityFieldValueMapper());
    }

    public static List<String> collectValues(Record record, FieldValueMapper fieldValueMapper) {
        return collectValues(record.streamOfFields(), fieldValueMapper);
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    public static List<String> collectValues(Field[] fields) {
        Objects.requireNonNull(fields);
        return collectValues(Arrays.stream(fields), new IdentityFieldValueMapper());
    }

    public static List<String> collectValues(Field[] fields, FieldValueMapper fieldValueMapper) {
        Objects.requireNonNull(fields);
        return collectValues(Arrays.stream(fields), fieldValueMapper);
    }

    public static List<String> collectValues(Stream<Field> fields) {
        return collectValues(fields, new IdentityFieldValueMapper());
    }

    public static List<String> collectValues(Stream<Field> fields, FieldValueMapper fieldValueMapper) {
        Objects.requireNonNull(fields);
        Objects.requireNonNull(fieldValueMapper);
        return fields.map(fieldValueMapper::mapToValue).collect(Collectors.toList());
    }

    public static String joinValues(Record record) {
        return joinValues(record.streamOfFields(), DEFAULT_FIELD_VALUE_DELIMITER);
    }

    public static String joinValues(Record record, CharSequence delimiter) {
        Objects.requireNonNull(delimiter);
        return joinValues(record.streamOfFields(), delimiter);
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    public static String joinValues(Field[] fields) {
        Objects.requireNonNull(fields);
        return joinValues(Arrays.stream(fields), DEFAULT_FIELD_VALUE_DELIMITER);
    }

    public static String joinValues(Field[] fields, CharSequence delimiter) {
        Objects.requireNonNull(fields);
        Objects.requireNonNull(delimiter);
        return joinValues(Arrays.stream(fields), delimiter);
    }

    public static String joinValues(Stream<Field> fields) {
        return joinValues(fields, DEFAULT_FIELD_VALUE_DELIMITER);
    }

    public static String joinValues(Stream<Field> fields, CharSequence delimiter) {
        Objects.requireNonNull(fields);
        Objects.requireNonNull(delimiter);
        return fields.map(Field::getValue).collect(Collectors.joining(delimiter));
    }

}
