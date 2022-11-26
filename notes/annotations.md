# Annotations

The purpose of an annotation is to assign metadata attributes to classes, methods, variables, and other Java types.

Annotations function a lot like interfaces. They allow as to mark for example a class without changing the class hierarchy. While interfaces can be applied only to classes, annotations can be applied to any declaration including classes, methods, expressions, and even other annotations. Unlike interfaces, annotations also allow us to pass a set of values where they are applied.

Annotations establish relationships that make it easier to manage data about our application.

Imagine the following hypothetical use of annotations:

```
public class Lion
{
    @ZooSchedule(hours={"9am", "5pm", "10pm"})
    public void feed()
    {
        ...
    }
    
    @ZooSchedule(hours={"4am", "5pm"})
    public void cleanPen()
    {
        ...
    }
}
```

These methods are defined in completely different classes, but the interpretation of the annotation is the same.

An annotation ascribes custom information on the declaration where it is defined. This turns out to be a powerful tool, as the same annotation can often be applied to completely unrelated classes or variables.

Annotations are optional metadata and by themselves do not do anything.


The `@SafeVarargs` annotation:

See [link](https://stackoverflow.com/questions/14231037/java-safevarargs-annotation-does-a-standard-or-best-practice-exist)

## Creating Custom Annotations

When creating an annotation, you give it a name, define a list of optional and required elements, and specify its usage.

Creating an empty annotation:

```
public @interface Test {}
```

Like classes and interfaces, they are commonly defined in their own file as a top-level type, although they can be defined inside a class declaration like an inner class.

Note that we use an annotation to create an annotation.

When using a marker annotation or not specifically setting any values, parentheses are optional.

If an annotation is declared on a line by itself, then it applies to the next non-annotation type found on the proceeding lines.

Some annotations can be applied more than once (repeatable annotations).

## Specifying a Required Element

An annotation element is an attribute that stores values about the particular usage of an annotation.

```
public @interface Example
{
    int hoursPerDay();
}
```

The syntax for the `hoursPerDay` element may seem a little strange at first. It looks like an abstract method, although we're calling it an element (or attribute). Behind the scenes, the JVM is creating elements as interface methods and annotations as implementations of these interfaces.

When declaring an annotation, any element without a default value is considered required.

## Providing an Optional Element

```
public @interface Example
{
    int hoursPerDay();
    int startHour() default 6;
}
```

For an element to be optional, rather than required, it must include a default value.

Similar to case statement values, the default value of an annotation must be a non-null constant expression.

```
public @interface BadAnnotation
{
    String name() default new String("");  // DOES NOT COMPILE
    String address() default "";
    String title() default null;           // DOES NOT COMPILE
}
```

## Selecting an Element Type

Similar to a default element value, an annotation element cannot be declared with just any type. It must be a primitive type, a String, a Class, an Enum, another annotation, or an array of any of these types.


## Applying Element Modifiers

Like abstract interface methods, annotation elements are implicitly abstract and public, whether you declare them that way or not.

```
public @interface Material {}

public @interface Fluffy
{
    int cuteness();
    public abstract int softness() default 11;
    protected Material material();  // DOES NOT COMPILE
    private String friendly();      // DOES NOT COMPILE
    final boolean isBunny();        // DOES NOT COMPILE
}
```

## Adding a Constant Variable

Annotations can include constant variables that can be accessed by other classes without actually creating the annotation.

```
public @interface ElectricitySource
{
    public int voltage();
    int MIN_VOLTAGE = 2;
    public static final int MAX_VOLTAGE = 18;
}
```

Just like interface variables, annotation variables are implicitly public, static, and final. These constant variables are not considered elements though. For example, marker annotations can contain constants.

## Applying Annotations

Annotations can be applied to any Java declaration including the following:
- Classes, interfaces, Enums, and modules
- Variables (static, instance, local)
- Methods and constructors
- Method, constructor, and lambda parameters
- Cast expressions
- Other annotations

## Mixing Required and Optional Elements

One of the most important rules when applying annotations is the following: to use an annotation, all required values must be provided. While an annotation may have many elements, values are required only for ones without default values.

## Creating a value() Element

An annotation must adhere to the following rules to be used without a name:
- The annotation declaration must contain an element named value(), which may be optional or required
- The annotation declaration must not contain any other elements that are required
- The annotation usage must not provide values for any other elements

An example of an annotation that meets these requirements:

```
public @interface Injured
{
    String veterinarian() default "unassigned";
    String value() default "foot";
    int age() default 1;
}
```

The following uses of the annotation are valid:
```
public abstract class Elephant
{
    @Injured("Legs") public void fallDown() {}
    @Injured(value="Legs2) public abstract int trip();
    @Injured String injuries[];
}
```

## Passing an Array of Values


Annotations support a shorthand notation for providing an array that contains a single element. Let's say we have an annotation `Music` defined as follows:

```
public @interface Music
{
    String[] genres();
}
```

If we want to provide only one value to the array, we have a choice of two ways to write the annotation:

```
public class Giraffe
{
    @Music(genres={"Classical"}) String mostLiked;
    @Music(genres="Jazz") String secondFavorite;
}
```

Each of the following uses of the following annotation are also valid:

```
public @interface Test
{
    String[] value();
}
```

```
public class TestClass
{
    @Test(value={"A"}) String a;
    @Test(value="B") String b;
    @Test({"C"}) String c;
    @Test("D") String d;
}
```

## Declaring Annotation-Specific Annotations

Many annotation declarations include the `@Target` annotation, which limits the types the annotation can be applied to. More specifically, the `@Target` annotation takes an array of `ElementType` enum values as its `value()` element.

The following are the values available for the `@Target` annotation:
- `TYPE` (Classes, interfaces, enums, annotations)
- `FIELD` (instance and static variables, enum values)
- `METHOD` (method declarations)
- `PARAMETER` (Constructor, method, and lambda parameters)
- `CONSTRUCTOR` (constructor declarations)
- `LOCAL_VARIABLE` (local variables)
- `ANNOTATION_TYPE` (annotations)
- `PACKAGE` (packages declared in `package-info.java`)
- `TYPE_PARAMETER` (parameterized types, generic declarations)
- `TYPE_USE` (able to be applied anywhere there is a Java type declared or used)
- `MODULE` (modules)

You cannot add a package annotation to just any package declaration, only those defined in a special file, which must be named `package-info.java`. This file stores documentation metadata about a package.

Note that annotations annotated with the `Target` annotation with `TYPE_USE` value cannot be applied to methods that return `void`.

## Storing Annotations with @Retention

Annotations may be discarded by the compiler or at runtime.

We can specify the policy using the `@Retention` annotation. This annotation takes a `value()` of the enum `RetentionPolicy` with the possible values being:
- `SOURCE` (Used only in the source file, discarded by the compiler)
- `CLASS` (Stored in the .class file but not available at runtime)
- `RUNTIME` (stored in the .class file and available at runtime)

## Generating Javadoc with @Documented

Javadoc is a built-in standard within Java that generates documentation for a class or API.

If the `@Documented` annotation is present, the generated Javadoc will include annotation information defined on Java types. Because it is a marker annotation, it does not take any values.

Javadoc has its own annotations that are used solely in generating data within a Javadoc file.

```
public class ZooLightShow {
    /**
     * Performs a light show at the zoo.
     *
     * @param                distance length the light needs to travel.
     * @return               the result of the light show operation.
     * @author               Grace Hopper
     * @since                1.5
     * @deprecated           Use EnhancedZooLightShow.lights() instead.
    */
    @Deprecated(since="1.5") 
    public static String perform(int distance)
    {
        return "Beginning light show!";
    }
}
```

Be careful not to confuse Javadoc annotations with the Java annotations.

## Inheriting Annotations with @Inherited

Another marker annotation you should know for the exam is `@Inherited`. When this annotation is applied to a class, sub-classes will inherit the annotation information found in the parent class.

## Supporting Duplicates with @Repeatable

The `@Repeatable` annotation is used when you want to specify an annotation more than once on a type.

You generally use repeatable annotations when you want to apply the same annotation with different values.

To define a repeatable annotation, you must define a containing annotation type value. A containing annotation type is a separate annotation that defines a `value()` array element. The type of this array is the particular annotation you want to repeat. By convention, the name of the annotation is often the plural form of the repeatable annotation.

```
public @interface Risks
{
    Risk[] value();
}
```

```
import java.lang.annotation.Repeatable;

@Repeatable(Risks.class)
public @interface Risk
{
    String danger();
    int level() default 1;
}
```

Example of use:

```
public class Zoo
{
    public static class Monkey {}

    @Risk(danger="Silly")
    @Risk(danger="Aggressive", level=5)
    @Risk(danger="Violent", level=10)
    private Monkey monkey;
}
```

## Why doesn't @Target Default Behavior Apply to All Types

To use an annotation in a type use or type parameter location, such as a lambda expression or generic declaration, you must explicitly set the `@Target` to include these values. If an annotation is declared without the `@Target` annotation that includes these values, then these locations are prohibited.


One possible explanation for this behavior is backward compatibility. When the authors of Java added `MODULE` value in Java 9, they did not make this same decision.

## Using Common Annotations

Such annotations include `@Override`, `@FunctionalInterface`, `@Deprecated` (takes parameters `since` and `forRemoval`), `@SuppressWarnings` (takes values such as `deprecation` and `unchecked`), `@SafeVarargs`

`@SafeVarargs` can only be applied to constructors or methods that cannot be overriden (private, static, or final).


## Annotation fields

Annotations can contain public static fields, such as members, classes, interfaces, and other annotation definitions. They cannot contain static methods.