package com.jventrib.ffmpeg.kotlin.dsl

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MainOptions {

    @Test
    internal fun formatOption() {
        val ffmpeg = ffmpeg {
            input {
                url("input") {
                    format("avi")
                }
            }
            output {
                url("output.avi")
            }
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -f avi -i input output.avi")
    }

    @Test
    internal fun overwriteOption() {
        val ffmpeg = ffmpeg {
            input {
                url("input.avi")
            }
            output {
                url("output.avi")
            }
            overwrite()
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i input.avi output.avi -y")
    }


    @Test
    internal fun doNotOverwriteOption() {
        val ffmpeg = ffmpeg {
            input {
                url("input.avi")
            }
            output {
                url("output.avi")
            }
            doNotOverwrite()
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i input.avi output.avi -n")
    }



}