/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import nz.ahw.kaha.http.StatusCode
import javax.servlet.http.HttpServletResponse

object Responses {
    object None: Response {
        override fun apply(httpServletResponse: HttpServletResponse) {}
    }

    open class WithStatusCode(private val statusCode: StatusCode, private val message: String = statusCode.defaultMessage): Response {
        override fun apply(httpServletResponse: HttpServletResponse) {
            httpServletResponse.sendError(statusCode.value, message)
        }
    }

    /**
     * Send a Redirect (status 302 - Found) to the client using the specified url.
     */
    class Redirect(val url: String): Response {
        override fun apply(httpServletResponse: HttpServletResponse) {
            httpServletResponse.sendRedirect(url)
        }
    }
}

