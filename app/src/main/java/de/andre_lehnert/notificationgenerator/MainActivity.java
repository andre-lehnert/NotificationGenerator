package de.andre_lehnert.notificationgenerator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "GENERATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String color[] = new String[] { "blue", "red", "brown", "white" };
    private final String form[] = new String[] { "rectangle", "sphere", "lego" };
    private final int res[] = new int[] { R.drawable.quadrat, R.drawable.kreis, R.drawable.lego };
    private final int resColor[] = new int[] { R.color.tokenBlue, R.color.tokenRed, R.color.tokenBrown, R.color.tokenWhite };

    // Token - App Mapping
    private final String apps[][] = new String[][]
    {
            { "com.google.android.gm",                  "com.whatsapp",                             "com.google.android.calendar",              "com.dropbox.android" },
            { "com.xing.android",                       "com.android.mms",                          "de.andre_lehnert.notificationgenerator",   "de.andre_lehnert.notification.a" },
            { "de.andre_lehnert.notification.b",        "de.andre_lehnert.notification.c",          "",                                         "" },
    };

    public void buttonClicked(View v){

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifcation = new NotificationCompat.Builder(this);

        notifcation.setContentTitle("Test Notification");

        int mNotificationId = 1;
        int row = 1;
        int col = 1;

        if(v.getId() == R.id.fab11) {
            row = 1;
            col = 1;

        } else if(v.getId() == R.id.fab12) {
            row = 1;
            col = 2;

        } else if(v.getId() == R.id.fab13) {
            row = 1;
            col = 3;

         }else if(v.getId() == R.id.fab14) {
            row = 1;
            col = 4;

        } else if(v.getId() == R.id.fab21) {
            row = 2;
            col = 1;

        } else if(v.getId() == R.id.fab22) {
            row = 2;
            col = 2;

        } else if(v.getId() == R.id.fab23) {
            row = 2;
            col = 3;

        } else if(v.getId() == R.id.fab24) {
            row = 2;
            col = 4;

        } else if(v.getId() == R.id.fab31) {
            row = 3;
            col = 1;

        } else if(v.getId() == R.id.fab32) {
            row = 3;
            col = 2;

        }

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notifcation.setContentIntent(resultPendingIntent);

        Log.d(TAG, "Button "+ row + col );
        mNotificationId = Integer.parseInt(row + col + (String.valueOf(System.currentTimeMillis() % 10000) ) );
        notifcation.setSmallIcon(res[row - 1]);
        notifcation.setLargeIcon(BitmapFactory.decodeResource(getResources(), res[row - 1]));
        notifcation.setColor(getResources().getColor(resColor[col - 1], getTheme()));
        notifcation.setContentText("☞ Simulate: "+ apps[row - 1][col - 1]);
        notifcation.setSubText("Date: "+dateFormat.format(new Date()));

        notifcation.setDefaults(Notification.DEFAULT_SOUND);

        // Will display the notification in the notification bar
        notificationManager.notify(mNotificationId, notifcation.build());



    }
}
