package app.ksit.indiapost;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by Shravan on 01-04-2017.
 */

public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        t1=(TextView) findViewById(R.id.tv2);
        t2=(TextView) findViewById(R.id.tv3);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "ComicRelief.ttf");
        t1.setTypeface(face);
        t2.setTypeface(face);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}