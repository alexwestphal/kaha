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

interface Response {

    fun apply(httpServletResponse: HttpServletResponse)

}

fun Response(statusCode: StatusCode, message: String = statusCode.defaultMessage) = Responses.WithStatusCode(statusCode, message)