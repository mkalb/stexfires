package stexfires.io.delimited.simple;

import stexfires.core.Record;
import stexfires.core.producer.ProducerException;
import stexfires.core.producer.UncheckedProducerException;
import stexfires.core.record.StandardRecord;
import stexfires.io.internal.AbstractReadableProducer;
import stexfires.io.internal.AbstractRecordRawDataIterator;
import stexfires.io.internal.RecordRawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class SimpleDelimitedProducer extends AbstractReadableProducer<Record> {

    private static final String NO_VALUE = null;

    protected final SimpleDelimitedFileSpec fileSpec;

    public SimpleDelimitedProducer(BufferedReader reader, SimpleDelimitedFileSpec fileSpec) {
        super(reader);
        Objects.requireNonNull(fileSpec);
        this.fileSpec = fileSpec;
    }

    @Override
    protected AbstractRecordRawDataIterator createIterator() throws UncheckedProducerException {
        return new SimpleDelimitedIterator(reader, fileSpec);
    }

    @Override
    protected Optional<Record> createRecord(RecordRawData recordRawData) throws UncheckedProducerException {
        StandardRecord record = null;
        String rawData = recordRawData.getRawData();

        boolean skipEmptyLine = fileSpec.isSkipEmptyLines() && rawData.isEmpty();

        if (!skipEmptyLine) {
            boolean nonEmptyFound = false;

            // Convert rawData to values
            int beginIndex = 0;
            int endIndex;
            List<String> newValues = new ArrayList<>(fileSpec.getFieldSpecs().size());
            for (SimpleDelimitedFieldSpec fieldSpec : fileSpec.getFieldSpecs()) {
                String value = NO_VALUE;

                endIndex = rawData.indexOf(fileSpec.getFieldDelimiter(), beginIndex);
                if (endIndex == -1) {
                    endIndex = rawData.length();
                }
                if (beginIndex < endIndex) {
                    value = rawData.substring(beginIndex, endIndex);
                    nonEmptyFound = nonEmptyFound || !value.isEmpty();
                }
                beginIndex = endIndex + 1;

                newValues.add(value);
            }

            boolean skipAllNull = fileSpec.isSkipAllNull() && !nonEmptyFound;

            if (!skipAllNull) {
                record = new StandardRecord(recordRawData.getCategory(), recordRawData.getRecordId(), newValues);
            }
        }

        return Optional.ofNullable(record);
    }

    protected static final class SimpleDelimitedIterator extends AbstractRecordRawDataIterator {

        public SimpleDelimitedIterator(BufferedReader reader, SimpleDelimitedFileSpec fileSpec) {
            super(reader, fileSpec.getIgnoreFirst(), fileSpec.getIgnoreLast());
        }

        @Override
        protected RecordRawData readNext(BufferedReader reader, long recordIndex) throws ProducerException, IOException {
            String rawData = reader.readLine();
            if (rawData == null) {
                return null;
            }
            return new RecordRawData(null, recordIndex, rawData);
        }

    }

}
