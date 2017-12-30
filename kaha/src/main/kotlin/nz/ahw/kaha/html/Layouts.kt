/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.body

object Layouts {
    object EmptyLayout: Layout() {
        override val render: LayoutRender = {
            html {
                body {
                    blockContent()
                }
            }

        }
    }
}

