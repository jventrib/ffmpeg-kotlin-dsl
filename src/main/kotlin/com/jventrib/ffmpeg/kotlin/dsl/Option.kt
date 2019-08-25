package com.jventrib.ffmpeg.kotlin.dsl

data class Option(val name: String, val value: String?) {
    override fun toString(): String {
        return "-" + name + getValueString()
    }

    private fun getValueString() = value?.let { " $value " } ?: ""
}