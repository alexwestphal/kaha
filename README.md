Kaha - A Kotlin Web Framework
=============================

Kaha is a simple Servlet based web framework for Kotlin.

[ ![Bintray](https://api.bintray.com/packages/ahwnz/kaha/kaha/images/download.svg) ](https://bintray.com/ahwnz/kaha/kaha/_latestVersion)
[ ![Kotlin](https://img.shields.io/badge/Kotlin-1.2-orange.svg) ](https://kotlinlang.org/)
[ ![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-green.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


## Hello World

```kotlin
@WebServlet("/hello")
class HelloWorld: KahaServlet() {
    override fun get() = Handler {
        Fragment {
            div("message") {
                +"Hello World"
            }
        }
    }
}
```
Responds to a GET request with:
```html
<div class="message">Hello World</div>
```

## Concepts 

## Installation

**Step 1:** Configure to use the Kaha repository (hosted on Bintray).

For Maven:
```xml
<repositories>
    <repository>
        <id>bintray-ahwnz-kaha</id>
        <name>bintray-kaha</name>
        <url>https://dl.bintray.com/ahwnz/kaha</url>
    </repository>
</repositories>
```

For Gradle:
```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/ahwnz/kaha" 
    }
}
```

**Step 2:** Add the dependency (where `{version}` is replaced with the desired Kaha version).

For Maven:
```xml
<dependencies>
    <dependency>
      <groupId>nz.ahw.kaha</groupId>
      <artifactId>kaha</artifactId>
      <version>{version}</version>
    </dependency>
</dependencies>
```

For Gradle
```groovy
compile 'nz.ahw.kaha:kaha:{version}'
```

## Design Goals

Kaha is designed to be a simple way of creating servlets in Kotlin. 

Key Design Goals:
- Compatible - Fit into an existing Java and/or Kotlin Servlet based web application.
- DRY - Avoid repetition wherever possible
- Typesafe - Use typesafe builders so that typos are caught at compile time
- Customizable - Components should be as customizable as possible
- Reusable - Components should be as reusable as possible
- Escapable - Escape hatches should be available  

## Documentation

See the [Documentation](https://github.com/ahwnz/kaha/wiki).

## License

Kaha is available to the public under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
