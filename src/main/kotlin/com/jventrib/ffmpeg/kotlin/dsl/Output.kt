package com.jventrib.ffmpeg.kotlin.dsl

class Output(block: Output.() -> Unit) : IO() {
    override fun getTypeOperator() = " "

    init {
        block()
    }



//    override fun toString(): String {
//        return urls.joinToString(" ")
//    }


}