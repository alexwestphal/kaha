/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletResponse

object Responses {
    object None: Response {
        override fun apply(httpServletResponse: HttpServletResponse) {}
    }

    open class ErrorCode(private val errorCode: Int, private val message: String? = null): Response {
        override fun apply(httpServletResponse: HttpServletResponse) {
            if(null == message) httpServletResponse.sendError(errorCode)
            else httpServletResponse.sendError(errorCode, message)
        }
    }

    class BadRequest(message: String): ErrorCode(400, message)

    object NotFound: ErrorCode(404)

}

