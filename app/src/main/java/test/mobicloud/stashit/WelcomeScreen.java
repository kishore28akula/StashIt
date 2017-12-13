package test.mobicloud.stashit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        Thread mainThread = new Thread(){
            public void run() {

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    // moving one screen to other screen...(Intent)
                    Intent logintype = new Intent(WelcomeScreen.this, WordInfo.class);
                    logintype.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logintype);
                }


            };

        };
        mainThread.start();

    }
}
