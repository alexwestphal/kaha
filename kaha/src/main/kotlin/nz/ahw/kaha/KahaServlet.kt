/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class KahaServlet: HttpServlet() {

    open fun delete(): Handler = RespondWithErrorCode(404)

    open fun get(): Handler = RespondWithErrorCode(404)

    open fun post(): Handler = RespondWithErrorCode(404)

    open fun put(): Handler = RespondWithErrorCode(404)

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        errorHandler(request, response) {
            delete().apply(request, response)
        }

    }

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        errorHandler(request, response) {
            get().apply(request, response)
        }
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        errorHandler(request, response) {
            post().apply(request, response)
        }
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {
        errorHandler(request, response) {
            put().apply(request, response)
        }
    }

    private fun errorHandler(request: HttpServletRequest, response: HttpServletResponse, body: () -> Unit) {
        try {
            body()
        } catch(ex: KahaException) {
            var info = "${request.method} ${request.requestURI}"
            if(null != request.queryString) info += "?${request.queryString}"

            throw ex.withSource(info)
        }
    }
}