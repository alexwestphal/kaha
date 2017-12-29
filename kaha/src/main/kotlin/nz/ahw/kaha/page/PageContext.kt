/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.page

import kotlinx.html.HtmlBlockTag
import nz.ahw.kaha.RequestContext
import javax.servlet.http.HttpServletRequest

class PageContext(override val request: HttpServletRequest, tag: HtmlBlockTag): RequestContext(), HtmlBlockTag by tag