/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.html

object Layouts {
    object EmptyLayout: Layout("EmptyLayout", {

        Render {
            html {
                head {

                }
                body {

                }
            }
        }
    })
}

