package gasolinaoualcool.cursoandroid.com.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText edtPrecoAlcool;
    private EditText edtPrecoGasolina;
    private TextView txtResultado;
    private Button btnVerificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrecoAlcool = findViewById(R.id.edtPrecoAlcool);
        edtPrecoGasolina = findViewById(R.id.edtPrecoGasolina);
        btnVerificar = findViewById(R.id.btnVerificar);
        txtResultado = findViewById(R.id.txtTextoResultado);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoAcool = edtPrecoAlcool.getText().toString();
                String textoGasolina = edtPrecoGasolina.getText().toString();

                if (textoAcool.isEmpty() || textoGasolina.isEmpty()){
                    txtResultado.setText("É necessário preencher o campo do valor do álcool e da gasolina!");
                } else {
                    Double valorAlcool = Double.parseDouble(textoAcool);
                    Double valorGasolina = Double.parseDouble(textoGasolina);

                    // (valorAlcool / valorGasolina)
                    Double resultado = valorAlcool / valorGasolina;

                    if (resultado >= 0.7){
                        txtResultado.setText("É melhor utilizar gasolina!");
                    } else {
                        txtResultado.setText("É melhor utilizar álcool!");
                    }
                }
            }
        });
    }
}
