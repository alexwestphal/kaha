# Overview

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
override fun get() = Handler { userId: Int ->

}
```

- Sending Error Codes

```kotlin
override fun get() = Handler {
    Response(StatusCodes.ImATeapot)
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

## Motivation

TODO

## Etymology

In New Zealand Maori, 'kaha' is a adverb that translates as "be strong". It's commonly used as "Kia Kaha" ("Stay Strong").


## License

Kaha is available to the public under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
