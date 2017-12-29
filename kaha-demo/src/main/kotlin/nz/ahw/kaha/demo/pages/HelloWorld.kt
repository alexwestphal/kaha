package nz.ahw.kaha.demo.pages

import kotlinx.html.*
import nz.ahw.kaha.KahaServlet
import nz.ahw.kaha.page.Page
import nz.ahw.kaha.raw.rawScript
import nz.ahw.kaha.signaling.BadRequest
import javax.servlet.annotation.WebServlet

@WebServlet(urlPatterns = ["/hello"])
class HelloWorld: KahaServlet() {

    override fun get() = Page(SimpleLayout("Hello World")) {
        val userId: Int = parameters["user_id"] ?: throw BadRequest("Missing or invalid userId")

        div("message") {
            style = "color: blue;"
            +"Hello World from Kaha (user_id = $userId)"
        }

        rawScript("""
            $(document).ready(function() {
                $(".message").css({color: 'red'});
            })
        """)


    }
}