package stexfires.core.consumer;

import stexfires.core.Record;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class NullConsumer<T extends Record> implements RecordConsumer<T> {

    @Override
    public void consume(T record) {
        // Do nothing.
    }

}
