/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import kotlin.reflect.KClass
import kotlin.reflect.jvm.reflect

data class Parameter<T: Any>(val name: String, val type: KClass<T>)


class ParameterizedHandler1<P1: Any>(val p1: Parameter<P1>, private val body: HandlerContext.(P1) -> Response): KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    body(p1Value)
})


class ParameterizedHandler2<P1: Any, P2: Any>(val p1: Parameter<P1>, val p2: Parameter<P2>, private val body: HandlerContext.(P1, P2) -> Response): KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    val p2Value = parameters.require(p2.name, p2.type, "Missing or invalid parameter '${p2.name}'")
    body(p1Value, p2Value)
})


inline fun <reified P1: Any> KahaServlet.Handler(noinline body: HandlerContext.(P1) -> Response): ParameterizedHandler1<P1> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")

    return ParameterizedHandler1(Parameter(names[0]!!, P1::class), body)
}

inline fun <reified P1: Any> KahaServlet.Handler(p1Name: String,
                                                 noinline body: HandlerContext.(P1) -> Response): ParameterizedHandler1<P1> {
    return ParameterizedHandler1(Parameter(p1Name, P1::class), body)
}

inline fun <reified P1: Any, reified P2: Any> KahaServlet.Handler(noinline body: HandlerContext.(P1, P2) -> Response): ParameterizedHandler2<P1, P2> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")

    return ParameterizedHandler2(Parameter(names[0]!!, P1::class), Parameter(names[1]!!, P2::class), body)
}

inline fun <reified P1: Any, reified P2: Any> KahaServlet.Handler(p1Name: String, p2Name: String,
                                                                  noinline body: HandlerContext.(P1, P2) -> Response): ParameterizedHandler2<P1, P2> {
    return ParameterizedHandler2(Parameter(p1Name, P1::class), Parameter(p2Name, P2::class), body)
}