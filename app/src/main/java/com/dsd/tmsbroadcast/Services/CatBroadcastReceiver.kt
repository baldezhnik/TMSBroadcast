package com.dsd.tmsbroadcast.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.dsd.tmsbroadcast.MainActivity
import com.dsd.tmsbroadcast.R
import java.security.AccessController.getContext

class CatBroadcastReceiver() :BroadcastReceiver() {
    var COUNTER = 0
    private val CHANNEL_ID: String? = "2h1urh21r92r1h89"

    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras
        COUNTER++
        if(bundle!=null){
            var value = bundle.getInt("value")
            if(value<=20){
                context?.let { showNotification(it) }
            }
        }
    }
    private fun showNotification(context: Context) {
        val mainIntent = Intent(context, MainActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val mainPendingIntent = PendingIntent.getActivity(context, 2, mainIntent, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My Dear Cat")
            .setContentText("Эй, тебя давно не было видно, мне нужен присмотр")
            .setAutoCancel(true)
            .setContentIntent(mainPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1, notificationBuilder.build())
    }

}