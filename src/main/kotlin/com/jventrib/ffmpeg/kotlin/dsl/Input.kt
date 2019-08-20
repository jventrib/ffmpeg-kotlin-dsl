package com.jventrib.ffmpeg.kotlin.dsl

class Input(block: Input.() -> Unit) : IO() {

    override fun getTypeOperator() = " -i "

    init {
        block()
    }

//    override fun toString(): String {
//        return urls.joinToString(" ") { "-i $it" }
//    }


}