package com.dsd.tmsbroadcast.Services

import android.app.NotificationChannel
import android.app.NotificationManager
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
import com.dsd.tmsbroadcast.R
import java.security.AccessController.getContext

class CatBroadcastReceiver() :BroadcastReceiver() {
    var COUNTER = 0
    private val CHANNEL_ID: String? = "2h1urh21r92r1h89"

    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras
        COUNTER++
        Log.e("TAG", "I M HERREEEEEEEEEEEEEE")
        if(bundle!=null){
            var value = bundle.getInt("value")
            if(value<=20){
                Toast.makeText(context,"HEEEEEEEEEy", Toast.LENGTH_SHORT)
                var builder = context?.let {
                    NotificationCompat.Builder(it, "My Cat notification")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("My Dear Cat")
                        .setContentText("Эй, тебя давно не было видно, мне нужен присмотр")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                }
                if (builder != null) {
                    with(context?.let { NotificationManagerCompat.from(it) }) {
                        this?.notify(COUNTER, builder.build())
                    }
                }
            }
        }
    }


}