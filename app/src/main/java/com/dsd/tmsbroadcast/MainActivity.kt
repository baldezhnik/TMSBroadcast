package com.dsd.tmsbroadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.dsd.tmsbroadcast.Fragments.StartFragment
import com.dsd.tmsbroadcast.Services.CatBroadcastReceiver
import com.dsd.tmsbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID: String? = "2h1urh21r92r1h89"
    private lateinit var binding: ActivityMainBinding
    private val catReceiver = CatBroadcastReceiver()
    val intentFilter = IntentFilter("com.dsd.tmsbroadcast.android.action.broadcast")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        registerCatReceiver(catReceiver)
        intentFilter.addAction("Parameters Changed")
        startStartFragment()
    }


    private fun startStartFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, StartFragment()).commit()
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Dear Cat"
            val descriptionText = "Come and see me"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun registerCatReceiver(receiver: CatBroadcastReceiver){
        registerReceiver(receiver, intentFilter)
    }
}