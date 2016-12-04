package stexfires.core.record;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stexfires.core.Field;

import static org.junit.jupiter.api.Assertions.*;

class SingleRecordTest {

    private static SingleRecord recordNull;
    private static SingleRecord recordEmpty;
    private static SingleRecord recordTest;
    private static SingleRecord recordCategory;
    private static SingleRecord recordRecordId;
    private static SingleRecord record;

    private static Field fieldNull;
    private static Field fieldEmpty;
    private static Field fieldTest;

    @BeforeAll
    static void setUp() {
        recordNull = new SingleRecord(null);
        recordEmpty = new SingleRecord("");
        recordTest = new SingleRecord("test");
        recordCategory = new SingleRecord("category", null, "test");
        recordRecordId = new SingleRecord(null, 1L, "test");
        record = new SingleRecord("category", 1L, "test");

        fieldNull = new Field(0, true, null);
        fieldEmpty = new Field(0, true, "");
        fieldTest = new Field(0, true, "test");
    }

    @Test
    void newValueRecord() {
        assertNotEquals(recordTest, recordTest.newValueRecord("newTest"));
        assertEquals(recordTest, recordTest.newValueRecord("test"));
        assertEquals(new SingleRecord("newTest"),
                recordTest.newValueRecord("newTest"));

        assertNotEquals(record, record.newValueRecord("newTest"));
        assertEquals(record, record.newValueRecord("test"));
        assertEquals(new SingleRecord("category", 1L, "newTest"),
                record.newValueRecord("newTest"));

        assertEquals(recordNull, recordTest.newValueRecord(null));
        assertEquals(recordEmpty, recordTest.newValueRecord(""));
    }

    @Test
    void arrayOfFields() {
        assertNotNull(recordNull.arrayOfFields());
        assertNotNull(recordEmpty.arrayOfFields());
        assertNotNull(recordTest.arrayOfFields());
        assertNotNull(recordCategory.arrayOfFields());
        assertNotNull(recordRecordId.arrayOfFields());
        assertNotNull(record.arrayOfFields());

        assertEquals(1, recordNull.arrayOfFields().length);
        assertEquals(1, recordEmpty.arrayOfFields().length);
        assertEquals(1, recordTest.arrayOfFields().length);
        assertEquals(1, recordCategory.arrayOfFields().length);
        assertEquals(1, recordRecordId.arrayOfFields().length);
        assertEquals(1, record.arrayOfFields().length);

        assertEquals(fieldNull, recordNull.arrayOfFields()[0]);
        assertEquals(fieldEmpty, recordEmpty.arrayOfFields()[0]);
        assertEquals(fieldTest, recordTest.arrayOfFields()[0]);
        assertEquals(fieldTest, recordCategory.arrayOfFields()[0]);
        assertEquals(fieldTest, recordRecordId.arrayOfFields()[0]);
        assertEquals(fieldTest, record.arrayOfFields()[0]);
    }

    @Test
    void listOfFields() {
        assertNotNull(recordNull.listOfFields());
        assertNotNull(recordEmpty.listOfFields());
        assertNotNull(recordTest.listOfFields());
        assertNotNull(recordCategory.listOfFields());
        assertNotNull(recordRecordId.listOfFields());
        assertNotNull(record.listOfFields());

        assertEquals(1, recordNull.listOfFields().size());
        assertEquals(1, recordEmpty.listOfFields().size());
        assertEquals(1, recordTest.listOfFields().size());
        assertEquals(1, recordCategory.listOfFields().size());
        assertEquals(1, recordRecordId.listOfFields().size());
        assertEquals(1, record.listOfFields().size());

        assertEquals(fieldNull, recordNull.listOfFields().get(0));
        assertEquals(fieldEmpty, recordEmpty.listOfFields().get(0));
        assertEquals(fieldTest, recordTest.listOfFields().get(0));
        assertEquals(fieldTest, recordCategory.listOfFields().get(0));
        assertEquals(fieldTest, recordRecordId.listOfFields().get(0));
        assertEquals(fieldTest, record.listOfFields().get(0));
    }

