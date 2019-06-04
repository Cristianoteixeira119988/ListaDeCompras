package pt.ipg.listadecompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private Button botao2;

    private Button botaocategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        botao=(Button) findViewById(R.id.buttonAddProduto);
        botao2=(Button) findViewById(R.id.buttonListaTotal);

        botaocategorias=(Button) findViewById(R.id.buttonCategorias);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_adicionar_produto), Toast.LENGTH_SHORT).show();
                Intent outraActivity = new Intent(MainActivity.this, TodosProduto.class);
                startActivity(outraActivity);
            }
        });

        botao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_ver_lista_compras), Toast.LENGTH_SHORT).show();
                Intent outraActivity = new Intent(MainActivity.this, MinhasListas.class);
                startActivity(outraActivity);
            }
        });


        botaocategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_ver_todas_as_categorias), Toast.LENGTH_SHORT).show();
                Intent outraActivity =  new Intent(MainActivity.this, Categorias.class);
                startActivity(outraActivity);
            }
        });

    }



}
