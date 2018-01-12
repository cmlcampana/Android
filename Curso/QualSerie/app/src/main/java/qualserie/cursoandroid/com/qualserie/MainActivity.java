package qualserie.cursoandroid.com.qualserie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.skBarId);
        imagem = findViewById(R.id.imagemResultadoId);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int valorProgresso = i;

                switch (valorProgresso){
                    case 1 :
                        imagem.setImageResource(R.drawable.pouco);
                        break;
                    case 2 :
                        imagem.setImageResource(R.drawable.medio);
                        break;
                    case 3 :
                        imagem.setImageResource(R.drawable.muito);
                        break;
                    case 4 :
                        imagem.setImageResource(R.drawable.susto);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
