/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Layout(private val body: LayoutContext.() -> Unit) {

    fun apply(request: HttpServletRequest, response: HttpServletResponse, pageContextConsumer: (PageContext) -> Unit) {
        response.writer.use { writer ->

            val layoutContext = LayoutContext(request, writer, pageContextConsumer)
            body.invoke(layoutContext)
        }
    }
}