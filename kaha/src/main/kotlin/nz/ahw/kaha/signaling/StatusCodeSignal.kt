/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.signaling

open class StatusCodeSignal(val statusCode: Int, message: String): Signal(message)


open class BadRequest(message: String): StatusCodeSignal(400, message)