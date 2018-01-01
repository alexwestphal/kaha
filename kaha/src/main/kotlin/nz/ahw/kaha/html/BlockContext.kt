/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

import kotlinx.html.TagConsumer
import nz.ahw.kaha.KahaDSL

@KahaDSL
class BlockContext<out T>(consumer: TagConsumer<T>): TagConsumer<T> by consumer