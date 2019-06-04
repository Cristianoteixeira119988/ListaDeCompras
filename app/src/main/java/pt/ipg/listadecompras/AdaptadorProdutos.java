package pt.ipg.listadecompras;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdaptadorProdutos extends RecyclerView.Adapter<AdaptadorProdutos.ViewHolderProdutos> {
    private Cursor cursor;
    private Context context;

    public AdaptadorProdutos(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        if (this.cursor != cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public AdaptadorProdutos.ViewHolderProdutos onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemProduto = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);

        return new ViewHolderProdutos(itemProduto);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProdutos.ViewHolderProdutos holderProdutos, int position) {
        cursor.moveToPosition(position);
        Produtos produtos = Produtos.fromCursor(cursor);
        holderProdutos.setProdutos(produtos);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;

        return cursor.getCount();
    }

    public Produtos getProdutoSelecionado() {
        if (viewHolderProdutoSelecionado == null) return null;

        return viewHolderProdutoSelecionado.produtos;
    }
    private static ViewHolderProdutos viewHolderProdutoSelecionado = null;

    public class ViewHolderProdutos extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewNomeProduto;
        private TextView textViewNomeLista;
        private TextView textViewCategoria;
        private TextView textViewQuantidade;
        private TextView textViewDataFaltou;



        private Produtos produtos;
        public ViewHolderProdutos(@NonNull View itemView) {
            super(itemView);
            textViewCategoria = (TextView)itemView.findViewById(R.id.textViewCategoriaItemProduto);
            textViewNomeProduto = (TextView)itemView.findViewById(R.id.textViewNomeProdutoItemProduto);
            textViewNomeLista = (TextView)itemView.findViewById(R.id.textViewNomeListaItemProduto);
            textViewQuantidade = (TextView)itemView.findViewById(R.id.textViewQuantidadeItemProduto);
            textViewDataFaltou = (TextView)itemView.findViewById(R.id.textViewDataqueFaltouItemProduto);


            itemView.setOnClickListener(this);
        }
        public void setProdutos(Produtos produtos){
            this.produtos=produtos;
            textViewCategoria.setText(String.valueOf(produtos.getCategoria()));
            textViewNomeProduto.setText(produtos.getNomeproduto());
            textViewNomeLista.setText(produtos.getNomelista());
            textViewQuantidade.setText(String.valueOf(produtos.getQuantidade()));
            textViewDataFaltou.setText(String.valueOf(produtos.getDataqueacabou()));

        }

        @Override
        public void onClick(View v) {if (viewHolderProdutoSelecionado != null) {
            viewHolderProdutoSelecionado.desSeleciona();
        }

            viewHolderProdutoSelecionado = this;

            ((TodosProduto) context).atualizaOpcoesMenu();

            seleciona();
        }

        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }

        private void seleciona() {
            itemView.setBackgroundResource(R.color.colorItemSelecionado);
        }
    }
}
