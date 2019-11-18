package com.example.alaincis.cisdepenses;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by AlainCis on 13/10/2019.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Initialisation de l'id et du message de la notification
        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("todo");
        //Lorsqu'on clique sur la notification ça ouvre la FenetrePrincipale
        Intent m = new Intent(context, FenetrePrincipale.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,m,0);
        NotificationManager myNotificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        //Préparation de la notifiacation
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic1)
                .setContentTitle("Cis Dépenses")
                .setContentText(message)
                .setWhen(1000)
                .setColor(0216213)
                .setVisibility(Notification.DEFAULT_VIBRATE)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
        //Notifier
        myNotificationManager.notify(notificationId, builder.build());



    }
}
