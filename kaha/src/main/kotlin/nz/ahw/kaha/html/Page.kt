/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import nz.ahw.kaha.Response
import nz.ahw.kaha.signal.Signal
import javax.servlet.http.HttpServletResponse

class Page(val layout: Layout, val block: Block): Response {

    override fun apply(httpServletResponse: HttpServletResponse) {

        httpServletResponse.writer.use { writer ->
            val layoutContext = LayoutContext(writer, block)
            try {
                layout.render(layoutContext)
            } catch (signal: Signal) {
                // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
                throw signal
            } catch(ex: BlockRenderException) {
                throw ex
            } catch(ex: Throwable) {
                print("Page catch ex: $ex")
                throw LayoutRenderException(layout.layoutName, ex)
            }
        }
    }


}