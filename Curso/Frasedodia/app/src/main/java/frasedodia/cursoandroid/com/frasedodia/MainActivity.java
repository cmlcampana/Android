package frasedodia.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnNovaFrase;
    private TextView novaFrase;

    private String[] frases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarArrayFrases();

        btnNovaFrase = (Button) findViewById(R.id.btnTrocaFrase);
        novaFrase = (TextView) findViewById(R.id.txtFraseDia);

        btnNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random randomico = new Random();
                int indice = randomico.nextInt(frases.length);

                novaFrase.setText(frases[indice]);
            }
        });
    }

    private void carregarArrayFrases(){
        frases = new String[]{
                "Não deixe que as pessoas te façam desistir daquilo que você mais quer na vida. Acredite. Lute. Conquiste. E acima de tudo, seja feliz.",
                "Deixe pra trás o que não te leva pra frente.",
                "O silêncio é a única resposta que devemos dar aos tolos. Porque onde a ignorância fala, a inteligência não dá palpites.",
                "Algumas vezes coisas ruins acontecem em nossas vidas para nos colocar na direção das melhores coisas que poderíamos viver.",
                "Ser feliz não é viver apenas momentos de alegria. É ter coragem de enfrentar os momentos de tristeza e sabedoria para transformar os problemas em aprendizado.",
                "Viva simples, sonhe grande, seja grato, dê amor, ria muito!",
                "Independente do que estiver sentindo, levante-se, vista-se e saia para brilhar."
        };
    }
}
