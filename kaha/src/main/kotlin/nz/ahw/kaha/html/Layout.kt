/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.html

abstract class Layout(name: String?, val render: LayoutContext.() -> Unit) {

    val layoutName: String = name ?: this::class.java.simpleName

    constructor(render: LayoutContext.() -> Unit): this(null, render)
}