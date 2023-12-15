jOOQ GOD (Java)Generator
====
[jOOQ](https://github.com/jOOQ/jOOQ) is the best way to write SQL in Java. But in issue [#10212](https://github.com/jOOQ/jOOQ/issues/10212) did a significant change how @NotNull and @Nullable annotations are handled in JavaGenerator.

Until this change, we took advantage of that platform types in Java when accessed from Kotlin are not nullable, but you don't have to work with them as non-nullable types (and put ? an !! operators everywhere).
On the other hand, if there is a real NULLABLE column in a database, JavaGenerator generated @Nullable annotation for that field.
And this is the best way how to work with generated POJOs in Kotlin.

That's the underlying motivation of this project. To bring back the old behavior of JavaGenerator.

## Usage


### Gradle Kotlin DSL

```kotlin