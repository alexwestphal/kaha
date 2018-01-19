/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.stream.appendHTML
import nz.ahw.kaha.HandlerContext
import nz.ahw.kaha.Response
import nz.ahw.kaha.Signal
import javax.servlet.http.HttpServletResponse

class Page(val parentContext: HandlerContext, val layout: Layout, val headRender: Render, val bodyRender: Render): Response {

    override fun apply(httpServletResponse: HttpServletResponse) {

        val layoutContext = LayoutContext(parentContext)
        val layoutRender = layout.body(layoutContext)


        httpServletResponse.writer.use { writer ->
            try {
                val renderContext = LayoutRenderContext(headRender, bodyRender, writer.appendHTML())
                layoutRender(renderContext)

            } catch (signal: Signal) {
                // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
                throw signal
            } catch(ex: BlockRenderException) {
                throw ex
            } catch(ex: Throwable) {
                throw LayoutRenderException(layout.name, ex)
            }
        }
    }
}

fun HandlerContext.Page(layout: Layout, bodyRender: Render): Page = Page(this, layout, {}, bodyRender)

fun HandlerContext.Page(layout: Layout, headRender: Render, bodyRender: Render): Page = Page(this, layout, headRender, bodyRender)