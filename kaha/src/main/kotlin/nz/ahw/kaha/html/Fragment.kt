/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.stream.appendHTML
import nz.ahw.kaha.Response
import nz.ahw.kaha.Signal
import javax.servlet.http.HttpServletResponse

class Fragment(val render: Render): Response {
    override fun apply(httpServletResponse: HttpServletResponse) {

        httpServletResponse.writer.use { writer ->
            try {
                val blockContext = RenderContext(writer.appendHTML())
                render(blockContext)
            } catch (signal: Signal) {
                // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
                throw signal
            } catch(ex: Throwable) {
                throw BlockRenderException(ex)
            }
        }
    }

}

