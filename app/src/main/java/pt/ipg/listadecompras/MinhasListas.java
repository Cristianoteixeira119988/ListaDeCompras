package pt.ipg.listadecompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MinhasListas extends AppCompatActivity {


    private Button botaoeditarlista;
    private Button botaoapagarlista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoeditarlista = (Button) findViewById(R.id.buttonEditarLista);
        botaoapagarlista = (Button) findViewById(R.id.buttonApagarLista);

        botaoapagarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregar_botao_apagar), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(MinhasListas.this,ApagarLista.class );
                startActivity(outraactivity);


            }
        });

        botaoeditarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregar_botao_editar_lista), Toast.LENGTH_SHORT).show();
                Intent outraactivity = new Intent(MinhasListas.this, EditarLista.class);
                startActivity(outraactivity);
            }
        });

    }

}
