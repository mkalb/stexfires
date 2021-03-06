package stexfires.io.config;

import stexfires.util.LineSeparator;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author Mathias Kalb
 * @since 0.1
 */
public final class ConfigFileSpec {

    public static final CodingErrorAction DEFAULT_CODING_ERROR_ACTION = CodingErrorAction.REPORT;
    public static final String DEFAULT_VALUE_DELIMITER = "=";

    public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;

    public static final String NULL_CATEGORY = "";
    public static final String NULL_KEY = "";
    public static final String NULL_VALUE = "";

    public static final String CATEGORY_PREFIX = "[";
    public static final String CATEGORY_POSTFIX = "]";

    private final Charset charset;
    private final CodingErrorAction codingErrorAction;
    private final String valueDelimiter;

    private final LineSeparator lineSeparator;

    public ConfigFileSpec(Charset charset, CodingErrorAction codingErrorAction, String valueDelimiter,
                          LineSeparator lineSeparator) {
        Objects.requireNonNull(charset);
        Objects.requireNonNull(codingErrorAction);
        Objects.requireNonNull(valueDelimiter);
        Objects.requireNonNull(lineSeparator);
        this.charset = charset;
        this.codingErrorAction = codingErrorAction;
        this.valueDelimiter = valueDelimiter;

        this.lineSeparator = lineSeparator;
    }

    public static ConfigFileSpec read(Charset charset, String valueDelimiter) {
        return new ConfigFileSpec(charset, DEFAULT_CODING_ERROR_ACTION, valueDelimiter, DEFAULT_LINE_SEPARATOR);
    }

    public static ConfigFileSpec read(Charset charset, CodingErrorAction codingErrorAction, String valueDelimiter) {
        return new ConfigFileSpec(charset, codingErrorAction, valueDelimiter, DEFAULT_LINE_SEPARATOR);
    }

    public static ConfigFileSpec write(Charset charset, String valueDelimiter,
                                       LineSeparator lineSeparator) {
        return new ConfigFileSpec(charset, DEFAULT_CODING_ERROR_ACTION, valueDelimiter, lineSeparator);
    }

    public static ConfigFileSpec write(Charset charset, CodingErrorAction codingErrorAction, String valueDelimiter,
                                       LineSeparator lineSeparator) {
        return new ConfigFileSpec(charset, codingErrorAction, valueDelimiter, lineSeparator);
    }

    public ConfigFile file(Path path) {
        return new ConfigFile(path, this);
    }

    public Charset getCharset() {
        return charset;
    }

    public CodingErrorAction getCodingErrorAction() {
        return codingErrorAction;
    }

    public String getValueDelimiter() {
        return valueDelimiter;
    }

    public LineSeparator getLineSeparator() {
        return lineSeparator;
    }

}
