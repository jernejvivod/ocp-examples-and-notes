# Interfaces

All variables defined in an interface are implicitly public, static, and final.

An interface can contain 4 kinds of methods:
- abstract methods
- default methods
- static methods
- private methods (static and non-static)

It is possible to define classes, interfaces, and enums within an interface. They cannot be anything other than public.

Unlike the static methods of a class, the static methods of an interface cannot be inherited (would be problematic as classes can implement multiple interfaces).

The fields of an interface can be referred to using a subclass. The fields must not be used ambiguously (must use e.g. `Activity.SIZE` or `Task.SIZE`).

If `A` implements `Activity` and `Task`, a call like `a.SIZE` will cause an error.

If one interface provides a default method and another interface provides an abstract method with the same signature, the code will not compile. The method must be overridden in the implementing class.

Java allows a class to inherit multiple fields with the same name as long as you don't try to use those fields ambiguously. this means that you will get a compilation error only if you try to use SIZE within 
Process directly without specifying which SIZE field you are trying to refer to.

Annotations from interfaces are not inherited (would cause problems due to multiple inheritance).
