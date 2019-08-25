package com.jventrib.ffmpeg.kotlin.dsl

class Input : IO() {
    override fun getTypeOperator() = "-i "
}