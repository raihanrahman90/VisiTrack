package com.visitrack.core.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.visitrack.core.R

class FirebaseNotification: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("Refreshed Token", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        p0.notification?.let {
            sendNotification(it)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage.Notification) {
        val channelId = getString(R.string.notification_channel_id)
        val channelName = getString(R.string.notification_channel_name)

        //ADD INTENT
        val intent = Intent(this, Class.forName("com.visitrack.main.MainActivity"))
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.visitrackabu)
            .setContentTitle(remoteMessage.title)
            .setContentText(remoteMessage.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationBuilder.setChannelId(channelId)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(0, notification)
    }
}