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


## Key Principles

### Compatible

Kaha can be added to any existing Servlet based Web Application. Your Servlet just inherits from `KahaServlet` rather than `HttpServlet`. You can even mix between Kaha and direct servlet usage:

```kotlin
@WebServlet("/my-servlet")
class MyServlet: KahaServlet() {
    override fun post() = Handler {
        // Response to POST request using Kaha
    }
    
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        // Response to GET request using other techniques
    }
}
```

### Concise

Kaha provides concise typed ways of doing many of things.

- Accessing Request parameters:

```kotlin
override fun get() = Handler {
    val userId: Int = parameters["userId"]
    ...
}
```

- Sending Error Codes

```kotlin
override fun get() = Handler {
    Responses.ImATeaPot
}
```

### DRY (Don't Repeat Yourself)

Kaha provides `Layout` as a way to centralise the container style HTML that is commonly shared across pages.

```kotlin
// Defining a layout
class SimpleLayout(pageTitle: String): Layout() {
    override val render: LayoutRender = {
        html {
            head {
                title(pageTitle)
            }
            body {
                div("header") {
                    h1 { +pageTitle }
                }
                div("sidebar") {}
                div("main") {
                    // Inject the page block
                    blockContent()
                }
                div("footer") {}
                script( type = ScriptType.textJavaScript, 
                        src = "https://code.jquery.com/jquery-1.12.4.min.js") {}
            }
        }
    }
}

// Using a layout
@WebServlet("/hello")
class HelloWorld: KahaServlet() {
    override fun get() = Handler {
        val message = "Hello World"
        Page(SimpleLayout(pageTitle = message)) {
            div("message") {
                +message
            }
        }
    }
}
```

### Typesafe

Kaha utilises the [kotlinx.html](https://github.com/Kotlin/kotlinx.html) DSL for building. This helps catch alot of typos and mistakes at compile time. For example:

```kotlin
Fragment {
    div { span { +"Hi" } } // Produces <div><span>Hi</span></div>
    
    div { sapn { +"Hi" } } // Doesn't Compile 
}
```

### Reusable

Writing reusable blocks is easy:

```kotlin
fun HtmlBlockTag.sayHello(name: String) {
    span("greeting") { +"Hello $name!" }  
}

// Using it
Fragment {
    div {
        sayHello("Alice")
    }
}
```

We can also create resuable parameter extractors:

```kotlin
// Define that userId (if requested) should be an Int otherwise a 400 - Bad Request error is sent
val HandlerContext.Parameters.userId: Int get() = this["user_id"] ?: throw Signals.BadRequest("Invalid or missing user_id")


override fun get() = Handler {
    lookupUserData(parameters.userId)
    ...
}
```

### Escapable

Kaha makes it easy to escape from the DSL and produce raw output.

```kotlin
Fragment {
    div { 
        rawHtml("""
            <h1>Page Title</h1>
        """) 
    }
    
    rawScript("""
        $(document).ready(function() {
            $(".message").css({color: 'red'});
        })
    """)
}
```


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

## Etymology

In New Zealand Maori, 'kaha' is a adverb that translates as "be strong". It's commonly used as "Kia Kaha" ("Stay Strong").


## License

Kaha is available to the public under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
