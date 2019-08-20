package com.jventrib.ffmpeg.kotlin.dsl

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BasicExamples {

    @Test
    internal fun `set the video bitrate of the output file to 64 kbits`() {

        val ffmpeg = ffmpeg {
            input {
                url("input.avi")
            }
            output {
                url("output.avi") {
                    bitRate {
                        video("64k")
                    }
                    bufsize("64k")
                }
            }
        }
        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i input.avi -b:v 64k -bufsize 64k output.avi")
    }
}