/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.HEAD
import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import nz.ahw.kaha.KahaDSL
import nz.ahw.kaha.Signal


@KahaDSL
class LayoutRenderContext<out T>(private val headRender: Render, private val bodyRender: Render, consumer: TagConsumer<T>): RenderContext<T>(consumer) {

    fun HEAD.injectHead() {
        try {
            headRender(RenderContext(this.consumer))
        } catch (signal: Signal) {
            // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
            throw signal
        } catch(ex: Throwable) {
            throw BlockRenderException(ex)
        }
    }

    fun HTMLTag.injectBody() {
        try {
            bodyRender(RenderContext(this.consumer))
        } catch (signal: Signal) {
            // Signals aren't allowed to happen here but we're rethrowing it for Handler to deal with
            throw signal
        } catch(ex: Throwable) {
            throw BlockRenderException(ex)
        }
    }
}