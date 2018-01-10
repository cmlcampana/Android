package caraoucoroa.cursoandroid.com.caraoucoroa;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetalheActivity extends AppCompatActivity {

    private ImageView botaoVoltar;
    private ImageView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        botaoVoltar = findViewById(R.id.voltarId);
        resultado = findViewById(R.id.resultadoId);

        Bundle extra = getIntent().getExtras();

        if (extra != null){

            String opcaoEscolhida = extra.getString("opcao");

            if (opcaoEscolhida.equals("cara")){
                resultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_cara));
            } else {
                resultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.moeda_coroa));
            }
        }

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
