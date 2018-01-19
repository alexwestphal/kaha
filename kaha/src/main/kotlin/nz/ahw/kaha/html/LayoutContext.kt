/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import nz.ahw.kaha.HandlerContext
import nz.ahw.kaha.KahaDSL
import nz.ahw.kaha.RequestContext

@KahaDSL
class LayoutContext(private val parentContext: HandlerContext): RequestContext by parentContext {

    fun Render(render: LayoutRender): LayoutRender = render
}
