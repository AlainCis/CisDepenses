package com.example.alaincis.cisdepenses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button demarrer;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  declaration et initialisation des vues */
        gestionNotification();
        /*  fin declaration et initialisation des vues */
        /* Ouverture de la fenetre principale */
        Thread ouverture = new Thread(){
            @Override
            public void run() {
                try {
                        sleep(2000);
                }catch (InterruptedException e){
                        e.printStackTrace();
                }finally {
                    Intent f = new Intent(MainActivity.this, FenetrePrincipale.class);
                    startActivity(f);
                }
            }
        };
        ouverture.start();
        /* Fin Ouverture de la fenetre principale */
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void gestionNotification(){
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", 0);
        intent.putExtra("todo", "veuillez consultez vos courses du jour et actualisez vos dépenses!!");

        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY,10);
        startTime.set(Calendar.MINUTE,42);
        startTime.set(Calendar.SECOND,00);
        long alarmStartTime = startTime.getTimeInMillis();
        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
        //Toast.makeText(this, "Notifiacation Ok !! ", Toast.LENGTH_LONG).show();

    }
}
