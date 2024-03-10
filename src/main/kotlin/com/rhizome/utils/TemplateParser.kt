package com.rhizome.utils

import com.rhizome.conf.AppConf
import kotlin.io.path.Path
import kotlin.io.path.readBytes

object TemplateParser {
    fun parse(fileName: String, replacer: String.() -> String): String {
        val template = String(Path("${AppConf.templateLocationPath}/$fileName").readBytes())
        val result = with(template, replacer)
        println("RESULT TEMPLATE PARSING $result")
        return result
    }
}