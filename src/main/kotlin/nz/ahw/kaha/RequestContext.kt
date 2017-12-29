package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest

abstract class RequestContext {

    abstract val request: HttpServletRequest

    val parameters = Parameters()

    inner class Parameters {

        operator fun get(name: String): String = request.getParameter(name)
    }
}