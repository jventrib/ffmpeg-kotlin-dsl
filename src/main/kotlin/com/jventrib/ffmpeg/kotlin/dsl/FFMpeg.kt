package com.jventrib.ffmpeg.kotlin.dsl

@FFMpegDslMarker
class FFMpeg {

    private lateinit var input: Input
    private lateinit var output: Output

    fun input(block: Input.() -> Unit) {
        input = Input()
        input.block()
    }

    fun output(block: Output.() -> Unit) {
        output = Output()
        output.block()
    }

    override fun toString(): String {
        return "ffmpeg $input $output ${options.joinToString(" ")}".trim()
    }

    fun overwrite() {
        addOption("y")
    }

    fun doNotOverwrite() {
        addOption("n")
    }

    private val options: MutableList<Option> = mutableListOf()

    private fun addOption(name: String, value: String? = null) {
        options.add(Option(name, value))
    }

}


fun ffmpeg(block: FFMpeg.() -> Unit): FFMpeg = FFMpeg().apply(block)

@DslMarker
annotation class FFMpegDslMarker