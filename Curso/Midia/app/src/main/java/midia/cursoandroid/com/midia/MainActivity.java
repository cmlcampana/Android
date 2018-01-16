package midia.cursoandroid.com.midia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.btnTocar);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.musica);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tocarMusica(!mediaPlayer.isPlaying());
            }
        });
    }

    private void tocarMusica(boolean start){

        if (mediaPlayer != null){

            if (start) {
                mediaPlayer.start();
                botao.setText("Pausar");
            }
            else {
                mediaPlayer.pause();
                botao.setText("Tocar");
            }
        }
    }

    @Override
    protected void onDestroy() {

        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
