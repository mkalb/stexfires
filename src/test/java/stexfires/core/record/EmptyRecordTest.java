package stexfires.core.record;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyRecordTest {

    private static EmptyRecord record;

    @BeforeAll
    static void setUp() {
        record = new EmptyRecord();
    }

    @Test
    void arrayOfFields() {
        assertNotNull(record.arrayOfFields());
        assertEquals(0, record.arrayOfFields().length);
    }

    @Test
    void listOfFields() {
        assertNotNull(record.listOfFields());
        assertEquals(0, record.listOfFields().size());
    }

    @Test
    void streamOfFields() {
        assertNotNull(record.streamOfFields());
        assertEquals(0, record.streamOfFields().count());
    }

    @Test
    void getCategory() {
        assertNull(record.getCategory());
    }

    @Test
    void getRecordId() {
        assertNull(record.getRecordId());
    }

    @Test
    void size() {
        assertEquals(0, record.size());
    }

    @Test
    void isEmpty() {
        assertTrue(record.isEmpty());
    }

    @Test
    void isValidIndex() {
        assertAll(
                () -> assertFalse(record.isValidIndex(-1)),
                () -> assertFalse(record.isValidIndex(0)),
                () -> assertFalse(record.isValidIndex(1)),
                () -> assertFalse(record.isValidIndex(2))
        );
    }

    @Test
    void getFieldAt() {
        assertAll(
                () -> assertNull(record.getFieldAt(-1)),
                () -> assertNull(record.getFieldAt(0)),
                () -> assertNull(record.getFieldAt(1)),
                () -> assertNull(record.getFieldAt(2))
        );
    }

    @Test
    void getFirstField() {
        assertNull(record.getFirstField());
    }

    @Test
    void getLastField() {
        assertNull(record.getLastField());
    }

    @Test
    void getValueAt() {
        assertAll(
                () -> assertNull(record.getValueAt(-1)),
                () -> assertNull(record.getValueAt(0)),
                () -> assertNull(record.getValueAt(1)),
                () -> assertNull(record.getValueAt(2))
        );
    }

    @Test
    void getValueAtOrElse() {
        assertAll(
                () -> assertEquals("ELSE", record.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("ELSE", record.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", record.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", record.getValueAtOrElse(2, "ELSE"))
        );
    }

    @Test
    void getValueOfFirstField() {
        assertNull(record.getValueOfFirstField());
    }

    @Test
    void getValueOfLastField() {
        assertNull(record.getValueOfLastField());
    }

    @Test
    void testEquals() {
        assertEquals(record, new EmptyRecord());
    }

    @Test
    void testHashCode() {
        assertEquals(record.hashCode(), new EmptyRecord().hashCode());
        assertEquals(0, record.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("EmptyRecord{}", record.toString());
    }

}