package com.jventrib.ffmpeg.kotlin.dsl

class Input : IO() {

    fun url(urlS: String, block: (InputUrl.() -> Unit)? = null) {
        val url = InputUrl(urlS)
        block?.let { it(url) }
        urls.add(url)
    }


    override fun getTypeOperator() = "-i "


    class InputUrl(urlS: String) : Url(urlS) {
        fun loop(i: Int) {
            addOption("stream_loop", i.toString())
        }
    }
}