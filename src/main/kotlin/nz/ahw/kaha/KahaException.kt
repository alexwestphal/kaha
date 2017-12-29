/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

open class KahaException: RuntimeException {

    constructor(message: String): super(message)

    constructor(cause: Throwable): super(cause)

    constructor(message: String, cause: Throwable): super(message, cause)

    fun withSource(source: String): KahaException {
        return KahaException("$message [$source]", cause!!)
    }

    override fun fillInStackTrace(): Throwable = this
}