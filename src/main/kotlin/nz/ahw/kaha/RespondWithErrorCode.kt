/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RespondWithErrorCode(private val errorCode: Int): Handler {
    override fun apply(request: HttpServletRequest, response: HttpServletResponse) {
        response.sendError(errorCode)
    }
}