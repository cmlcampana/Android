package minhasanotacoes.cursoandroid.com.minhasanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edtTexto;
    private ImageView botao;

    private static final String ARQUIVO = "anotacao.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTexto = findViewById(R.id.edtAnotacoesId);
        botao = findViewById(R.id.botaoSalvarId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtTexto.getText().toString();

                gravarNoArquivo(texto);
                Toast.makeText(MainActivity.this, "Anotação gravada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        String anotacaoGravada = lerArquivo();
        if (anotacaoGravada != null){
            edtTexto.setText(anotacaoGravada);
        }

    }

    private void gravarNoArquivo(String texto){

        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(openFileOutput(ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();
        } catch (Exception e){
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo(){
        String resultado = "";

        try {
            InputStream inputStream = openFileInput(ARQUIVO);

            if (inputStream != null){
                InputStreamReader reader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(reader);

                String linhaArquivo = "";

                while ((linhaArquivo = bufferedReader.readLine()) != null) {
                    resultado += linhaArquivo;
                }

                inputStream.close();
            }

        } catch (Exception e){
            Log.v("MainActivity", e.toString());
        }

        return resultado;
    }
}
