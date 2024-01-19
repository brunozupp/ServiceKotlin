package com.novelitech.servicekotlin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * When I create a class : Service() it can support multi-thread, but it will run in the main
 * thread (UI Thread) by Default
 * IntentService can not support multi-thread, but it runs in a separate thread by default
 */
class MyService : Service() {

    val TAG = "MyService"

    init {
        Log.d(TAG, "Service is running...")
    }

    /**
     * I probably will not need this often because I would use it if I have multiple clients wanting
     * to connect to my service in the same time.
     * If I don't need this, I can return null
     */
    override fun onBind(intent: Intent?): IBinder? = null

    /**
     * It's used to delivery the intent I start this service with
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val dataString = intent?.getStringExtra("EXTRA_DATA")

        dataString?.let {
            Log.d(TAG, dataString)
        }

        /**
         * If I have complex logic/calculations that will take time and possible freeze my UI
         * I need to take this code inside a new Thread
         */
//        Thread {
//            while (true) {}
//        }.start()

        /**
         * START_NOT_STICKY = if the Android System kills the service it will be recreated if their is resources available
         * START_STICKY = if the Android System kills the service it will be recreated when possible. So the last intent won't be delivered and a new service will be started with a null intent
         * START_REDELIVER_INTENT = if the Android System kills the service it will be recreated when possible. So the last intent will be read when the service is recreated
         */
        return START_STICKY
    }

    // It's called before the service is destroyed
    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "Service is being killed")
    }
}