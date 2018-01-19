/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import nz.ahw.kaha.http.StatusCodes
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import kotlin.reflect.KClass

interface RequestContext {

    val request: HttpServletRequest
    val servletContext: ServletContext get() = request.servletContext

    val HttpServletRequest.attributes: RequestAttributes get() = RequestAttributes(this)
    val HttpServletRequest.headers: RequestHeaders get() = RequestHeaders(this)
    val HttpServletRequest.parameters: RequestParameters get() = RequestParameters(this)

    val HttpSession.session: SessionAttributes get() = SessionAttributes(this)

    val ServletContext.attributes: ServletContextAttributes get() = ServletContextAttributes(this)

    fun signal(response: Response): Nothing = throw Signal(response)
}

class RequestAttributes(private val request: HttpServletRequest): Iterable<Pair<String, Any>> {
    override fun iterator(): Iterator<Pair<String, Any>> =
        request.attributeNames.asSequence().map { name -> Pair(name, request.getAttribute(name)) }.iterator()

    operator fun <T> set(name: String, value: T) = request.setAttribute(name, value)

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

class RequestHeaders(private val request: HttpServletRequest) {
    operator fun get(name: String): String = request.getHeader(name)
}

class RequestParameters(private val request: HttpServletRequest): Iterable<Pair<String, String>> {
    override fun iterator(): Iterator<Pair<String, String>> =
        request.parameterNames.asSequence().map { name -> Pair(name, request.getParameter(name)) }.iterator()

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

class SessionAttributes(private val session: HttpSession): Iterable<Pair<String, Any>> {
    override fun iterator(): Iterator<Pair<String, Any>> =
        session.attributeNames.asSequence().map { name -> Pair(name, session.getAttribute(name)) }.iterator()

    operator fun <T> set(name: String, value: T) = session.setAttribute(name, value)

    inline operator fun <reified T: Any> get(name: String): T? = get(name, T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(name: String, cls: KClass<T>): T? = session.getAttribute(name) as? T

    fun getBoolean(name: String): Boolean? = session.getAttribute(name) as? Boolean
    fun getDouble(name: String): Double? = session.getAttribute(name) as? Double
    fun getInt(name: String): Int? = session.getAttribute(name) as? Int
    fun getLong(name: String): Long? = session.getAttribute(name) as? Long
    fun getString(name: String): String? = session.getAttribute(name) as? String

    inline fun <reified T: Any> require(name: String, errorMessage: String): T = require(name, T::class, errorMessage)

    fun <T: Any> require(name: String, cls: KClass<T>, errorMessage: String): T = get(name, cls) ?: throw Signal(Responses.WithStatusCode(StatusCodes.BadRequest, errorMessage))
}

class ServletContextAttributes(private val servletContext: ServletContext): Iterable<Pair<String, Any>> {

    override fun iterator(): Iterator<Pair<String, Any>> =
        servletContext.attributeNames.asSequence().map { name -> Pair(name, servletContext.getAttribute(name)) }.iterator()

    operator fun <T> set(name: String, value: T) = servletContext.setAttribute(name, value)

    inline operator fun <reified T: Any> get(name: String): T? = get(name, T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(name: String, cls: KClass<T>): T? = servletContext.getAttribute(name) as? T

    fun getBoolean(name: String): Boolean? = servletContext.getAttribute(name) as? Boolean
    fun getDouble(name: String): Double? = servletContext.getAttribute(name) as? Double
    fun getInt(name: String): Int? = servletContext.getAttribute(name) as? Int
    fun getLong(name: String): Long? = servletContext.getAttribute(name) as? Long
    fun getString(name: String): String? = servletContext.getAttribute(name) as? String

    inline fun <reified T: Any> require(name: String, errorMessage: String): T = require(name, T::class, errorMessage)

    fun <T: Any> require(name: String, cls: KClass<T>, errorMessage: String): T = get(name, cls) ?: throw Signal(Responses.WithStatusCode(StatusCodes.BadRequest, errorMessage))
}