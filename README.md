Koha - A Kotlin Web Framework
=============================

Koha is a simple Servlet based web framework for Kotlin.

## Hello World

```kotlin

@WebServlet("/hello")
class HelloWorld: KahaServlet() {

    override fun get() = Page(title = "Hello World") {
        div("message") {
            +"Hello World"
        }
    }
}
```

Responds to a GET request with:

```html
<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>    
</head>
<body>
    <div class="message">Hello World</div>
</body> 
</html>
```

## Parameter Access

Request parameters are available through typed accessors

```kotlin
    override fun get() = Page(title = "Hello World") {
        // Note the specified type
        val userId: Int? = parameters["user_id"]
        
        // Or alternatively
        parameter.getInt("user_id")
    
        div("message") {
            +"Hello World $userId"
        }
    }
```

### Shared Layout

```kotlin
object SimpleLayout: Layout() {

    override val render: LayoutRender = {
        html {
            head {
                title(pageTitle)
                script(type = ScriptType.textJavaScript, src = "https://code.jquery.com/jquery-1.12.4.min.js") {}
            }
            body {
                div("header") {  }
                div("sidebar") {  }
                div("main") {
                    // Inject the page into the layout
                    pageContent()
                }
                div("footer") {  }
            }
        }
    }
}

// Using the layout
override fun get() = Page(SimpleLayout, title = "Some Page") { 

}
```

Responds to a GET request with:

```html
<!DOCTYPE html>
<html>
<head>
    <title>Some Page</title>
    <script type="text" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
    <div class="header"></div>
    <div class="sidebar"></div>
    <div class="main">
        <div class="message">Hello World</div>
    </div>
    <div class="footer"></div>
</body> 
</html>
```
