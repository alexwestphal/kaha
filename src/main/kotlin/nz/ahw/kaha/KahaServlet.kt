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

    override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        delete().apply(req, resp)
    }

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        get().apply(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        post().apply(req, resp)
    }

    override fun doPut(req: HttpServletRequest, resp: HttpServletResponse) {
        put().apply(req, resp)
    }

}