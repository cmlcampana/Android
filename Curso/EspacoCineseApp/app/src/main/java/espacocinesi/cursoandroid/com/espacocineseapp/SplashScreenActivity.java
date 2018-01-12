package espacocinesi.cursoandroid.com.espacocineseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibirTelaPrincipal();
            }
        }, 2000);
    }

    private void exibirTelaPrincipal(){

        Intent main = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
