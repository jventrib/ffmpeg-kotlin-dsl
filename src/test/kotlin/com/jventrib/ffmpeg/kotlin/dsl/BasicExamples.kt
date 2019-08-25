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

    @Test
    internal fun `force the frame rate of the output file to 24 fps`() {
        val ffmpeg = ffmpeg {
            input {
                url("input.avi")
            }
            output {
                url("output.avi") {
                    frameRate(24)
                }
            }
        }
        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i input.avi -r 24 output.avi")
    }

        @Test
        internal fun `force the frame rate of the input file (valid for raw formats only) to 1 fps and the frame rate of the output file to 24 fps`() {
            val ffmpeg = ffmpeg {
            input {
                url("input.m2v") {
                    frameRate(1)
                }
            }
            output {
                url("output.avi") {
                    frameRate(24)
                }
            }
        }
        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -r 1 -i input.m2v -r 24 output.avi")
    }



}