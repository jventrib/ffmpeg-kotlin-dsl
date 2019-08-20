package com.jventrib.ffmpeg.kotlin.dsl

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun test1() {


        val s: String = ffmpeg {
            input {
                url("/test.mkv")
                url("/test2.mkv")
            }
            output {
                url("test.jpg")
            }
        }.toString()

        Assertions.assertThat(s).isEqualTo("ffmpeg -i /test.mkv -i /test2.mkv test.jpg")

    }

}