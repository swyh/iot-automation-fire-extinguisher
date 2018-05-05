package com.power.kimyounghoon.project;

/**
 * Created by KIMYOUNGHOON on 2017-08-22.
 */

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.messaging.RemoteMessage;

//서버 -> 어플로 메세지 받는 코드
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    static String temp;

    public void setTemp(String temp){
        this.temp = temp;
    }
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
        PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
        wakeLock.acquire(3000);

        //추가한것
        temp = remoteMessage.getData().get("temp");  // 1 : 불꽃, 2 : 연기, 3 : 둘다
        sendNotification(remoteMessage.getData().get("message"));
    }

    private void sendNotification(String messageBody) {
        Intent intent;
        Log.i("temp값",temp);
        if(temp.equals("1")){
            Log.i("들어간temp값","1");
            intent = new Intent(this, AlertActivity.class);    // 분사확인 Activity

        }
        else if(temp.equals("2")){
            Log.i("들어간temp값","2");
            intent = new Intent(this, AlertActivity.class);    // 분사확인 ACtivity
        }
        else if(temp.equals("3")){
            Log.i("들어간temp값","3");
            intent = new Intent(this, Alert2Activity.class);    // 분사시키는 Activity
        }
        else{
            intent = new Intent(this, AlertActivity.class);    // 예외
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_CANCEL_CURRENT); //FLAG_ONE_SHOT

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("경고")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setTicker("Test1")
                .setPriority(Notification.PRIORITY_MAX); //** MAX 나 HIGH로 줘야 가능함

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}