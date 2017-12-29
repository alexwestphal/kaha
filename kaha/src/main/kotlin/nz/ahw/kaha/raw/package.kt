/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.raw

import kotlinx.html.*
import org.intellij.lang.annotations.Language

fun HTMLTag.rawHtml(@Language("HTML") html: String) {
    unsafe { raw(html) }
}

fun FlowOrPhrasingOrMetaDataContent.rawScript(@Language("JavaScript") javaScript: String) {
    script(type = ScriptType.textJavaScript) {
        unsafe { raw(javaScript) }
    }
}

fun FlowOrMetaDataContent.rawStyle(@Language("CSS") css: String) {
    style(type = StyleType.textCss) {
        unsafe { raw(css) }
    }
}
