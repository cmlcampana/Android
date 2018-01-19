package listatarefas.cursoandroid.com.listadetarefas;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private Button botao;
    private ListView listaTarefas;
    private SQLiteDatabase bancoDados;

    private ArrayAdapter<String> listaAdapter;
    private ArrayList<String> itens;
    private ArrayList<Integer> listaIds;
    private Integer posicaoDeletar = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            texto = findViewById(R.id.textoTarefaId);
            botao = findViewById(R.id.botaoAdicionarId);
            listaTarefas = findViewById(R.id.listaId);

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tarefa = texto.getText().toString();

                    if (tarefa.isEmpty()){
                        Toast.makeText(MainActivity.this, "Digite um valor para salvar", Toast.LENGTH_SHORT).show();
                    } else {
                        salvarTarefa(tarefa);
                        Toast.makeText(MainActivity.this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                        recuperarTarefas();
                        texto.setText("");
                    }
                }
            });

            listaTarefas.setLongClickable(true);
            listaTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    posicaoDeletar = position;

                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setIcon(android.R.drawable.ic_dialog_alert);
                    dialog.setTitle("Atenção");
                    dialog.setMessage("Deseja apagar a tarefa?");
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removeTarefa(listaIds.get(posicaoDeletar));
                                }
                            });
                    dialog.setNegativeButton("Não", null);
                    dialog.create();
                    dialog.show();
                    return true;
                }
            });

            carregaBancoDados();
            recuperarTarefas();
        } catch (Exception e){
            Log.v("MainActivity", e.toString());
        }
    }

    private void carregaBancoDados() {
        bancoDados = openOrCreateDatabase("appTarefas", MODE_PRIVATE, null);

        // criar tabela de tarefas
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS TAREFA(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tarefa VARCHAR)");
    }

    private void salvarTarefa(String tarefa) {
        bancoDados.execSQL("INSERT INTO tarefa (tarefa) VALUES ('" + tarefa + "')");
    }

    private void recuperarTarefas() {

        try {
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM TAREFA ORDER BY ID DESC", null);
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");

            itens = new ArrayList<String>();
            listaIds = new ArrayList<Integer>();
            cursor.moveToFirst();
            do {
                itens.add(cursor.getString(indiceColunaTarefa));
                listaIds.add(cursor.getInt(indiceColunaId));
            } while (cursor.moveToNext());

            listaAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, itens);
            listaTarefas.setAdapter(listaAdapter);

        } catch (Exception e){
            Log.v("MainActivity", e.toString());
        }
    }

    private void removeTarefa(Integer id){

        try {
            bancoDados.execSQL("DELETE FROM TAREFA WHERE ID = " + id);
            Toast.makeText(MainActivity.this, "Tarefa deletada com sucesso", Toast.LENGTH_SHORT).show();
            recuperarTarefas();
        } catch (Exception e){
            Log.v("MainActivity", e.toString());
        }
    }
}
