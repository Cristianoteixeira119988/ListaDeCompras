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

public class CriarLista extends AppCompatActivity {

    private Button botaomercearia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaomercearia = (Button) findViewById(R.id.buttonMercearia);



        botaomercearia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Carregou para criar uma nova lista de mercearia", Toast.LENGTH_SHORT).show();
                Intent NovaActivity = new Intent(CriarLista.this, CriarListaMercearia.class);
                startActivity(NovaActivity);
            }
        });


    }

}
