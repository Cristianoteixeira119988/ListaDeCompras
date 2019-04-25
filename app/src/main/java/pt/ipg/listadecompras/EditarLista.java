package pt.ipg.listadecompras;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditarLista extends AppCompatActivity {


    private EditText edittextnomenovo;
    private Button botaoguardar;
    private Button botaocancelar;

    //calendario-------------------------------------
    private TextView txtViewVerData;
    private Button botaoescolherdata;
    Calendar calendario;
    DatePickerDialog datapiker;
    //------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edittextnomenovo = (EditText) findViewById(R.id.EditTextEditListaNomeNovo);
        botaocancelar = (Button) findViewById(R.id.buttonCancelar);
        botaoguardar = (Button) findViewById(R.id.buttonGuardarEditarLista);

        //calendario-----------------------------
        txtViewVerData= (TextView) findViewById(R.id.txtData);
        botaoescolherdata=(Button) findViewById(R.id.buttonEscolherData);
        botaoescolherdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario= Calendar.getInstance();
                int dia=calendario.get(Calendar.DAY_OF_MONTH);
                int mes= calendario.get(Calendar.MONTH);
                int ano = calendario.get(Calendar.YEAR);

                datapiker = new DatePickerDialog(EditarLista.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                        txtViewVerData.setText(mDia + "-" + (mMes+1) + "-"+ mAno);
                    }
                }, dia, mes, ano);
                datapiker.show();
            }
        });
        //-----------------------------------------



        botaoguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem2 = edittextnomenovo.getText().toString();
                if (mensagem2.trim().length() == 0) {
                    edittextnomenovo.setError(getString(R.string.nome_obrigatorio));
                } else if (mensagem2.length() <= 3) {
                    edittextnomenovo.setError(getString(R.string.numero_minimo_de_caracters));
                } else if (mensagem2.length() >= 25) {
                    edittextnomenovo.setError(getString(R.string.numero_maximo_de_caracters));
                } else {
                    finish();
                    Toast.makeText(EditarLista.this, getString(R.string.lista_alterada), Toast.LENGTH_SHORT).show();
                }
            }
        });

        botaocancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}


