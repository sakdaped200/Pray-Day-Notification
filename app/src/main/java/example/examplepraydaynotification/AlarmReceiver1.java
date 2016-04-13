package example.examplepraydaynotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class AlarmReceiver1 extends BroadcastReceiver {
    public AlarmReceiver1() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        //        throw new UnsupportedOperationException("Not yet implemented");
        // an Intent broadcast.
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC/GMT+7"), Locale.getDefault());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c.getTime());
        Log.d("date", "" + formattedDate);
        String[] currentSplit = formattedDate.split("/");

        String[] dateArray = context.getResources().getStringArray(R.array.date_pray);
        for (int i = 0; i < dateArray.length; i++) {
            String[] dataSplit = dateArray[i].split("/");
            if (currentSplit[0].equals(dataSplit[0])
                    && currentSplit[1].equals(dataSplit[1])
                    && currentSplit[2].equals(dataSplit[2])){
                // Date Matching Run Notification
                Intent service1 = new Intent(context, NotificationService1.class);
                service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
                context.startService(service1);
            }
        }
    }
}
