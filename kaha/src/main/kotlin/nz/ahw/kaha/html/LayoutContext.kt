/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.HTML
import kotlinx.html.HtmlBlockTag
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import nz.ahw.kaha.KahaDSL
import nz.ahw.kaha.Signal

@KahaDSL
class LayoutContext(val appendable: Appendable, val block: Block) {

    fun html(body: HTML.() -> Unit) {
        appendable.append("<!DOCTYPE html>")
        appendable.appendHTML().html(body)
    }

    fun HtmlBlockTag.blockContent() {
        try {
            block(BlockContext(this.consumer))
        } catch (signal: Signal) {
            // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
            throw signal
        } catch(ex: Throwable) {
            throw BlockRenderException(ex)
        }
    }
}