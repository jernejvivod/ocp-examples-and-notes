# Optional

An empty `Optional` instance can be created as: `Optional.empty()`

A non-empty `Optional` instance can be created as: `Optional.of(1)`

This method throws a `java.lang.NullPointerException` if the argument is `null`.

A potentially empty `Optional` can be created as: `Optional.ofNullable(x)`

This returns an empty `Optional` instance if `x` is null and a non-empty `Optional` if not.

## Testing the Optional and getting the value

We check if the `Optional` is empty with: `opt.isEmpty()`

We retrieve the value using: `opt.get()`

This method throws a `java.util.NoSuchElementException` if the `Optional` instance is empty.

The following table lists the important methods of an `Optional` instance.

| Method                    | When `Optional` is empty                       | When `Optional` contains a value |
|---------------------------|------------------------------------------------|----------------------------------|
| `get()`                   | Throws an exception                            | Returns value                    |
| `ifPresent(Consumer c)`   | Does nothing                                   | Calls `Consumer` with value      |
| `isPresent()`             | Returns false                                  | Returns true                     |
| `orElse(T other)`         | Returns other parameter                        | Returns true                     |
| `orElseGet(Supplier s)`   | Returns result of calling Supplier             | Returns value                    |
| `orElseThrow()`           | Throws `java.util.NoSuchElementException`      | Returns value                    |
| `orElseThrow(Supplier s)` | Throws exception created by calling `Supplier` | Returns value                    |

## Chaining Optionals

Suppose you are given an `Optional<Integer>` instance and asked to print the value, but only if it is a three-digit number.

We can write something like:
```
optional.map(n -> n.toString()).filter(s -> s.length() == 3).ifPresent(System.out::println);
```

What if we want to perform the map operation using a function that returns an optional and store the value in an `Optional` instance?

`Optional<Integer> result = optional.flatMap(ChainingOptionals::calculator);`