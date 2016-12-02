package holysearch.holysearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 4 seconds
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        }, 3500);


    }
}
