package com.example.demo.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import org.json.JSONObject;

import timber.log.Timber;


public class NotificationUtils {

    private Context mContext;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private Notification notification;
    private Resources mResources;

    public static final int NOTIFY_ACTIVITY_ID_SERVICE = 1001;

    private String channelId = "";

    public PendingIntent launchIntent;

    public NotificationUtils(Context context) {
        this.mContext = context;
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        this.mResources = mContext.getResources();
        channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "";
        notificationBuilder = new NotificationCompat.Builder(mContext, channelId);

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager) {
        String channelId = "table_app_channel_id";
        String channelName = "Table App Notification";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setImportance(NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.setLightColor(Color.GREEN);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }

    /*public void startNotification(RemoteMessage remoteMessage) {

        try {

            JsonObject jsonObject = convertRemoteMessage(remoteMessage);
            Log.i("Servicex", "onMessageServiceY==>" + jsonObject.toString());
            String titlePush = remoteMessage.getNotification().getTitle();
            String textPush = remoteMessage.getNotification().getBody();

//            //you can get your text message here.
//            String titlePush= data.get("title");
//            String textPush = data.get("text");

            Log.e("title", titlePush);
            Log.e("text", textPush);

            Intent resultIntent = new Intent(mContext, SplashActivity.class);
            resultIntent.putExtra("remote_message_data", remoteMessage);
            PendingIntent launchIntent = PendingIntent.getActivity(mContext, 100, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            notification = notificationBuilder.setOngoing(false)
                    .setSmallIcon(R.drawable.ic_app_icon)
                    .setContentTitle(titlePush)
                    .setContentText(textPush)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setAutoCancel(true)
                    .setContentIntent(launchIntent)
                    .setTicker(titlePush)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .build();


            notificationManager.notify(NOTIFY_ACTIVITY_ID_SERVICE, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void cancelNotification(int cancelId) {
        if (notificationManager != null) {
            notificationManager.cancel(cancelId);
        }
    }


    private Bitmap getLargeIconBitmap(int iconDrawable) {
        if (iconDrawable == 0) return null;
        Bitmap largeIcon = BitmapFactory.decodeResource(mResources, iconDrawable);
        int height = (int) mResources.getDimension(android.R.dimen.notification_large_icon_height);
        int width = (int) mResources.getDimension(android.R.dimen.notification_large_icon_width);
        return Bitmap.createScaledBitmap(largeIcon, width, height, false);
    }

  /*  public JsonObject convertRemoteMessage(RemoteMessage remoteMessage) {
        JsonObject jsonObject = new JsonObject(); // com.google.gson.JsonObject
        JsonParser jsonParser = new JsonParser(); // com.google.gson.JsonParser
        Map<String, String> map = remoteMessage.getData();
        String valData;

        for (String mykey : map.keySet()) {
            valData = map.get(mykey);
            try {
                jsonObject.add(mykey, jsonParser.parse(valData));
            } catch (Exception e) {
                jsonObject.addProperty(mykey, valData);
            }
        }
        return jsonObject;
    }*/

    public static String convertBundleToJsonObject(Bundle bundle) {
        JSONObject jsonObject = new JSONObject();
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            try {
                jsonObject.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Timber.tag("jsonObject").e(" bundle ==>" + jsonObject);
        return jsonObject.toString();
    }

}
