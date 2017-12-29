/*---------------------------------------------*\
**                                             **
**        Kaha Web Framework                   **
**        Copyright 2018, Alex Westphal        **
**        https://github.com/ahwnz/kaha        **
**                                             **
\*---------------------------------------------*/
package nz.ahw.kaha.fa

import java.io.FileOutputStream

object Generate {

    val dir = "kaha-font-awesome/src/main/kotlin/nz/ahw/kaha/fa"
    val cssFile = "META-INF/resources/webjars/font-awesome/4.7.0/css/font-awesome.css"

    @JvmStatic
    fun main(args: Array<String>) {
        val loader = Generate::class.java.classLoader
        val lines = loader.getResourceAsStream(cssFile).reader().readLines()
        val icons = lines
                .map { it.trim() }
                .filter { it.startsWith(".fa-") && it.endsWith(":before {") }
                .map { it.substring(4, it.indexOf(':')).let { icon -> Pair(icon.toCamelCase(), icon) } }

        FileOutputStream("$dir/FAIcon.kt").writer().use { writer ->
            writer.append("/*---------------------------------------------*\\\n" +
                    "**                                             **\n" +
                    "**        Kaha Web Framework                   **\n" +
                    "**        Copyright 2018, Alex Westphal        **\n" +
                    "**        https://github.com/ahwnz/kaha        **\n" +
                    "**                                             **\n" +
                    "\\*---------------------------------------------*/\n")
            writer.append("package nz.ahw.kaha.fa\n\n")

            writer.append("object FAIcon {\n")

            for((name, value) in icons)
                writer.append("    const val `$name` = \"$value\"\n")

            writer.append("}")
        }
    }

    private fun String.toCamelCase(): String =
            this.split("-").map(String::capitalize).joinToString("").decapitalize()
}
