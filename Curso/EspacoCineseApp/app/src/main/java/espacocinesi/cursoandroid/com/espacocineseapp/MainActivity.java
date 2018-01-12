package espacocinesi.cursoandroid.com.espacocineseapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView sobreClinica;
    private ImageView pilates;
    private ImageView procedimentos;
    private ImageView contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sobreClinica = findViewById(R.id.logoSobreId);
        pilates = findViewById(R.id.logoPilatesId);
        procedimentos = findViewById(R.id.logoProcedimentoId);
        contato = findViewById(R.id.logoContatoId);

        sobreClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clinica = new Intent(MainActivity.this, SobreClinicaActivity.class);
                startActivity(clinica);
            }
        });

    }
}
