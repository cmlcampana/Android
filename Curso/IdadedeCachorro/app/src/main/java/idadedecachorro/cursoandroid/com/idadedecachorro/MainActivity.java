package idadedecachorro.cursoandroid.com.idadedecachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botaoDescrobrirIdade;
    private TextView txtIdade;
    private EditText edtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoDescrobrirIdade = (Button) findViewById(R.id.btnDescobirIdade);
        edtIdade = (EditText) findViewById(R.id.edtIdadeCachorro);
        txtIdade = (TextView) findViewById(R.id.txtResultadoIdade);

        botaoDescrobrirIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idadeDigitada = edtIdade.getText().toString();

                if (!idadeDigitada.isEmpty()){
                    int valorDigitado = Integer.parseInt(idadeDigitada);
                    int idade = valorDigitado * 7;

                    txtIdade.setText("A idade do cachorro em anos humanos é " + idade + " anos");
                } else {
                    txtIdade.setText("Nenhum número digitado.");
                }
            }
        });
    }
}
