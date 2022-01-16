package com.example.opncodingchallenge.util

import com.example.opncodingchallenge.util.StringUtil.processString
import org.junit.Test

class StringUtilTest {
    @Test
    fun replaceString() {
        val test1 = "test {0}".processString("0")
        assert(test1 == "test 0")

        val test2 = "test {0} {1}".processString("0", "1")
        assert(test2 == "test 0 1")

        val test3 = "test {1}".processString("0")
        assert(test3 != "test 0")
    }

}