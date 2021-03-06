package stexfires.io.singlevalue;

import stexfires.core.record.SingleRecord;
import stexfires.core.record.ValueRecord;
import stexfires.io.BaseRecordFile;
import stexfires.io.ReadableRecordProducer;
import stexfires.io.WritableRecordConsumer;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class SingleValueFile extends BaseRecordFile<ValueRecord, SingleRecord> {

    protected final SingleValueFileSpec fileSpec;

    public SingleValueFile(Path path, SingleValueFileSpec fileSpec) {
        super(path);
        Objects.requireNonNull(fileSpec);
        this.fileSpec = fileSpec;
    }

    @Override
    public ReadableRecordProducer<SingleRecord> openProducer() throws IOException {
        return new SingleValueProducer(
                newBufferedReader(newCharsetDecoder(fileSpec.getCharset(), fileSpec.getCodingErrorAction())),
                fileSpec);
    }

    @Override
    public WritableRecordConsumer<ValueRecord> openConsumer(OpenOption... writeOptions) throws IOException {
        return new SingleValueConsumer(
                newBufferedWriter(newCharsetEncoder(fileSpec.getCharset(), fileSpec.getCodingErrorAction()),
                        writeOptions),
                fileSpec);
    }

}
