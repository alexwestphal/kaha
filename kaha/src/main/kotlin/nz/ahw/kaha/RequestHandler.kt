/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

import kotlin.reflect.jvm.reflect

open class RequestHandler(private val body: HandlerContext.() -> Response) {

    fun handle(req: HttpServletRequest, resp: HttpServletResponse) {
        val response = try {
            body(HandlerContext(req))
        } catch(signal: Signal) {
            signal.response
        } catch (ex: Throwable) {
            resp.sendError(500)
            throw ex
        }
        try {
            response.apply(resp)
        } catch(signal: Signal) {
            throw InvalidSignalLocationException(signal)
        }
    }
}

class EHandler1<P1: Any>(servlet: KahaServlet, val p1Name: String, val p1Class: KClass<P1>, private val body: HandlerContext.(P1) -> Response): RequestHandler({
    val p1 = parameters.require(p1Name, p1Class, "Missing or invalid parameter '$p1Name'")
    body(p1)
}) {
    fun invoke(p1: P1): String = "$p1Name=$p1"
}

fun KahaServlet.Handler(body: HandlerContext.() -> Response): RequestHandler = RequestHandler(body)

inline fun <reified P1: Any> KahaServlet.Handler(noinline body: HandlerContext.(P1) -> Response): EHandler1<P1> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")

    return EHandler1(this, names[1]!!, P1::class, body)
}