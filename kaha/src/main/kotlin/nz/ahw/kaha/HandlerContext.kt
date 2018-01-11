/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest
import kotlin.reflect.KClass

@KahaDSL
class HandlerContext(val request: HttpServletRequest) {

    val parameters = Parameters(this)

    fun signal(response: Response): Nothing = throw Signal(response)

    class Parameters(val context: HandlerContext) {

        operator inline fun <reified T: Any> get(name: String): T? = get(name, T::class)

        @Suppress("UNCHECKED_CAST")
        fun <T: Any> get(name: String, cls: KClass<T>): T? = when(cls) {
            Boolean::class -> getBoolean(name) as T?
            Double::class -> getDouble(name) as T?
            Int::class -> getInt(name) as T?
            Long::class -> getLong(name) as T?
            String::class -> getString(name) as T?
            else -> throw IllegalArgumentException("Can't extract '${cls.java.name}' from a parameter")
        }

        fun getBoolean(name: String): Boolean? = context.request.getParameter(name)?.toBoolean()
        fun getDouble(name: String): Double? = context.request.getParameter(name)?.toDoubleOrNull()
        fun getInt(name: String): Int? = context.request.getParameter(name)?.toIntOrNull()
        fun getLong(name: String): Long? = context.request.getParameter(name)?.toLongOrNull()
        fun getString(name: String): String? = context.request.getParameter(name)

        inline fun <reified T: Any> require(name: String, errorMessage: String): T = require(name, T::class, errorMessage)

        fun <T: Any> require(name: String, cls: KClass<T>, errorMessage: String): T = get(name, cls) ?: context.signal(Responses.BadRequest(errorMessage))
    }
}