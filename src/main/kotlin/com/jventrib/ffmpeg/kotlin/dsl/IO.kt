package com.jventrib.ffmpeg.kotlin.dsl

@FFMpegDslMarker
abstract class IO {
    private val urls: MutableList<Url> = mutableListOf()

    fun url(urlS: String, block: (Url.() -> Unit)? = null) {
        val url = Url(urlS)
        block?.let { it(url) }
        urls.add(url)
    }

    class Url(private val url: String) {
        private val options: MutableList<Option> = mutableListOf()

        private fun addOption(name: String, value: String) {
            options.add(Option(name, value))
        }

        fun bitRate(block: BitRate.() -> Unit) {
            BitRate(this).block()
        }

        fun bufsize(value: String) {
            addOption("bufsize", value)
        }

        fun frameRate(rate: Int) {
            addOption("r", rate.toString())
        }

        /**
         * Force input or output file format. The format is normally auto detected for input files and guessed from the file extension for output files, so this option is not needed in most cases.
         */
        fun format(fmt: String) {
            addOption("f", fmt)
        }

        class BitRate(private val url: Url) {
            fun video(value: String) {
                url.addOption("b:v", value)
            }
        }

        fun toString(typeOperator: String): String {
            return options.joinToString("") + typeOperator + url
        }
    }


    abstract fun getTypeOperator(): String

    override fun toString(): String {
        return urls.joinToString(" ") { it.toString(getTypeOperator()) }
    }

}