package si.jernej.ocp.records;

public class RecordExamples
{

    // simple record with two properties
    public record Data(int x, int y)
    {
    }

    // simple record with a validating constructor
    public record Data2(int x, int y)
    {
        public Data2
        {
            if (x > y)
                throw new IllegalArgumentException();

            x += 100;
            y += 100;
        }
    }

    public record Data3(int x, int y)
    {
        public Data3(int x, int y)  // long form of the validating constructor
        {
            if (x > y)
                throw new IllegalArgumentException();

            x += 100;
            y += 100;

            this.x = x;
            this.y = y;
        }
    }

    public record Data4(int x, int y)
    {
        public Data4(int x, int y, int sumLim)  // a non-canonical constructor must delegate to the canonical constructor in the first statement of its body
        {
            this(x, y);

            if (x + y > sumLim)
                throw new IllegalArgumentException();
        }
    }

    public interface I
    {
        int run(int x);
    }

    public record Data5(int x) implements I  // records may implement interfaces
    {
        @Override
        public int run(int x)
        {
            return this.x + x;
        }
    }

    // record may contain static fields, static interfaces, static classes, and static abstract classes
    public record Data6()
    {
        public interface I
        {
        }

        public static class A
        {
        }

        public abstract static class B
        {
        }

        public static final int a = 1;
        public static int b = 2;

        public enum E
        {
            A, B, C
        }
    }

    // record may contain instance interfaces, instance classes, and instance abstract classes
    // they may not contain instance fields
    public record Data8()
    {
        public interface I
        {
        }

        public Data8
        {
        }

        public class A
        {
        }

        public abstract class B
        {
        }
    }
}
