/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.signal

import nz.ahw.kaha.Responses

object Signals {

    open class StatusCode(errorCode: Int, message: String? = null): Signal(Responses.StatusCode(errorCode, message))

    class BadRequest(message: String): StatusCode(400, message)
}