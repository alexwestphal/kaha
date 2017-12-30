/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest

class HandlerContext(val request: HttpServletRequest): RequestContext() {

    val parameters = Parameters()

    inner class Parameters {

        operator inline fun <reified T> get(name: String): T? = when(T::class) {
            Boolean::class -> getBoolean(name) as T
            Double::class -> getDouble(name) as T
            Int::class -> getInt(name) as T
            Long::class -> getLong(name) as T
            String::class -> getString(name) as T
            else -> throw IllegalArgumentException("Can't extract '${T::class.java.name}' from a parameter")
        }

        fun getBoolean(name: String): Boolean? = request.getParameter(name)?.toBoolean()
        fun getDouble(name: String): Double? = request.getParameter(name)?.toDoubleOrNull()
        fun getInt(name: String): Int? = request.getParameter(name)?.toIntOrNull()
        fun getLong(name: String): Long? = request.getParameter(name)?.toLongOrNull()
        fun getString(name: String): String? = request.getParameter(name)
    }
}