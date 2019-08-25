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

    @Test
    internal fun streamLoop() {
        val ffmpeg = ffmpeg {
            input {
                url("input.avi") {
                    loop(3)
                }
            }
            output {
                url("output.avi")
            }
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -stream_loop 3 -i input.avi output.avi")
    }

    @Test
    internal fun codec1() {
        val ffmpeg = ffmpeg {
            input {
                url("INPUT") {
                }
            }
            output {
                url("OUTPUT") {
                    map(0)
                    codec {
                        video("libx264")
                        audio("copy")
                    }
                }
            }
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i INPUT -map 0 -c:v libx264 -c:a copy OUTPUT")
    }

    @Test
    internal fun codec2() {
        val ffmpeg = ffmpeg {
            input {
                url("INPUT") {
                }
            }
            output {
                url("OUTPUT") {
                    map(0)
                    codec("copy") {
                        video(1, "libx264")
                        audio(137, "libvorbis")
                    }
                }
            }
        }

        Assertions.assertThat(ffmpeg.toString()).isEqualTo("ffmpeg -i INPUT -map 0 -c copy -c:v:1 libx264 -c:a:137 libvorbis OUTPUT")
    }


}