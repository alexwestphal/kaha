/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.layout

import nz.ahw.kaha.Block
import nz.ahw.kaha.BlockRenderException
import nz.ahw.kaha.Response
import javax.servlet.http.HttpServletResponse

abstract class Layout {

    open val layoutName: String = this::class.java.name

    abstract val render: LayoutRender

    fun renderBlock(block: Block): Response = object: Response {

        override fun apply(httpServletResponse: HttpServletResponse) {
            httpServletResponse.writer.use { writer ->
                val layoutContext = LayoutContext(writer, block)
                try {
                    render(layoutContext)
                } catch(ex: BlockRenderException) {
                    throw ex
                } catch(ex: Throwable) {
                  throw LayoutRenderException(layoutName, ex)
                }
            }
        }
    }
}