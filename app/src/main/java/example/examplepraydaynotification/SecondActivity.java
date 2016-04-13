package example.examplepraydaynotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initInstance();
    }

    private void initInstance() {
        Intent intent = getIntent();
        String textBundle = intent.getStringExtra("test");


        textView = (TextView) findViewById(R.id.textView);
        textView.setText(textBundle);

    }
}