    @Test
    void streamOfFields() {
        assertNotNull(recordNull.streamOfFields());
        assertNotNull(recordEmpty.streamOfFields());
        assertNotNull(recordTest.streamOfFields());
        assertNotNull(recordCategory.streamOfFields());
        assertNotNull(recordRecordId.streamOfFields());
        assertNotNull(record.streamOfFields());

        assertEquals(1, recordNull.streamOfFields().count());
        assertEquals(1, recordEmpty.streamOfFields().count());
        assertEquals(1, recordTest.streamOfFields().count());
        assertEquals(1, recordCategory.streamOfFields().count());
        assertEquals(1, recordRecordId.streamOfFields().count());
        assertEquals(1, record.streamOfFields().count());

        assertEquals(fieldNull, recordNull.streamOfFields().findFirst().orElse(fieldEmpty));
        assertEquals(fieldEmpty, recordEmpty.streamOfFields().findFirst().orElse(fieldNull));
        assertEquals(fieldTest, recordTest.streamOfFields().findFirst().orElse(fieldEmpty));
        assertEquals(fieldTest, recordCategory.streamOfFields().findFirst().orElse(fieldEmpty));
        assertEquals(fieldTest, recordRecordId.streamOfFields().findFirst().orElse(fieldEmpty));
        assertEquals(fieldTest, record.streamOfFields().findFirst().orElse(fieldEmpty));
    }

    @Test
    void getCategory() {
        assertNull(recordNull.getCategory());
        assertNull(recordEmpty.getCategory());
        assertNull(recordTest.getCategory());
        assertEquals("category", recordCategory.getCategory());
        assertNull(recordRecordId.getCategory());
        assertEquals("category", record.getCategory());
    }

    @Test
    void getRecordId() {
        assertNull(recordNull.getRecordId());
        assertNull(recordEmpty.getRecordId());
        assertNull(recordTest.getRecordId());
        assertNull(recordCategory.getRecordId());
        assertEquals(Long.valueOf(1L), recordRecordId.getRecordId());
        assertEquals(Long.valueOf(1L), record.getRecordId());
    }

    @Test
    void size() {
        assertEquals(1, recordNull.size());
        assertEquals(1, recordEmpty.size());
        assertEquals(1, recordTest.size());
        assertEquals(1, recordCategory.size());
        assertEquals(1, recordRecordId.size());
        assertEquals(1, record.size());
    }

    @Test
    void isEmpty() {
        assertFalse(recordNull.isEmpty());
        assertFalse(recordEmpty.isEmpty());
        assertFalse(recordTest.isEmpty());
        assertFalse(recordCategory.isEmpty());
        assertFalse(recordRecordId.isEmpty());
        assertFalse(record.isEmpty());
    }

    @Test
    void isValidIndex() {
        assertAll(
                () -> assertFalse(recordNull.isValidIndex(-1)),
                () -> assertTrue(recordNull.isValidIndex(0)),
                () -> assertFalse(recordNull.isValidIndex(1)),
                () -> assertFalse(recordNull.isValidIndex(2))
        );
        assertAll(
                () -> assertFalse(recordEmpty.isValidIndex(-1)),
                () -> assertTrue(recordEmpty.isValidIndex(0)),
                () -> assertFalse(recordEmpty.isValidIndex(1)),
                () -> assertFalse(recordEmpty.isValidIndex(2))
        );
        assertAll(
                () -> assertFalse(recordTest.isValidIndex(-1)),
                () -> assertTrue(recordTest.isValidIndex(0)),
                () -> assertFalse(recordTest.isValidIndex(1)),
                () -> assertFalse(recordTest.isValidIndex(2))
        );
        assertAll(
                () -> assertFalse(recordCategory.isValidIndex(-1)),
                () -> assertTrue(recordCategory.isValidIndex(0)),
                () -> assertFalse(recordCategory.isValidIndex(1)),
                () -> assertFalse(recordCategory.isValidIndex(2))
        );
        assertAll(
                () -> assertFalse(recordRecordId.isValidIndex(-1)),
                () -> assertTrue(recordRecordId.isValidIndex(0)),
                () -> assertFalse(recordRecordId.isValidIndex(1)),
                () -> assertFalse(recordRecordId.isValidIndex(2))
        );
        assertAll(
                () -> assertFalse(record.isValidIndex(-1)),
                () -> assertTrue(record.isValidIndex(0)),
                () -> assertFalse(record.isValidIndex(1)),
                () -> assertFalse(record.isValidIndex(2))
        );
    }

