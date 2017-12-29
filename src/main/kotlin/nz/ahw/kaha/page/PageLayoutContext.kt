/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.page

import kotlinx.html.HTML
import kotlinx.html.HtmlBlockTag
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import nz.ahw.kaha.RequestContext
import javax.servlet.http.HttpServletRequest

class PageLayoutContext(override val request: HttpServletRequest, val appendable: Appendable, val pageContextConsumer: (PageContext) -> Unit): RequestContext() {

    fun html(body: HTML.() -> Unit) {
        appendable.append("<!DOCTYPE html>")
        appendable.appendHTML().html(body)
    }

    fun HtmlBlockTag.pageContent() {
        pageContextConsumer(PageContext(request, this))
    }
}
