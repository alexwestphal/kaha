Koha - A Kotlin Web Framework
=============================

Koha is a simple Servlet based web framework for Kotlin.

## Hello World

```kotlin
import kotlinx.html.*
import nz.ahw.kaha.KahaServlet
import nz.ahw.kaha.page.EmptyLayout
import nz.ahw.kaha.page.Page
import javax.servlet.annotation.WebServlet

@WebServlet(urlPatterns = ["/hello"])
class HelloWorld: KahaServlet() {

    override fun get() = Page(EmptyLayout("Hello World")) {
        div("message") {
            +"Hello World"
        }
    }
}
```
