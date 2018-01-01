/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest

@KahaDSL
class HandlerContext(val request: HttpServletRequest) {

    val parameters = Parameters(this)

    fun signal(response: Response): Nothing = throw Signal(response)

    class Parameters(val context: HandlerContext) {

        operator inline fun <reified T> get(name: String): T? = when(T::class) {
            Boolean::class -> getBoolean(name) as T
            Double::class -> getDouble(name) as T
            Int::class -> getInt(name) as T
            Long::class -> getLong(name) as T
            String::class -> getString(name) as T
            else -> throw IllegalArgumentException("Can't extract '${T::class.java.name}' from a parameter")
        }

        fun getBoolean(name: String): Boolean? = context.request.getParameter(name)?.toBoolean()
        fun getDouble(name: String): Double? = context.request.getParameter(name)?.toDoubleOrNull()
        fun getInt(name: String): Int? = context.request.getParameter(name)?.toIntOrNull()
        fun getLong(name: String): Long? = context.request.getParameter(name)?.toLongOrNull()
        fun getString(name: String): String? = context.request.getParameter(name)

        inline fun <reified T> require(name: String, errorMessage: String): T = get(name) ?: context.signal(Responses.BadRequest(errorMessage))
    }
}