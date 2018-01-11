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

    open class StatusCode(private val statusCode: Int, private val message: String? = null): Response {
        override fun apply(httpServletResponse: HttpServletResponse) {
            if(null == message) httpServletResponse.sendError(statusCode)
            else httpServletResponse.sendError(statusCode, message)
        }
    }



    // 2xx Success

    abstract class Success(statusCode: Int): StatusCode(statusCode)

    object OK: Success(200)
    object Created: Success(201)
    object Accepted: Success(202)
    object NonAuthoritativeInformation: Success(203)
    object NoContent: Success(204)
    object ResetContent: Success(205)

    // 3xx Redirection

    abstract class Redirection(statusCode: Int): StatusCode(statusCode)

    // 4xx Client Errors

    abstract class ClientError(statusCode: Int, message: String?): StatusCode(statusCode, message)

    class BadRequest(message: String? = null): ClientError(400, message)
    class NotFound(message: String? = null): StatusCode(404, message)


    // 5xx Server Errors

    abstract class ServerError(statusCode: Int): StatusCode(statusCode)

}

