package com.realmo.notification


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("realmo","MainActivity onCreate:"+android.os.Process.myPid())
        if (!isNotificationListenerEnabled(this)){
            openNotificationListenSettings();
        }

        sendNormal()
    }


    //检测通知监听服务是否被授权
    fun isNotificationListenerEnabled(context: Context): Boolean {
        val packageNames =
            NotificationManagerCompat.getEnabledListenerPackages(this)
        return if (packageNames.contains(context.getPackageName())) {
            true
        } else false
    }

    //打开通知监听设置页面
    fun openNotificationListenSettings() {
        try {
            val intent: Intent
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            } else {
                intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //把应用的NotificationListenerService实现类disable再enable，即可触发系统rebind操作
    private fun toggleNotificationListenerService() {
        val pm = packageManager
        pm.setComponentEnabledSetting(
            ComponentName(
                this,
                RealMoNotificationListenerService::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
        )
        pm.setComponentEnabledSetting(
            ComponentName(
                this,
                RealMoNotificationListenerService::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
        )
    }


    //点击，发送普通通知
    fun sendNormal() { //NOTIFICATION_SERVICE是Context的内容
//1.获取NotificationManager
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //2.创建Notification对象
        val builder = NotificationCompat.Builder(this)
        //设置小图标,必须设置SmallIcon和Ticker否则不弹出通知
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
            .setTicker("Notification show status bar text") //设置的是通知时在状态栏显示的通知内容
            .setContentText("Content text...")
            .setContentTitle("Content title...")
        //设置通知铃声……
        builder.setDefaults(Notification.DEFAULT_ALL)


//        //跳转到指定的Activity
//        val intent = Intent(this, MainActivity::class.java)
//        //Intent-->PenddingIntent
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            1,
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )

//        builder.setContentIntent(pendingIntent)

        //设置点击后，自动取消
        builder.setAutoCancel(true)
        //创建对象
        val notification: Notification = builder.build()
        //3.通过manager发通知　
        manager.notify(100, notification)
    }

}
