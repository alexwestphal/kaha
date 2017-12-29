package nz.ahw.kaha.demo.pages

import kotlinx.html.*
import nz.ahw.kaha.page.Layout
import nz.ahw.kaha.page.LayoutRender

class SimpleLayout(val pageTitle: String): Layout() {
    fun HtmlBlockTag.pageHeader() {
        div("header") {
            h1 {
                +pageTitle
            }
        }
    }

    fun HtmlBlockTag.sideBar() {
        div("sideBar") {

        }
    }

    fun HtmlBlockTag.pageFooter() {
        div("footer") {

        }
    }

    override val render: LayoutRender = {
        html {
            head {
                title(pageTitle)
                script(type = ScriptType.textJavaScript, src = "https://code.jquery.com/jquery-1.12.4.min.js") {}
            }
            body {
                pageHeader()
                sideBar()
                div("main") {
                    pageContent()
                }
                pageFooter()
            }
        }
    }


}


