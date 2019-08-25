package com.jventrib.ffmpeg.kotlin.dsl

@FFMpegDslMarker
abstract class IO {
    protected val urls: MutableList<Url> = mutableListOf()

    open class Url(private val url: String) {
        private val options: MutableList<Option> = mutableListOf()

        protected fun addOption(name: String, value: String) {
            options.add(Option(name, value))
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

        fun bitRate(block: AV.() -> Unit) {
            BitRate(this).block()
        }

        fun codec(value:String? = null, block: AV.() -> Unit) {
            addAVOption(value, Codec(this), block)
        }

        private fun addAVOption(value: String?, av: AV, block: AV.() -> Unit) {
            value?.let {
                addOption(av.prefix, value)
            }
            block(av)
        }

        open class AV(private val url: Url, internal val prefix: String) {
            fun audio(value: String) {
                audio(null, value)
            }

            fun video(value: String) {
                video(null, value)
            }

            fun audio(stream: Int? = null, value: String) {
                av("a", stream, value)
            }

            fun video(stream: Int? = null, value: String) {
                av("v", stream, value)
            }

            private fun av(avType: String, stream: Int?, value: String) {
                url.addOption("$prefix:$avType${stream?.let { ":$it" } ?: ""}", value)
            }
        }

        class BitRate(url: Url) : AV(url, "b")

        class Codec(url: Url) : AV(url, "c")

        fun toString(typeOperator: String): String {
            return options.joinToString("") + typeOperator + url
        }

    }


    abstract fun getTypeOperator(): String

    override fun toString(): String {
        return urls.joinToString(" ") { it.toString(getTypeOperator()) }
    }


}