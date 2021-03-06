package stexfires.core.message;

import stexfires.core.Record;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class CategoryMessage<T extends Record> implements RecordMessage<T> {

    protected final String nullCategoryValue;

    public CategoryMessage() {
        this(null);
    }

    public CategoryMessage(String nullCategoryValue) {
        this.nullCategoryValue = nullCategoryValue;
    }

    @Override
    public String createMessage(T record) {
        return record.getCategoryOrElse(nullCategoryValue);
    }

}
