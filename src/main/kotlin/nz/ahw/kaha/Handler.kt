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

interface Handler {

    fun apply(request: HttpServletRequest, response: HttpServletResponse)
}