/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import nz.ahw.kaha.KahaException

class BlockRenderException(cause: Throwable): KahaException("Error while rendering page", cause)