    @Test
    void getFieldAt() {
        assertAll(
                () -> assertNull(recordNull.getFieldAt(-1)),
                () -> assertEquals(fieldNull, recordNull.getFieldAt(0)),
                () -> assertNull(recordNull.getFieldAt(1)),
                () -> assertNull(recordNull.getFieldAt(2))
        );
        assertAll(
                () -> assertNull(recordEmpty.getFieldAt(-1)),
                () -> assertEquals(fieldEmpty, recordEmpty.getFieldAt(0)),
                () -> assertNull(recordEmpty.getFieldAt(1)),
                () -> assertNull(recordEmpty.getFieldAt(2))
        );
        assertAll(
                () -> assertNull(recordTest.getFieldAt(-1)),
                () -> assertEquals(fieldTest, recordTest.getFieldAt(0)),
                () -> assertNull(recordTest.getFieldAt(1)),
                () -> assertNull(recordTest.getFieldAt(2))
        );
        assertAll(
                () -> assertNull(recordCategory.getFieldAt(-1)),
                () -> assertEquals(fieldTest, recordCategory.getFieldAt(0)),
                () -> assertNull(recordCategory.getFieldAt(1)),
                () -> assertNull(recordCategory.getFieldAt(2))
        );
        assertAll(
                () -> assertNull(recordRecordId.getFieldAt(-1)),
                () -> assertEquals(fieldTest, recordRecordId.getFieldAt(0)),
                () -> assertNull(recordRecordId.getFieldAt(1)),
                () -> assertNull(recordRecordId.getFieldAt(2))
        );
        assertAll(
                () -> assertNull(record.getFieldAt(-1)),
                () -> assertEquals(fieldTest, record.getFieldAt(0)),
                () -> assertNull(record.getFieldAt(1)),
                () -> assertNull(record.getFieldAt(2))
        );
    }

    @Test
    void getFirstField() {
        assertEquals(fieldNull, recordNull.getFirstField());
        assertEquals(fieldEmpty, recordEmpty.getFirstField());
        assertEquals(fieldTest, recordTest.getFirstField());
        assertEquals(fieldTest, recordCategory.getFirstField());
        assertEquals(fieldTest, recordRecordId.getFirstField());
        assertEquals(fieldTest, record.getFirstField());
    }

    @Test
    void getLastField() {
        assertEquals(fieldNull, recordNull.getLastField());
        assertEquals(fieldEmpty, recordEmpty.getLastField());
        assertEquals(fieldTest, recordTest.getLastField());
        assertEquals(fieldTest, recordCategory.getLastField());
        assertEquals(fieldTest, recordRecordId.getLastField());
        assertEquals(fieldTest, record.getLastField());
    }

    @Test
    void getValueField() {
        assertEquals(fieldNull, recordNull.getValueField());
        assertEquals(fieldEmpty, recordEmpty.getValueField());
        assertEquals(fieldTest, recordTest.getValueField());
        assertEquals(fieldTest, recordCategory.getValueField());
        assertEquals(fieldTest, recordRecordId.getValueField());
        assertEquals(fieldTest, record.getValueField());
    }

    @Test
    void getValueAt() {
        assertAll(
                () -> assertNull(recordNull.getValueAt(-1)),
                () -> assertNull(recordNull.getValueAt(0)),
                () -> assertNull(recordNull.getValueAt(1)),
                () -> assertNull(recordNull.getValueAt(2))
        );
        assertAll(
                () -> assertNull(recordEmpty.getValueAt(-1)),
                () -> assertEquals("", recordEmpty.getValueAt(0)),
                () -> assertNull(recordEmpty.getValueAt(1)),
                () -> assertNull(recordEmpty.getValueAt(2))
        );
        assertAll(
                () -> assertNull(recordTest.getValueAt(-1)),
                () -> assertEquals("test", recordTest.getValueAt(0)),
                () -> assertNull(recordTest.getValueAt(1)),
                () -> assertNull(recordTest.getValueAt(2))
        );
        assertAll(
                () -> assertNull(recordCategory.getValueAt(-1)),
                () -> assertEquals("test", recordCategory.getValueAt(0)),
                () -> assertNull(recordCategory.getValueAt(1)),
                () -> assertNull(recordCategory.getValueAt(2))
        );
        assertAll(
                () -> assertNull(recordRecordId.getValueAt(-1)),
                () -> assertEquals("test", recordRecordId.getValueAt(0)),
                () -> assertNull(recordRecordId.getValueAt(1)),
                () -> assertNull(recordRecordId.getValueAt(2))
        );
        assertAll(
                () -> assertNull(record.getValueAt(-1)),
                () -> assertEquals("test", record.getValueAt(0)),
                () -> assertNull(record.getValueAt(1)),
                () -> assertNull(record.getValueAt(2))
        );
    }

