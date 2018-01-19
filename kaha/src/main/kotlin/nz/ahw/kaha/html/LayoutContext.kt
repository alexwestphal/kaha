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

@KahaDSL
class LayoutContext(val parentContext: HandlerContext) {

    fun Render(render: LayoutRender): LayoutRender = render


}
