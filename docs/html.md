## Generating HTML

Kaha provides access to the [kotlinx.html](https://github.com/Kotlin/kotlinx.html) Domain Specific Language (DSL) for generating HTML. This means generating HTML becomes reasonably typesafe (i.e. if it compiles it's probably valid HTML).

## Simple Example

```kotlin
Fragment {
  div("message") {
    +"Hello World"
  }
}
```
In this example we an HTML fragment consisting of a single div. `message` is a css class and `+` escapes a string and adds it to the element.

## Loops and Conditionals

The DSL is pure Kotlin so we can use the standard `for`, `if`, `when`, etc.

```kotlin
Fragment {
  // Conditional Inclusion
  if(userIsAdmin()) {
    div("adminBar") {
      ...
    }
  }
  
  // Looping
  ul("userList") {
    for(user in users) {
      li { +"user.name" }
    }
}
```

## Reusable Blocks

TODO

## Custom Tags

TODO
