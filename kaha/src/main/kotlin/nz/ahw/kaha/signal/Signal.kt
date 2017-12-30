/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.signal

import nz.ahw.kaha.Response
import javax.servlet.http.HttpServletResponse

open class Signal(val response: Response): RuntimeException() {

    override fun fillInStackTrace(): Throwable = this

    companion object {
        operator fun invoke(response: (HttpServletResponse) -> Unit): Signal = Signal(response)
    }
}