    @Test
    void getValueAtOrElse() {
        assertAll(
                () -> assertEquals("ELSE", recordNull.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("ELSE", recordNull.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", recordNull.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", recordNull.getValueAtOrElse(2, "ELSE"))
        );
        assertAll(
                () -> assertEquals("ELSE", recordEmpty.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("", recordEmpty.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", recordEmpty.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", recordEmpty.getValueAtOrElse(2, "ELSE"))
        );
        assertAll(
                () -> assertEquals("ELSE", recordTest.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("test", recordTest.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", recordTest.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", recordTest.getValueAtOrElse(2, "ELSE"))
        );
        assertAll(
                () -> assertEquals("ELSE", recordCategory.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("test", recordCategory.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", recordCategory.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", recordCategory.getValueAtOrElse(2, "ELSE"))
        );
        assertAll(
                () -> assertEquals("ELSE", recordRecordId.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("test", recordRecordId.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", recordRecordId.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", recordRecordId.getValueAtOrElse(2, "ELSE"))
        );
        assertAll(
                () -> assertEquals("ELSE", record.getValueAtOrElse(-1, "ELSE")),
                () -> assertEquals("test", record.getValueAtOrElse(0, "ELSE")),
                () -> assertEquals("ELSE", record.getValueAtOrElse(1, "ELSE")),
                () -> assertEquals("ELSE", record.getValueAtOrElse(2, "ELSE"))
        );
    }

    @Test
    void getValueOfFirstField() {
        assertNull(recordNull.getValueOfFirstField());
        assertEquals("", recordEmpty.getValueOfFirstField());
        assertEquals("test", recordTest.getValueOfFirstField());
        assertEquals("test", recordCategory.getValueOfFirstField());
        assertEquals("test", recordRecordId.getValueOfFirstField());
        assertEquals("test", record.getValueOfFirstField());
    }

    @Test
    void getValueOfLastField() {
        assertNull(recordNull.getValueOfLastField());
        assertEquals("", recordEmpty.getValueOfLastField());
        assertEquals("test", recordTest.getValueOfLastField());
        assertEquals("test", recordCategory.getValueOfLastField());
        assertEquals("test", recordRecordId.getValueOfLastField());
        assertEquals("test", record.getValueOfLastField());
    }

    @Test
    void getValueOfValueField() {
        assertNull(recordNull.getValueOfValueField());
        assertEquals("", recordEmpty.getValueOfValueField());
        assertEquals("test", recordTest.getValueOfValueField());
        assertEquals("test", recordCategory.getValueOfValueField());
        assertEquals("test", recordRecordId.getValueOfValueField());
        assertEquals("test", record.getValueOfValueField());
    }

    @Test
    void testEquals() {
        assertEquals(recordNull, new SingleRecord(null));
        assertEquals(recordEmpty, new SingleRecord(""));
        assertEquals(recordTest, new SingleRecord("test"));
        assertEquals(recordCategory, new SingleRecord("category", null, "test"));
        assertEquals(recordRecordId, new SingleRecord(null, 1L, "test"));
        assertEquals(record, new SingleRecord("category", 1L, "test"));

        assertEquals(recordTest, new SingleRecord(null, null, "test"));

        assertEquals(recordNull, recordNull);
        assertNotEquals(recordNull, recordEmpty);
        assertNotEquals(recordNull, recordTest);
        assertNotEquals(recordNull, recordCategory);
        assertNotEquals(recordNull, recordRecordId);
        assertNotEquals(recordNull, record);

        assertNotEquals(recordEmpty, recordNull);
        assertEquals(recordEmpty, recordEmpty);
        assertNotEquals(recordEmpty, recordTest);
        assertNotEquals(recordEmpty, recordCategory);
        assertNotEquals(recordEmpty, recordRecordId);
        assertNotEquals(recordEmpty, record);

        assertNotEquals(recordTest, recordNull);
        assertNotEquals(recordTest, recordEmpty);
        assertEquals(recordTest, recordTest);
        assertNotEquals(recordTest, recordCategory);
        assertNotEquals(recordTest, recordRecordId);
        assertNotEquals(recordTest, record);

        assertNotEquals(recordCategory, recordNull);
        assertNotEquals(recordCategory, recordEmpty);
        assertNotEquals(recordCategory, recordTest);
        assertEquals(recordCategory, recordCategory);
        assertNotEquals(recordCategory, recordRecordId);
        assertNotEquals(recordCategory, record);

        assertNotEquals(recordRecordId, recordNull);
        assertNotEquals(recordRecordId, recordEmpty);
        assertNotEquals(recordRecordId, recordTest);
        assertNotEquals(recordRecordId, recordCategory);
        assertEquals(recordRecordId, recordRecordId);
        assertNotEquals(recordRecordId, record);

        assertNotEquals(record, recordNull);
        assertNotEquals(record, recordEmpty);
        assertNotEquals(record, recordTest);
        assertNotEquals(record, recordCategory);
        assertNotEquals(record, recordRecordId);
        assertEquals(record, record);
    }

    @Test
    void testHashCode() {
        assertEquals(recordTest.hashCode(), new SingleRecord(null, null, "test").hashCode());

        assertEquals(recordNull.hashCode(), recordNull.hashCode());
        assertNotEquals(recordNull.hashCode(), recordEmpty.hashCode());
        assertNotEquals(recordNull.hashCode(), recordTest.hashCode());
        assertNotEquals(recordNull.hashCode(), recordCategory.hashCode());
        assertNotEquals(recordNull.hashCode(), recordRecordId.hashCode());
        assertNotEquals(recordNull.hashCode(), record.hashCode());

        assertNotEquals(recordEmpty.hashCode(), recordNull.hashCode());
        assertEquals(recordEmpty.hashCode(), recordEmpty.hashCode());
        assertNotEquals(recordEmpty.hashCode(), recordTest.hashCode());
        assertNotEquals(recordEmpty.hashCode(), recordCategory.hashCode());
        assertNotEquals(recordEmpty.hashCode(), recordRecordId.hashCode());
        assertNotEquals(recordEmpty.hashCode(), record.hashCode());

        assertNotEquals(recordTest.hashCode(), recordNull.hashCode());
        assertNotEquals(recordTest.hashCode(), recordEmpty.hashCode());
        assertEquals(recordTest.hashCode(), recordTest.hashCode());
        assertNotEquals(recordTest.hashCode(), recordCategory.hashCode());
        assertNotEquals(recordTest.hashCode(), recordRecordId.hashCode());
        assertNotEquals(recordTest.hashCode(), record.hashCode());

        assertNotEquals(recordCategory.hashCode(), recordNull.hashCode());
        assertNotEquals(recordCategory.hashCode(), recordEmpty.hashCode());
        assertNotEquals(recordCategory.hashCode(), recordTest.hashCode());
        assertEquals(recordCategory.hashCode(), recordCategory.hashCode());
        assertNotEquals(recordCategory.hashCode(), recordRecordId.hashCode());
        assertNotEquals(recordCategory.hashCode(), record.hashCode());

        assertNotEquals(recordRecordId.hashCode(), recordNull.hashCode());
        assertNotEquals(recordRecordId.hashCode(), recordEmpty.hashCode());
        assertNotEquals(recordRecordId.hashCode(), recordTest.hashCode());
        assertNotEquals(recordRecordId.hashCode(), recordCategory.hashCode());
        assertEquals(recordRecordId.hashCode(), recordRecordId.hashCode());
        assertNotEquals(recordRecordId.hashCode(), record.hashCode());

        assertNotEquals(record.hashCode(), recordNull.hashCode());
        assertNotEquals(record.hashCode(), recordEmpty.hashCode());
        assertNotEquals(record.hashCode(), recordTest.hashCode());
        assertNotEquals(record.hashCode(), recordCategory.hashCode());
        assertNotEquals(record.hashCode(), recordRecordId.hashCode());
        assertEquals(record.hashCode(), record.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("SingleRecord{category=null, recordId=null, value=null}", recordNull.toString());
        assertEquals("SingleRecord{category=null, recordId=null, value=}", recordEmpty.toString());
        assertEquals("SingleRecord{category=null, recordId=null, value=test}", recordTest.toString());
        assertEquals("SingleRecord{category=category, recordId=null, value=test}", recordCategory.toString());
        assertEquals("SingleRecord{category=null, recordId=1, value=test}", recordRecordId.toString());
        assertEquals("SingleRecord{category=category, recordId=1, value=test}", record.toString());
    }

}