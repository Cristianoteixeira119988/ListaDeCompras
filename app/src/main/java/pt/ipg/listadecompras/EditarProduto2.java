package pt.ipg.listadecompras;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditarProduto2 extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private Button botaoguardarproduto;
    private Button botaocancelar;
    private TextView txtViewVerData;
    private Button botaoescolherdata;
    Calendar calendario;
    DatePickerDialog datapiker;
    private Spinner spinnercategorias;
    private EditText edittextnomeproduto;
    private EditText edittextquantidadeproduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Spinner--------------------------------------------------------2
        spinnercategorias = (Spinner) findViewById(R.id.spinnerCategoria3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercategorias.setAdapter(adapter);
        spinnercategorias.setOnItemSelectedListener(this);
        //calendario-----------------------------4
        txtViewVerData= (TextView) findViewById(R.id.textViewDataSelecionada2);
        botaoescolherdata=(Button) findViewById(R.id.buttonEscolherDataQueFaltou2);
        botaoescolherdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario= Calendar.getInstance();
                int dia=calendario.get(Calendar.DAY_OF_MONTH);
                int mes= calendario.get(Calendar.MONTH);
                int ano = calendario.get(Calendar.YEAR);

                datapiker = new DatePickerDialog(EditarProduto2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                        txtViewVerData.setText(mDia + "-" + (mMes+1) + "-"+ mAno);
                    }
                }, dia, mes, ano);
                datapiker.show();
            }
        });
        //-----------------------------------------4
        botaoguardarproduto =(Button) findViewById(R.id.buttonGuardarProduto);
        botaocancelar= (Button) findViewById(R.id.buttonCancelar);
        edittextnomeproduto=(EditText) findViewById(R.id.EditTextNovonomeproduto);
        edittextquantidadeproduto=(EditText) findViewById(R.id.EditTextQuantidadeNova);


        botaoguardarproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edittextnomeproduto.getText().toString();
                String texto2= edittextquantidadeproduto.getText().toString();

                if (texto.trim().length() == 0) {
                    edittextnomeproduto.setError(getString(R.string.nome_obrigatorio_geral));
                    edittextnomeproduto.requestFocus();
                } else if (texto.length() <= 3) {
                    edittextnomeproduto.setError(getString(R.string.numero_minimo_de_caracters));
                    edittextnomeproduto.requestFocus();
                } else if (texto.length() >= 25) {
                    edittextnomeproduto.setError(getString(R.string.numero_maximo_de_caracters));
                    edittextnomeproduto.requestFocus();
                } else if (texto2.trim().length() == 0) {
                    edittextquantidadeproduto.setError(getString(R.string.quantidade_obrigatoria));
                    edittextquantidadeproduto.requestFocus();
                }else if (texto2.matches("0")) {
                    edittextquantidadeproduto.setError(getString(R.string.quantidade_invalida));
                    edittextquantidadeproduto.requestFocus();
                } else {
                    finish();
                    Toast.makeText(EditarProduto2.this, getString(R.string.produto_inserido_com_sucesso), Toast.LENGTH_SHORT).show();
                }
            }
        });
        botaocancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SimpleDateFormat formatadata= new SimpleDateFormat("dd-mm-yyyy");
        Date data =  new Date();
        String dataFormatada =  formatadata.format(data);

        txtViewVerData.setText(dataFormatada);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String texto = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //--------------------------------------------------------------------2



}


