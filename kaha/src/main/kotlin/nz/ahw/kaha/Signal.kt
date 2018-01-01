/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServletResponse

class Signal(val response: Response): RuntimeException() {

    override fun fillInStackTrace(): Throwable = this

    companion object {
        operator fun invoke(response: (HttpServletResponse) -> Unit): Signal = Signal(response)
    }
}

class InvalidSignalLocationException(signal: Signal): KahaException("A Signal can't be sent within a response. Offending Signal: $signal")
