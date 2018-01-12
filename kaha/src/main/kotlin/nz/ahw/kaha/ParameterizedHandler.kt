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


class ParameterizedHandler1<P1: Any>(val p1: Parameter<P1>,
                                     private val body: HandlerContext.(P1) -> Response): KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    body(p1Value)
})

class ParameterizedHandler2<P1: Any, P2: Any>(val p1: Parameter<P1>, val p2: Parameter<P2>,
                                              private val body: HandlerContext.(P1, P2) -> Response): KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    val p2Value = parameters.require(p2.name, p2.type, "Missing or invalid parameter '${p2.name}'")
    body(p1Value, p2Value)
})

class ParameterizedHandler3<P1: Any, P2: Any, P3: Any>(val p1: Parameter<P1>, val p2: Parameter<P2>, val p3: Parameter<P3>,
                                                       private val body: HandlerContext.(P1, P2, P3) -> Response):KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    val p2Value = parameters.require(p2.name, p2.type, "Missing or invalid parameter '${p2.name}'")
    val p3Value = parameters.require(p3.name, p3.type, "Missing or invalid parameter '${p3.name}'")
    body(p1Value, p2Value, p3Value)
})

class ParameterizedHandler4<P1: Any, P2: Any, P3: Any, P4: Any>(val p1: Parameter<P1>, val p2: Parameter<P2>,
                                                                val p3: Parameter<P3>, val p4: Parameter<P4>,
                                                                private val body: HandlerContext.(P1, P2, P3, P4) -> Response):KahaHandler({
    val p1Value = parameters.require(p1.name, p1.type, "Missing or invalid parameter '${p1.name}'")
    val p2Value = parameters.require(p2.name, p2.type, "Missing or invalid parameter '${p2.name}'")
    val p3Value = parameters.require(p3.name, p3.type, "Missing or invalid parameter '${p3.name}'")
    val p4Value = parameters.require(p4.name, p4.type, "Missing or invalid parameter '${p4.name}'")
    body(p1Value, p2Value, p3Value, p4Value)
})


// Builders for ParameterizedHandler1

inline fun <reified P1: Any> KahaServlet.Handler(
        noinline body: HandlerContext.(P1) -> Response
): ParameterizedHandler1<P1> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")
    return ParameterizedHandler1(Parameter(names[0]!!, P1::class), body)
}

inline fun <reified P1: Any> KahaServlet.Handler(
        p1Name: String,
        noinline body: HandlerContext.(P1) -> Response
): ParameterizedHandler1<P1> {
    return ParameterizedHandler1(Parameter(p1Name, P1::class), body)
}


// Builders for ParameterizedHandler2

inline fun <reified P1: Any, reified P2: Any> KahaServlet.Handler(
        noinline body: HandlerContext.(P1, P2) -> Response
): ParameterizedHandler2<P1, P2> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")
    return ParameterizedHandler2(Parameter(names[0]!!, P1::class), Parameter(names[1]!!, P2::class), body)
}

inline fun <reified P1: Any, reified P2: Any> KahaServlet.Handler(
        p1Name: String, p2Name: String,
        noinline body: HandlerContext.(P1, P2) -> Response
): ParameterizedHandler2<P1, P2> {
    return ParameterizedHandler2(Parameter(p1Name, P1::class), Parameter(p2Name, P2::class), body)
}


// Builders for ParameterizedHandler3

inline fun <reified P1: Any, reified P2: Any, reified P3: Any> KahaServlet.Handler(
        noinline body: HandlerContext.(P1, P2, P3) -> Response
): ParameterizedHandler3<P1, P2, P3> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")
    return ParameterizedHandler3(Parameter(names[0]!!, P1::class), Parameter(names[1]!!, P2::class), Parameter(names[2]!!, P3::class), body)
}

inline fun <reified P1: Any, reified P2: Any, reified P3: Any> KahaServlet.Handler(
        p1Name: String, p2Name: String, p3Name: String,
        noinline body: HandlerContext.(P1, P2, P3) -> Response
): ParameterizedHandler3<P1, P2, P3> {
    return ParameterizedHandler3(Parameter(p1Name, P1::class), Parameter(p2Name, P2::class), Parameter(p3Name, P3::class), body)
}


// Builders for ParameterizedHandler4

inline fun <reified P1: Any, reified P2: Any, reified P3: Any, reified P4: Any> KahaServlet.Handler(
        noinline body: HandlerContext.(P1, P2, P3, P4) -> Response
): ParameterizedHandler4<P1, P2, P3, P4> {
    val names = body.reflect()?.parameters?.map { it.name } ?: throw IllegalStateException("Can't extract parameter names for handler")
    return ParameterizedHandler4(
            Parameter(names[0]!!, P1::class),
            Parameter(names[1]!!, P2::class),
            Parameter(names[2]!!, P3::class),
            Parameter(names[3]!!, P4::class),
            body)
}

inline fun <reified P1: Any, reified P2: Any, reified P3: Any, reified P4: Any> KahaServlet.Handler(
        p1Name: String, p2Name: String, p3Name: String, p4Name: String,
        noinline body: HandlerContext.(P1, P2, P3, P4) -> Response
): ParameterizedHandler4<P1, P2, P3, P4> {
    return ParameterizedHandler4(
            Parameter(p1Name, P1::class),
            Parameter(p2Name, P2::class),
            Parameter(p3Name, P3::class),
            Parameter(p4Name, P4::class),
            body)
}