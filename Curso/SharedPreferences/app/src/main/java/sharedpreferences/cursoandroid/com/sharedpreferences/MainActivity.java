package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private EditText edtNome;
    private TextView textoResultado;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.btnSalvarId);
        edtNome = findViewById(R.id.edtNomeId);
        textoResultado = findViewById(R.id.txtResultadoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (edtNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Por favor preencha o nome!", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("nome", edtNome.getText().toString());
                    editor.commit();

                    setaTextoUsuario(edtNome.getText().toString());
                }
            }
        });

        // Recuperar dados salvos
        recuperaDadosSalvos();
    }

    private void recuperaDadosSalvos(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString("nome", "usuário não definido");

        setaTextoUsuario(nomeUsuario);
    }

    private void setaTextoUsuario(String nomeUsuario){
        textoResultado.setText("Olá, " + nomeUsuario + "!");
    }
}
