# Method References

## Reference to a Static Method

Take a look at the following example:

```
private static class StringUtils
{
    public static String capitalize(String s)
    {
        return s.toUpperCase();
    }
}


List<String> res = l.stream().map(StringUtils::capitalize).toList()
```

## Reference to an Instance Method of a Particular Object

Take a look at the following example:

```
class SpecialStringComparator implements Comparator<String>
{
    @Override
    public int compare(String s1, String s2)
    {
        assert s1.length() >= 2 && s2.length() >= 2;
        return Character.compare(s1.charAt(1), s2.charAt(1));
    }
}

SpecialStringComparator specialStringComparator = new SpecialStringComparator();
l.sort(specialStringComparator::compare); 
```

## Reference to an Instance Method of an Arbitrary Object of a Particular Type

Take a look at the following example:

```
class A
{
    private int a;

    public A(int a)
    {
        this.a = a;
    }

    public void multiplyPropertyBy2()
    {
        this.a *= 2;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.a);
    }
} 

List<A> l = List.of(new A(1), new A(2), new A(3));

l2.forEach(A::multiplyPropertyBy2);
```

## Reference to a Constructor

Take a look at the following example:

```
List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
List<A> res4 = l.stream().map(A::new).toList()
```