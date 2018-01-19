/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import nz.ahw.kaha.http.StatusCodes
import javax.servlet.http.HttpServletRequest
import kotlin.reflect.KClass

interface RequestContext {

    val request: HttpServletRequest

    val HttpServletRequest.attributes: RequestAttributes get() = RequestAttributes(this)
    val HttpServletRequest.headers: RequestHeaders get() = RequestHeaders(this)
    val HttpServletRequest.parameters: RequestParameters get() = RequestParameters(this)

    fun signal(response: Response): Nothing = throw Signal(response)
}

class RequestAttributes(val request: HttpServletRequest) {
    inline operator fun <reified T: Any> get(name: String): T? = get(name, T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(name: String, cls: KClass<T>): T? = request.getAttribute(name) as? T

    fun getBoolean(name: String): Boolean? = request.getAttribute(name) as? Boolean
    fun getDouble(name: String): Double? = request.getAttribute(name) as? Double
    fun getInt(name: String): Int? = request.getAttribute(name) as? Int
    fun getLong(name: String): Long? = request.getAttribute(name) as? Long
    fun getString(name: String): String? = request.getAttribute(name) as? String

    inline fun <reified T: Any> require(name: String, errorMessage: String): T = require(name, T::class, errorMessage)

    fun <T: Any> require(name: String, cls: KClass<T>, errorMessage: String): T = get(name, cls) ?: throw Signal(Responses.WithStatusCode(StatusCodes.BadRequest, errorMessage))
}

class RequestHeaders(val request: HttpServletRequest) {
    operator fun get(name: String): String = request.getHeader(name)
}

class RequestParameters(val request: HttpServletRequest) {

    inline operator fun <reified T: Any> get(name: String): T? = get(name, T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(name: String, cls: KClass<T>): T? = when(cls) {
        Boolean::class -> getBoolean(name) as T?
        Double::class -> getDouble(name) as T?
        Int::class -> getInt(name) as T?
        Long::class -> getLong(name) as T?
        String::class -> getString(name) as T?
        else -> throw IllegalArgumentException("Can't extract '${cls.java.name}' from a parameter")
    }

    fun getBoolean(name: String): Boolean? = request.getParameter(name)?.toBoolean()
    fun getDouble(name: String): Double? = request.getParameter(name)?.toDoubleOrNull()
    fun getInt(name: String): Int? = request.getParameter(name)?.toIntOrNull()
    fun getLong(name: String): Long? = request.getParameter(name)?.toLongOrNull()
    fun getString(name: String): String? = request.getParameter(name)

    inline fun <reified T: Any> require(name: String, errorMessage: String): T = require(name, T::class, errorMessage)

    fun <T: Any> require(name: String, cls: KClass<T>, errorMessage: String): T = get(name, cls) ?: throw Signal(Responses.WithStatusCode(StatusCodes.BadRequest, errorMessage))
}