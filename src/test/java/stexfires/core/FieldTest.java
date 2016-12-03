package stexfires.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private static Field fieldNullValue;
    private static Field fieldEmptyValue;
    private static Field fieldValueFirst;
    private static Field fieldValueFirstLast;
    private static Field fieldValueLast;

    @BeforeAll
    static void setUp() {
        fieldNullValue = new Field(0, true, null);
        fieldEmptyValue = new Field(0, true, "");
        fieldValueFirst = new Field(0, false, "test");
        fieldValueFirstLast = new Field(0, true, "test");
        fieldValueLast = new Field(1, true, "test");
    }

    @Test
    void getIndex() {
        assertEquals(0, fieldNullValue.getIndex());
        assertEquals(0, fieldEmptyValue.getIndex());
        assertEquals(0, fieldValueFirst.getIndex());
        assertEquals(0, fieldValueFirstLast.getIndex());
        assertEquals(1, fieldValueLast.getIndex());
    }

    @Test
    void isFirst() {
        assertTrue(fieldNullValue.isFirst());
        assertTrue(fieldEmptyValue.isFirst());
        assertTrue(fieldValueFirst.isFirst());
        assertTrue(fieldValueFirstLast.isFirst());
        assertFalse(fieldValueLast.isFirst());
    }

    @Test
    void isLast() {
        assertTrue(fieldNullValue.isLast());
        assertTrue(fieldEmptyValue.isLast());
        assertFalse(fieldValueFirst.isLast());
        assertTrue(fieldValueFirstLast.isLast());
        assertTrue(fieldValueLast.isLast());
    }

    @Test
    void getValue() {
        assertNull(fieldNullValue.getValue());
        assertEquals("", fieldEmptyValue.getValue());
        assertEquals("test", fieldValueFirst.getValue());
        assertEquals("test", fieldValueFirstLast.getValue());
        assertEquals("test", fieldValueLast.getValue());
    }

    @Test
    void getValueOrElse() {
        assertEquals("else", fieldNullValue.getValueOrElse("else"));
        assertEquals("", fieldEmptyValue.getValueOrElse("else"));
        assertEquals("test", fieldValueFirst.getValueOrElse("else"));
        assertEquals("test", fieldValueFirstLast.getValueOrElse("else"));
        assertEquals("test", fieldValueLast.getValueOrElse("else"));
    }

    @Test
    void getValueAsOptional() {
        assertNotNull(fieldNullValue.getValueAsOptional());
        assertNotNull(fieldEmptyValue.getValueAsOptional());
        assertNotNull(fieldValueFirst.getValueAsOptional());
        assertNotNull(fieldValueFirstLast.getValueAsOptional());
        assertNotNull(fieldValueLast.getValueAsOptional());

        assertFalse(fieldNullValue.getValueAsOptional().isPresent());
        assertTrue(fieldEmptyValue.getValueAsOptional().isPresent());
        assertTrue(fieldValueFirst.getValueAsOptional().isPresent());
        assertTrue(fieldValueFirstLast.getValueAsOptional().isPresent());
        assertTrue(fieldValueLast.getValueAsOptional().isPresent());

        assertEquals("ELSE", fieldNullValue.getValueAsOptional().orElse("ELSE"));
        assertEquals("", fieldEmptyValue.getValueAsOptional().orElse("ELSE"));
        assertEquals("test", fieldValueFirst.getValueAsOptional().orElse("ELSE"));
        assertEquals("test", fieldValueFirstLast.getValueAsOptional().orElse("ELSE"));
        assertEquals("test", fieldValueLast.getValueAsOptional().orElse("ELSE"));
    }

    @Test
    void valueEquals() {
        assertTrue(fieldNullValue.valueEquals(null));
        assertFalse(fieldEmptyValue.valueEquals(null));
        assertFalse(fieldValueFirst.valueEquals(null));
        assertFalse(fieldValueFirstLast.valueEquals(null));
        assertFalse(fieldValueLast.valueEquals(null));

        assertFalse(fieldNullValue.valueEquals(""));
        assertTrue(fieldEmptyValue.valueEquals(""));
        assertFalse(fieldValueFirst.valueEquals(""));
        assertFalse(fieldValueFirstLast.valueEquals(""));
        assertFalse(fieldValueLast.valueEquals(""));

        assertFalse(fieldNullValue.valueEquals("test"));
        assertFalse(fieldEmptyValue.valueEquals("test"));
        assertTrue(fieldValueFirst.valueEquals("test"));
        assertTrue(fieldValueFirstLast.valueEquals("test"));
        assertTrue(fieldValueLast.valueEquals("test"));
    }

    @Test
    void valueIsNull() {
        assertTrue(fieldNullValue.valueIsNull());
        assertFalse(fieldEmptyValue.valueIsNull());
        assertFalse(fieldValueFirst.valueIsNull());
        assertFalse(fieldValueFirstLast.valueIsNull());
        assertFalse(fieldValueLast.valueIsNull());
    }

    @Test
    void valueIsEmpty() {
        assertFalse(fieldNullValue.valueIsEmpty());
        assertTrue(fieldEmptyValue.valueIsEmpty());
        assertFalse(fieldValueFirst.valueIsEmpty());
        assertFalse(fieldValueFirstLast.valueIsEmpty());
        assertFalse(fieldValueLast.valueIsEmpty());
    }

    @Test
    void valueIsNullOrEmpty() {
        assertTrue(fieldNullValue.valueIsNullOrEmpty());
        assertTrue(fieldEmptyValue.valueIsNullOrEmpty());
        assertFalse(fieldValueFirst.valueIsNullOrEmpty());
        assertFalse(fieldValueFirstLast.valueIsNullOrEmpty());
        assertFalse(fieldValueLast.valueIsNullOrEmpty());
    }

    @Test
    void length() {
        assertEquals(0, fieldNullValue.length());
        assertEquals(0, fieldEmptyValue.length());
        assertEquals(4, fieldValueFirst.length());
        assertEquals(4, fieldValueFirstLast.length());
        assertEquals(4, fieldValueLast.length());
    }

    @Test
    void stream() {
        assertNotNull(fieldNullValue.stream());
        assertNotNull(fieldEmptyValue.stream());
        assertNotNull(fieldValueFirst.stream());
        assertNotNull(fieldValueFirstLast.stream());
        assertNotNull(fieldValueLast.stream());

        assertEquals(0, fieldNullValue.stream().count());
        assertEquals(1, fieldEmptyValue.stream().count());
        assertEquals(1, fieldValueFirst.stream().count());
        assertEquals(1, fieldValueFirstLast.stream().count());
        assertEquals(1, fieldValueLast.stream().count());

        assertEquals("ELSE", fieldNullValue.stream().findFirst().orElse("ELSE"));
        assertEquals("", fieldEmptyValue.stream().findFirst().orElse("ELSE"));
        assertEquals("test", fieldValueFirst.stream().findFirst().orElse("ELSE"));
        assertEquals("test", fieldValueFirstLast.stream().findFirst().orElse("ELSE"));
        assertEquals("test", fieldValueLast.stream().findFirst().orElse("ELSE"));
    }

    @Test
    void testEquals() {
        assertTrue(fieldNullValue.equals(fieldNullValue));
        assertFalse(fieldNullValue.equals(fieldEmptyValue));
        assertFalse(fieldNullValue.equals(fieldValueFirst));
        assertFalse(fieldNullValue.equals(fieldValueFirstLast));
        assertFalse(fieldNullValue.equals(fieldValueLast));

        assertFalse(fieldEmptyValue.equals(fieldNullValue));
        assertTrue(fieldEmptyValue.equals(fieldEmptyValue));
        assertFalse(fieldEmptyValue.equals(fieldValueFirst));
        assertFalse(fieldEmptyValue.equals(fieldValueFirstLast));
        assertFalse(fieldEmptyValue.equals(fieldValueLast));

        assertFalse(fieldValueFirst.equals(fieldNullValue));
        assertFalse(fieldValueFirst.equals(fieldEmptyValue));
        assertTrue(fieldValueFirst.equals(fieldValueFirst));
        assertFalse(fieldValueFirst.equals(fieldValueFirstLast));
        assertFalse(fieldValueFirst.equals(fieldValueLast));

        assertFalse(fieldValueFirstLast.equals(fieldNullValue));
        assertFalse(fieldValueFirstLast.equals(fieldEmptyValue));
        assertFalse(fieldValueFirstLast.equals(fieldValueFirst));
        assertTrue(fieldValueFirstLast.equals(fieldValueFirstLast));
        assertFalse(fieldValueFirstLast.equals(fieldValueLast));

        assertFalse(fieldValueLast.equals(fieldNullValue));
        assertFalse(fieldValueLast.equals(fieldEmptyValue));
        assertFalse(fieldValueLast.equals(fieldValueFirst));
        assertFalse(fieldValueLast.equals(fieldValueFirstLast));
        assertTrue(fieldValueLast.equals(fieldValueLast));

        assertTrue(fieldNullValue.equals(new Field(0, true, null)));
        assertTrue(fieldEmptyValue.equals(new Field(0, true, "")));
        assertTrue(fieldValueFirst.equals(new Field(0, false, "test")));
        assertTrue(fieldValueFirstLast.equals(new Field(0, true, "test")));
        assertTrue(fieldValueLast.equals(new Field(1, true, "test")));

        assertFalse(fieldNullValue.equals(new Field(1, true, null)));
        assertFalse(fieldEmptyValue.equals(new Field(1, true, "")));
        assertFalse(fieldValueFirst.equals(new Field(1, false, "test")));
        assertFalse(fieldValueFirstLast.equals(new Field(1, true, "test")));
        assertFalse(fieldValueLast.equals(new Field(0, true, "test")));

        assertFalse(fieldNullValue.equals(new Field(0, false, null)));
        assertFalse(fieldEmptyValue.equals(new Field(0, false, "")));
        assertFalse(fieldValueFirst.equals(new Field(0, true, "test")));
        assertFalse(fieldValueFirstLast.equals(new Field(0, false, "test")));
        assertFalse(fieldValueLast.equals(new Field(1, false, "test")));

        assertTrue(fieldNullValue.equals(new Field(0, true, null)));
        assertFalse(fieldEmptyValue.equals(new Field(0, true, null)));
        assertFalse(fieldValueFirst.equals(new Field(0, false, null)));
        assertFalse(fieldValueFirstLast.equals(new Field(0, true, null)));
        assertFalse(fieldValueLast.equals(new Field(1, true, null)));

        assertFalse(fieldNullValue.equals(new Field(0, true, "")));
        assertTrue(fieldEmptyValue.equals(new Field(0, true, "")));
        assertFalse(fieldValueFirst.equals(new Field(0, false, "")));
        assertFalse(fieldValueFirstLast.equals(new Field(0, true, "")));
        assertFalse(fieldValueLast.equals(new Field(1, true, "")));

        assertFalse(fieldNullValue.equals(new Field(0, true, "test")));
        assertFalse(fieldEmptyValue.equals(new Field(0, true, "test")));
        assertTrue(fieldValueFirst.equals(new Field(0, false, "test")));
        assertTrue(fieldValueFirstLast.equals(new Field(0, true, "test")));
        assertTrue(fieldValueLast.equals(new Field(1, true, "test")));

        assertFalse(fieldNullValue.equals(new Field(0, true, "test2")));
        assertFalse(fieldEmptyValue.equals(new Field(0, true, "test2")));
        assertFalse(fieldValueFirst.equals(new Field(0, false, "test2")));
        assertFalse(fieldValueFirstLast.equals(new Field(0, true, "test2")));
        assertFalse(fieldValueLast.equals(new Field(1, true, "test2")));
    }

    @Test
    void testHashCode() {
        assertEquals(fieldNullValue.hashCode(), fieldNullValue.hashCode());
        assertEquals(fieldEmptyValue.hashCode(), fieldEmptyValue.hashCode());
        assertEquals(fieldValueFirst.hashCode(), fieldValueFirst.hashCode());
        assertEquals(fieldValueFirstLast.hashCode(), fieldValueFirstLast.hashCode());
        assertEquals(fieldValueLast.hashCode(), fieldValueLast.hashCode());

        assertNotEquals(fieldNullValue.hashCode(), fieldEmptyValue.hashCode());
        assertNotEquals(fieldNullValue.hashCode(), fieldValueFirst.hashCode());
        assertNotEquals(fieldNullValue.hashCode(), fieldValueFirstLast.hashCode());
        assertNotEquals(fieldNullValue.hashCode(), fieldValueLast.hashCode());

        assertNotEquals(fieldEmptyValue.hashCode(), fieldValueFirst.hashCode());
        assertNotEquals(fieldEmptyValue.hashCode(), fieldValueFirstLast.hashCode());
        assertNotEquals(fieldEmptyValue.hashCode(), fieldValueLast.hashCode());

        assertNotEquals(fieldValueFirst.hashCode(), fieldValueFirstLast.hashCode());
        assertNotEquals(fieldValueFirst.hashCode(), fieldValueLast.hashCode());

        assertNotEquals(fieldValueFirstLast.hashCode(), fieldValueLast.hashCode());

        assertEquals(
                new Field(3, false, "TEST").hashCode(),
                new Field(3, false, "TEST").hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Field{0, true, null}", fieldNullValue.toString());
        assertEquals("Field{0, true, ''}", fieldEmptyValue.toString());
        assertEquals("Field{0, false, 'test'}", fieldValueFirst.toString());
        assertEquals("Field{0, true, 'test'}", fieldValueFirstLast.toString());
        assertEquals("Field{1, true, 'test'}", fieldValueLast.toString());
    }

}