package com.jventrib.ffmpeg.kotlin.dsl

class Output : IO() {
    override fun getTypeOperator() = ""

    fun url(urlS: String, block: (OutputUrl.() -> Unit)? = null) {
        val url = OutputUrl(urlS)
        block?.let { it(url) }
        urls.add(url)
    }

    class OutputUrl(urlS: String): Url(urlS) {
        fun map(stream: Int) {
            addOption("map", stream.toString())
        }
    }
}