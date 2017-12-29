/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.page

import nz.ahw.kaha.KahaException

class LayoutRenderException(layoutName: String, cause: Throwable): KahaException("Error while rendering layout: $layoutName", cause)