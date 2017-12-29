/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.fa

import kotlinx.html.*

fun FlowOrPhrasingOrMetaDataContent.faInclude() {
    link(rel = LinkRel.stylesheet, href = "webjars/font-awesome/4.7.0/css/font-awesome.css")
}

fun FlowOrPhrasingContent.icon(faIcon: String, vararg faOptions: String) {
    i("fa fa-$faIcon ${faOptions.joinToString(" ")}")
}