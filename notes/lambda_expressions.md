# Lambda Expressions

Lambda expressions can be used to implement interfaces. Java does not allow using lambda expressions to implement abstract classes.

## Parameters

- If a lambda expression takes no parameters, the parameter part of the expression must have an empty set of brackets, i.e., ().

- If a lambda expression takes exactly one parameter, the parameter may be specified within brackets or without brackets. If you want to include the type of the parameter you must use brackets.

- If a lambda expression takes more than one parameter, all the parameter names must be specified within the brackets. You can use the var type for declaring the parameters. If you use var for an argument you must also use it for all other arguments. Also, if you specify a type for an argument you must specify it for all arguments.

It is not possible to apply annotations on the parameters unless you specify the type.

## The Body of a Lambda Expression

The body can either be an expression or a block of code contained within curly braces. Given that the body may or may not return a value, there are four possibilities:

- Expression with or without a return value
- Block of code with or without a return value

## Using Functional Interfaces with Collections API

Notable methods include:

- `forEach(Consumer<E> consumer)`

- `removeIf(Predicate<? super E> filter)`

- `sort(Comparator<? super E> comparator)`

## Scope of Variables in a Lambda Expression

The variables that you define in the variable section of a lambda expression exist in the same scope as which the lambda expression itself exists. This means you cannot redefine the variables that already exist in that scope.

It is possible to access a variable that is in scope of the lambda expression from within the lambda expression's body but only if that variable is declared final or is "effectively final". Effectively final means that even though the variable is not explicitly declared as final, its value is not changed throughout the scope in which it exists, so the compiler assumes it is final.