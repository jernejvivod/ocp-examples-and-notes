package si.jernej.ocp.test.records;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.records.RecordExamples;

class RecordsTest
{
    @Test
    void testSimpleRecord()
    {
        RecordExamples.Data data = new RecordExamples.Data(1, 2);
        Assertions.assertEquals(1, data.x());
        Assertions.assertEquals(2, data.y());
    }

    @Test
    void testSimpleRecordWithValidatingConstructor()
    {
        RecordExamples.Data3 data = new RecordExamples.Data3(1, 2);
        Assertions.assertEquals(101, data.x());
        Assertions.assertEquals(102, data.y());

        Assertions.assertThrows(IllegalArgumentException.class, () -> new RecordExamples.Data3(2, 1));
    }

    @Test
    void testSimpleRecordWithDelegatingValidatingConstructor()
    {
        RecordExamples.Data4 data = new RecordExamples.Data4(1, 2, 3);
        Assertions.assertEquals(1, data.x());
        Assertions.assertEquals(2, data.y());

        Assertions.assertThrows(IllegalArgumentException.class, () -> new RecordExamples.Data4(2, 1, 2));
    }

    @Test
    void testSimpleRecordImplementingInterface()
    {
        RecordExamples.Data5 data = new RecordExamples.Data5(3);

        Assertions.assertEquals(7, data.run(4));
    }

    @Test
    void testRecordWithStaticMembers()
    {
        class C implements RecordExamples.Data6.I
        {
        }

        Assertions.assertEquals(RecordExamples.Data6.I.class, C.class.getInterfaces()[0]);

        RecordExamples.Data6.A a = new RecordExamples.Data6.A();
        Assertions.assertEquals(RecordExamples.Data6.A.class, a.getClass());

        class D extends RecordExamples.Data6.B
        {
        }

        Assertions.assertEquals(RecordExamples.Data6.B.class, D.class.getSuperclass());

        Assertions.assertEquals(1, RecordExamples.Data6.a);
        Assertions.assertEquals(2, RecordExamples.Data6.b);

        RecordExamples.Data6.b = 11;

        Assertions.assertEquals(11, RecordExamples.Data6.b);
    }

    @Test
    void testRecordWithMembers()
    {
        RecordExamples.Data8 data = new RecordExamples.Data8();

        class C implements RecordExamples.Data8.I
        {
        }

        Assertions.assertEquals(RecordExamples.Data8.I.class, C.class.getInterfaces()[0]);

        RecordExamples.Data8.A a = data.new A();
        Assertions.assertEquals(RecordExamples.Data8.A.class, a.getClass());

        // note how inner classes are extended
        // we need a reference to the enclosing class and call the superclass' constructor through it
        class D extends RecordExamples.Data8.B
        {
            public D(RecordExamples.Data8 data)
            {
                data.super();
            }
        }

        D d = new D(new RecordExamples.Data8());

        Assertions.assertEquals(D.class, d.getClass());
        Assertions.assertEquals(D.class.getSuperclass(), RecordExamples.Data8.B.class);
    }
}
