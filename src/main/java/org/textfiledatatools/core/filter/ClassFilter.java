package org.textfiledatatools.core.filter;

import org.textfiledatatools.core.Record;

import java.util.Objects;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class ClassFilter implements RecordFilter<Record> {

    private final Class<? extends Record> recordClass;

    public ClassFilter(Class<? extends Record> recordClass) {
        Objects.requireNonNull(recordClass);
        this.recordClass = recordClass;
    }

    @Override
    public boolean isValid(Record record) {
        return recordClass.equals(record.getClass());
    }

}