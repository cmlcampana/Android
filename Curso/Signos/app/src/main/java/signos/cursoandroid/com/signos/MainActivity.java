package signos.cursoandroid.com.signos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private String[] listaSignos;
    private String[] detalhesSignos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregaListaSignos();
        carregaDetalhesSignos();

        lista = findViewById(R.id.listaSignoId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaSignos
        );

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), detalhesSignos[i].toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void carregaListaSignos(){
        listaSignos = new String[]{
                "Áries", "Touro", "Gêmeos", "Câncer", "Leão",
                "Virgem", "Libra", "Escorpião", "Sagitário",
                "Capricórnio", "Aquário", "Peixes"
        };
    }

    private void carregaDetalhesSignos(){
        detalhesSignos = new String[]{
                "Arianos são...",
                "Taurinos são...",
                "Geminianos são...",
                "Cancerianos são...",
                "Leoninos são...",
                "Virginianos são...",
                "Librianos são...",
                "Escorpianos são...",
                "Sagitarianos são...",
                "Capricornianos são...",
                "Aquarianos são...",
                "Piscianos são..."
        };
    }
}
