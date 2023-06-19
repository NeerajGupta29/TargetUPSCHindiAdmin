package target.upsc.hindiadmin;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static target.upsc.hindiadmin.App.FCM_CHANNEL_ID;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

            String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();
            Notification notification=new NotificationCompat.Builder(this,FCM_CHANNEL_ID).
                    setSmallIcon(R.drawable.mygslogo).
                    setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.gslogo7))
                    .setContentTitle(title).setContentText(body)
                    .build();
            NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1003,notification);

    }
    @Override
    public void  onDeletedMessages(){
        super.onDeletedMessages();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
