package com.uni.myuniapp

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log

class CalculateService : Service() {

    private val handlerThread = HandlerThread("MyThread")

    private val handler by lazy {
        object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                Log.d(
                    "Thread",
                    "isMainThread:${Looper.myLooper()?.thread == Looper.getMainLooper().thread}"
                )
                val first = msg.data.getLong("first")
                val second = msg.data.getLong("second")
                val operation = msg.data.getString("operation")

                val result = when (operation) {
                    "*" -> first * second
                    "+" -> first + second
                    "-" -> first - second
                    "/" -> first / second
                    else -> null
                }?.toString()
                msg.replyTo.send(Message().apply { data.putString("calculationResult", result) })
            }
        }
    }

    private val messenger by lazy { Messenger(handler) }

    override fun onBind(intent: Intent): IBinder {
        return messenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        handlerThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quitSafely()
    }
}
