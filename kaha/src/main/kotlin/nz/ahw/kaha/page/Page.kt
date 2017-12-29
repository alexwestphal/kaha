/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.page

import nz.ahw.kaha.Handler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Page(val layout: Layout = EmptyLayout, val title: String = "", val body: PageContext.() -> Unit): Handler {
    override fun apply(request: HttpServletRequest, response: HttpServletResponse) {
            layout.apply(request, response, title, body)
    }

}