package com.example.opncodingchallenge.util

object StringUtil {
    fun String.processString(vararg info: String): String {
        var result = this
        info.forEachIndexed { index, s ->
            result = result.replace("{${index}}", s)
        }
        return result
    }
}