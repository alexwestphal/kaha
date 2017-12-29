/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.page

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PageLayout(private val body: PageLayoutContext.() -> Unit) {

    fun apply(request: HttpServletRequest, response: HttpServletResponse, pageContextConsumer: (PageContext) -> Unit) {
        response.writer.use { writer ->

            val layoutContext = PageLayoutContext(request, writer, pageContextConsumer)
            body.invoke(layoutContext)
        }
    }
}