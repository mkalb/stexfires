package stexfires.core.modifier;

import stexfires.core.Record;
import stexfires.core.record.StandardRecord;
import stexfires.util.Strings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class UnpivotModifier<T extends Record, R extends Record> implements RecordStreamModifier<T, R> {

    protected final Function<? super T, Stream<? extends R>> unpivotFunction;

    public UnpivotModifier(Function<? super T, Stream<? extends R>> unpivotFunction) {
        Objects.requireNonNull(unpivotFunction);
        this.unpivotFunction = unpivotFunction;
    }

    public static <T extends Record, R extends Record> UnpivotModifier<T, R> of(Function<? super T, Collection<? extends R>> unpivotFunction) {
        Objects.requireNonNull(unpivotFunction);
        return new UnpivotModifier<>(record -> unpivotFunction.apply(record).stream());
    }

    public static <T extends Record> UnpivotModifier<T, Record> oneRecordPerValue(Collection<Integer> keyIndexes,
                                                                                  IntFunction<String> valueIndexToIdentifier,
                                                                                  boolean onlyExistingValues,
                                                                                  Collection<Integer> valueIndexes) {
        Objects.requireNonNull(keyIndexes);
        Objects.requireNonNull(valueIndexToIdentifier);
        Objects.requireNonNull(valueIndexes);
        return new UnpivotModifier<>(record ->
                valueIndexes.stream()
                            .filter(valueIndex -> !onlyExistingValues || record.isValidIndex(valueIndex))
                            .map(valueIndex ->
                                    new StandardRecord(record.getCategory(), record.getRecordId(),
                                            Strings.concat(
                                                    // keys
                                                    keyIndexes.stream().map(record::getValueAt),
                                                    // identifier
                                                    Stream.of(valueIndexToIdentifier.apply(valueIndex)),
                                                    // one value
                                                    Stream.of(record.getValueAt(valueIndex)))
                                    )));
    }

    public static <T extends Record> UnpivotModifier<T, Record> oneRecordPerValue(int keyIndex,
                                                                                  IntFunction<String> valueIndexToIdentifier,
                                                                                  boolean onlyExistingValues,
                                                                                  int... valueIndexes) {
        Objects.requireNonNull(valueIndexToIdentifier);
        Objects.requireNonNull(valueIndexes);
        return oneRecordPerValue(
                Collections.singleton(keyIndex),
                valueIndexToIdentifier,
                onlyExistingValues,
                Arrays.stream(valueIndexes).boxed().collect(Collectors.toList())
        );
    }

    public static <T extends Record> UnpivotModifier<T, Record> oneRecordPerValue(int keyIndex,
                                                                                  boolean onlyExistingValues,
                                                                                  Map<Integer, String> valueIndexesAndIdentifiers) {
        Objects.requireNonNull(valueIndexesAndIdentifiers);
        return oneRecordPerValue(
                Collections.singleton(keyIndex),
                valueIndexesAndIdentifiers::get,
                onlyExistingValues,
                valueIndexesAndIdentifiers.keySet()
        );
    }

    @SafeVarargs
    public static <T extends Record> UnpivotModifier<T, Record> oneRecordPerValues(Collection<Integer> keyIndexes,
                                                                                   IntFunction<String> recordIndexToIdentifier,
                                                                                   Collection<Integer>... valueIndexes) {
        Objects.requireNonNull(keyIndexes);
        Objects.requireNonNull(recordIndexToIdentifier);
        Objects.requireNonNull(valueIndexes);
        return new UnpivotModifier<>(record ->
                IntStream.range(0, valueIndexes.length)
                         .mapToObj(recordIndex ->
                                 new StandardRecord(record.getCategory(), record.getRecordId(),
                                         Strings.concat(
                                                 // keys
                                                 keyIndexes.stream().map(record::getValueAt),
                                                 // identifier
                                                 Stream.of(recordIndexToIdentifier.apply(recordIndex)),
                                                 // many values
                                                 valueIndexes[recordIndex].stream().map(valueIndex -> valueIndex != null ? record.getValueAt(valueIndex) : null))
                                 )));
    }

    @Override
    public Stream<R> modify(Stream<T> recordStream) {
        return recordStream.map(unpivotFunction).flatMap(Function.identity());
    }

}
