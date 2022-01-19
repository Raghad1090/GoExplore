//package com.raghad.goexplore
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.widget.RemoteViews
//import androidx.core.app.NotificationCompat
//import androidx.core.content.ContextCompat.getSystemService
//import com.google.firebase.messaging.FirebaseMessagingService
//
//const val channelId = "notificationChannel"
//const val channelName = "com.raghad.goexplore"
//
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//
//    override fun onMessageRecived(remoteMessage: RemoteMessage){
//
//        if (remoteMessage.getNotification() != null){
//
//            generateNotification(remoteMessage.notification!!.message!!)
//        }
//    }
//
//    fun getRemoteView(message: String): RemoteViews{
//
//        val remoteView = RemoteViews ("com.raghad.goexplore",R.layout.notification)
//
//        remoteView.setTextViewText(R.id.message,message)
//        remoteView.setImageViewResource(R.id.notificationImage,R.drawable.travelNotification)
//
//        return remoteView
//    }
//
//    fun generateNotification(message : String){
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
//
//
//        var builder : NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,channelId)
//            .setSmallIcon(R.drawable.travelNotification)
//            .setAutoCancel(true)
//            .setVibrate(longArrayOf(1000,1000,1000,1000))
//            .setOnlyAlertOnce(true)
//            .setContentIntent(pendingIntent)
//
//        builder = builder.setContent(getRemoteView(message))
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0){
//
//         val notificationChannel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
//         notificationManager.createNotificationChannel(notificationChannel)
//
//        }
//        notificationManager.notify(0,builder.build())
//    }
//
//
//}
