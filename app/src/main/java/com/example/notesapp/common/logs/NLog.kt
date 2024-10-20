package com.example.notesapp.common.logs

import android.util.Log

object NLog {
    @JvmStatic
    fun d(message: String) {

        val messages = message.splitBySize()

        messages.forEach {
            Log.d(stackElement.tag(), stackElement.msg(it))
        }
    }

    private val stackElement: StackTraceElement
        get() = Thread.currentThread().stackTrace.callerStackElementFrom(this)
}