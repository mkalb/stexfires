package stexfires.core.mapper;

import stexfires.core.Record;
import stexfires.core.message.RecordMessage;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public class LookupMapper<T extends Record, R extends Record, K> implements RecordMapper<T, R> {

    protected final Function<? super T, K> keyFunction;
    protected final Function<K, RecordMapper<? super T, ? extends R>> mapperFunction;
    protected final RecordMapper<? super T, ? extends R> defaultMapper;

    public LookupMapper(Function<? super T, K> keyFunction,
                        Function<K, RecordMapper<? super T, ? extends R>> mapperFunction,
                        RecordMapper<? super T, ? extends R> defaultMapper) {
        Objects.requireNonNull(keyFunction);
        Objects.requireNonNull(mapperFunction);
        Objects.requireNonNull(defaultMapper);
        this.keyFunction = keyFunction;
        this.mapperFunction = mapperFunction;
        this.defaultMapper = defaultMapper;
    }

    public static <T extends Record> LookupMapper<T, Record, String> messageMap(RecordMessage<? super T> recordMessage,
                                                                                Map<String, RecordMapper<? super T, Record>> recordMapperMap) {
        Objects.requireNonNull(recordMessage);
        Objects.requireNonNull(recordMapperMap);
        return new LookupMapper<>(recordMessage.asFunction(), recordMapperMap::get, new IdentityMapper<>());
    }

    @Override
    public R map(T record) {
        RecordMapper<? super T, ? extends R> recordMapper = mapperFunction.apply(keyFunction.apply(record));
        return (recordMapper == null) ? defaultMapper.map(record) : recordMapper.map(record);
    }

}
