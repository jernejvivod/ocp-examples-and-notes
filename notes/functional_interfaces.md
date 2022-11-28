# Functional Interfaces

## Working with Built-in Functional Interfaces

A functional interface has exactly one abstract method.

The following table lists some common functional interfaces:

Functional interface  | Return type | Method name    | # of parameters
----------------------|-------------|----------------|----------------
`Supplier<T>`         | `T`         | `get()`        | 0
`Consumer<T>`         | `void`      | `accept(T)`    | 1 (`T)
`BiConsumer<T, U>`    | `void`      | `accept(T, U)` | 2 (`T`, `U`)
`Predicate<T>`        | `boolean`   | `test(T)`      | 1 (`T`)
`BiPredicate<T, U>`   | `boolean`   | `test(T, U)`   | 2 (`T`, `U`)
`Function<T, R>`      | `R`         | `apply(T)`     | 1 (`T`)
`BiFunction<T, U, R>` | `R`         | `apply(T, U)`  | 2 (`T`, `U`)
`UnaryOperator<T>`    | `T`         | `apply(T)`     | 1 (`T`)
`BinaryOperator<T>`   | `T`         | `apply(T, T)`  | 2 (`T`, `T`)

The `@FunctionalInterface` annotation can be applied to an interface to make sure the interface is a functional interface.

Abstract methods implemented by the inherited methods from `Object` do not increase the abstract method count.

## Convenience Methods on Functional Interfaces

By definition, all functional interfaces have a single abstract method. This doesn't mean they can have only one method, though. Several of the common functional interfaces provide a number of helpful default methods.

The following table shows some common convenience methods on the built-in functional interfaces.

Interface Instance | Method Return Type | Method name | Method Parameters
-------------------|--------------------|-------------|------------------
`Consumer`         | `Consumer`         | `andThen()` | `Consumer`
`Function`         | `Function`         | `andThen()` | `Function`
`Function`         | `Function`         | `compose()` | `Function`
`Predicate`        | `Predicate`        | `and()`     | `Predicate`
`Predicate`        | `Predicate`        | `negate()`  |
`Predicate`        | `Predicate`        | `or()`      | `Predicate`
