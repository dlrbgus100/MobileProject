package kr.ac.kumoh.ce.s20100884.database;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);

    startLoading();
}

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

}