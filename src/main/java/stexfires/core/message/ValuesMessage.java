package stexfires.core.message;

import stexfires.core.Fields;
import stexfires.core.Record;

import java.util.Objects;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class ValuesMessage<T extends Record> implements RecordMessage<T> {

    protected final CharSequence delimiter;

    public ValuesMessage() {
        this(Fields.DEFAULT_FIELD_VALUE_DELIMITER);
    }

    public ValuesMessage(CharSequence delimiter) {
        Objects.requireNonNull(delimiter);
        this.delimiter = delimiter;
    }

    @Override
    public String createMessage(T record) {
        return Fields.joinValues(record, delimiter);
    }

}
