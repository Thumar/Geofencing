package com.app.androidkt.geofencing;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
import java.util.Random;

public class GeofenceRegistrationService extends IntentService {

    private static final String TAG = "GeoIntentService";
    private NotificationManager notificationManager;

    public GeofenceRegistrationService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (geofence.getRequestId().equals(Constants.GEOFENCE_ID_STAN_UNI)) {
                switch (transaction) {
                    case Geofence.GEOFENCE_TRANSITION_ENTER:
                        Log.d(TAG, "You are inside Stanford University");
                        showNotification("You are inside Stanford University");
                        break;

                    case Geofence.GEOFENCE_TRANSITION_EXIT:
                        Log.d(TAG, "You are outside Stanford University");
                        showNotification("You are outside Stanford University");
                        break;

                    case Geofence.GEOFENCE_TRANSITION_DWELL:
                        Log.d(TAG, "You are dwelling inside Stanford University");
                        showNotification("You are dwelling inside Stanford University");
                        break;


                }
            }
        }
    }

    private void showNotification(String contentText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                    new NotificationChannel(
                            "default",
                            "general",
                            NotificationManager.IMPORTANCE_DEFAULT
                    )
            );
        }

        Notification myNotification = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setContentTitle("Notify")
                .setContentText(contentText)
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .build();
        notificationManager.notify(new Random().nextInt(10000), myNotification);
    }
}
