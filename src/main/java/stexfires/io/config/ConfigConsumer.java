package stexfires.io.config;

import stexfires.core.consumer.ConsumerException;
import stexfires.core.record.KeyValueRecord;
import stexfires.io.internal.AbstractWritableConsumer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class ConfigConsumer extends AbstractWritableConsumer<KeyValueRecord> {

    public static final String CATEGORY_PREFIX = "[";
    public static final String CATEGORY_POSTFIX = "]";

    private final ConfigFileSpec fileSpec;

    private String currentCategory;

    public ConfigConsumer(BufferedWriter writer, ConfigFileSpec fileSpec) {
        super(writer);
        Objects.requireNonNull(fileSpec);
        this.fileSpec = fileSpec;
    }

    @Override
    public void writeBefore() throws IOException {
        super.writeBefore();
        currentCategory = null;
    }

    @Override
    public void writeRecord(KeyValueRecord record) throws IOException, ConsumerException {
        super.writeRecord(record);

        if (!Objects.equals(currentCategory, record.getCategory())) {
            currentCategory = record.getCategory();
            write(CATEGORY_PREFIX);
            if (currentCategory != null) {
                write(currentCategory);
            }
            write(CATEGORY_POSTFIX);
            write(fileSpec.getLineSeparator().getSeparator());
        }

        write(record.getValueOfKeyField());
        write(fileSpec.getValueDelimiter());
        if (!record.getValueField().valueIsNullOrEmpty()) {
            write(record.getValueOfValueField());
        }
        write(fileSpec.getLineSeparator().getSeparator());
    }

}