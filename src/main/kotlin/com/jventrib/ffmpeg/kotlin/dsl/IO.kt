package com.jventrib.ffmpeg.kotlin.dsl

@FFMpegDslMarker
abstract class IO {
    private val urls: MutableList<Url> = mutableListOf()


    fun url(s: String, options: (Url.() -> Unit)? = null) {
        val url = Url(s, options)
        urls.add(url)

    }

    class Url(private val url: String, optionsBlock: (Url.() -> Unit)?) {
        private val options: MutableList<Option> = mutableListOf()

        init {
            optionsBlock?.let { optionsBlock.invoke(this) }
        }

        data class Option(val name: String, val value: String)

        private fun addOption(name: String, value: String) {
            options.add(Option(name, value))
        }

        fun bitRate(block: BitRate.() -> Unit) {
            BitRate(this, block)
        }

        fun bufsize(value: String) {
            addOption("bufsize", value)
        }

        class BitRate(private val url: Url, block: BitRate.() -> Unit) {

            init {
                block()
            }
            fun video(value: String) {
                url.addOption("b:v", value)
            }

        }

        fun toString(typeOperator: String): String {
            return options.joinToString(" ") { "-${it.name} ${it.value}" }  + typeOperator + url
        }


    }


    abstract fun getTypeOperator(): String

    override fun toString(): String {
        return urls.joinToString(" ") { it.toString(getTypeOperator()) }
    }
}