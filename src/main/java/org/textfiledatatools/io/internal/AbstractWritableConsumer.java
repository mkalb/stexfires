package org.textfiledatatools.io.internal;

import org.textfiledatatools.core.Record;
import org.textfiledatatools.core.consumer.ConsumerException;
import org.textfiledatatools.io.WritableRecordConsumer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

import static org.textfiledatatools.io.internal.WritableConsumerState.*;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public abstract class AbstractWritableConsumer<T extends Record> implements WritableRecordConsumer<T> {

    protected final BufferedWriter writer;

    protected WritableConsumerState state;

    protected AbstractWritableConsumer(BufferedWriter writer) {
        Objects.requireNonNull(writer);
        this.writer = writer;
        state = OPEN;
    }

    protected final void write(String str) throws IOException {
        state.validateNotClosed();
        writer.write(str);
    }

    @Override
    public void writeBefore() throws IOException {
        state = WRITE_BEFORE.validate(state);
    }

    @Override
    public void writeRecord(T record) throws IOException, ConsumerException {
        state = WRITE_RECORDS.validate(state);
    }

    @Override
    public void writeAfter() throws IOException {
        state = WRITE_AFTER.validate(state);
    }

    @Override
    public void close() throws IOException {
        state = CLOSE.validate(state);
        writer.close();
    }

}