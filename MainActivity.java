package hr.vsite.map.taxivodstvo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;
    Integer odgoda = 4000; // U milisekundama

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.iv_logo);
        img.animate().alpha(4000).setDuration(4000);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(dsp);
                finish();
            }
        }, odgoda);
    }
}
