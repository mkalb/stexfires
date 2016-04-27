package org.textfiledatatools.core.filter;

import org.textfiledatatools.core.Record;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class IsEmptyFilter implements RecordFilter<Record> {

    @Override
    public boolean isValid(Record record) {
        return record.isEmpty();
    }

}
