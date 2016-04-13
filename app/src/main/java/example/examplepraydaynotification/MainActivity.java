package example.examplepraydaynotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private Intent alarmIntent;
    private Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();


    }

    private void initInstance() {
        // View Matching
        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(clickListener);


        boolean alarmUp = (PendingIntent.getBroadcast(MainActivity.this, 0,
                new Intent(MainActivity.this, AlarmReceiver1.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        // Alarming not active
        if (alarmUp) {
            Log.d("Alarm", "Alarm is already active");
        } else {
            // Set Alarming Service
            setAlarmingService();
            Log.d("Alarm", "Alarm is't activate");
        }
    }

    private void setAlarmingService() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(MainActivity.this, AlarmReceiver1.class); // AlarmReceiver1 = broadcast receiver

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.cancel(pendingIntent);

        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        // รันทุก ๆ 8 โมงเช้า
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 8);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);
        if (now.after(alarmStartTime)) {
            Log.d("Hey", "Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Log.d("Alarm", "Alarms set for everyday 8 am.");
    }

    /**
     * Listener Zone
     */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    };

}
