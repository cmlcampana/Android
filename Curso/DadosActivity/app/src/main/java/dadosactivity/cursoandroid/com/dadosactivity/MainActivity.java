package dadosactivity.cursoandroid.com.dadosactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btnPassarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPassarDados = findViewById(R.id.btnPassarDados);

        btnPassarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                intent.putExtra("nome", "Camila");
                startActivity(intent);
            }
        });
    }
}
