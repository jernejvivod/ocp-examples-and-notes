# The AnnotatedElement Interface

The interface represents an annotated construct of the program currently running in this VM.

This interface allows annotations to be read reflectively. All annotations returned by methods in this interface are immutable and serializable.

The `getAnnotationsByType(class)` and `getDeclaredAnnotationsByType(Class)` methods support multiple annotations of the same type on an element.
If the argument to either method is repeatable annotation type, then the method will "look through" a container annotation and if present, will return any annotations inside
the container. Container annotations may be generated at compile-time to wrap multiple annotations of the argument type.

An annotation `A` is directly present on an element `E` if `E` has a `RuntimeVisibleAnnotations` or `RuntimeVisibleParameterAnnotations` or `RuntimeVisibleTypeAnnotations` attribute,
and the attribute contains A.

An annotation `A` is indirectly present on an element `E` if `E` has a `RuntimeVisibleAnnotations` or `RuntimeVisibleParameterAnnotations` or `RuntimeVisibleTypeAnnotations` attribute, and `A`'s type is
repeatable, and the attribute contains exactly one annotation whose value element contains `A` and whose type is the containing annotation type of `A`'s type.

An annotation `A` is present on an element `E` if either:

- `A` is directly present on `E`.
- No annotation of `A`'s type is directly present on `E`, and `E` is a class, and `A`'s type is inheritable, and `A` is present on the superclass of `E`.

An annotation `A` is associated with an element `E` if either:

- `A` is directly or indirectly present on `E`.
- No annotation of `A`'s type is directly or indirectly present on `E`, and `E` is a class, and `A`'s type is inheritable, and `A` is associated with the superclass of `E`.

| Return type    | Signature                                | Directly Present | Indirectly Present | Present | Associated |
|----------------|------------------------------------------|------------------|--------------------|---------|------------|
| `T`            | `getAnnotation(Class<T>)`                |                  |                    | X       |            |
| `Annotation[]` | `getAnnotations()`                       |                  |                    | X       |            |
| `T[]`          | `getAnnotationsByType(Class<T>)`         |                  |                    |         | X          |
| `T`            | `getDeclaredAnnotation(Class<T>)`        | X                |                    |         |            |
| `Annotation[]` | `getDeclaredAnnotations()`               | X                |                    |         |            |
| `T[]`          | `getDeclaredAnnotationsByType(Class<T>)` | X                | X                  |         |            |
