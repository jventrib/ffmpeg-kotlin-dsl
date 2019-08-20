package com.jventrib.ffmpeg.kotlin.dsl

@FFMpegDslMarker
class FFMpeg(block: FFMpeg.() -> Unit) {

    private lateinit var input: Input
    private lateinit var output: Output

    init {
        block()
    }

    fun input(block: Input.() -> Unit) {
        input = Input(block)
    }

    fun output(block: Output.() -> Unit) {
        output = Output(block)
    }

    override fun toString(): String {
        return "ffmpeg$input $output"
    }

}


fun ffmpeg(block: FFMpeg.() -> Unit): FFMpeg {
    return FFMpeg(block)
}

@DslMarker
annotation class FFMpegDslMarker