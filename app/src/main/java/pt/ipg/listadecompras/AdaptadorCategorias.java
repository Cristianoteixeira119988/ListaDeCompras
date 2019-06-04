package pt.ipg.listadecompras;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdaptadorCategorias extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolderCategorias> {
    private Cursor cursor;
    private Context context;

    public AdaptadorCategorias(Context context) {
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
    public AdaptadorCategorias.ViewHolderCategorias onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemCategoria = LayoutInflater.from(context).inflate(R.layout.item_categoria, parent, false);

        return new ViewHolderCategorias(itemCategoria);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCategorias.ViewHolderCategorias holderCategorias, int position) {
        cursor.moveToPosition(position);
        Categoria categorias = Categoria.fromCursor(cursor);
        holderCategorias.setCategoria(categorias);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;

        return cursor.getCount();
    }

    public Categoria getCategoriaSelecionada() {
        if (viewHolderCategoriaSelecionada == null) return null;

        return viewHolderCategoriaSelecionada.categoria;
    }
    private static ViewHolderCategorias viewHolderCategoriaSelecionada = null;

    public class ViewHolderCategorias extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewCategoria;


        private Categoria categoria;
        public ViewHolderCategorias(@NonNull View itemView) {
            super(itemView);
            textViewCategoria = (TextView)itemView.findViewById(R.id.textViewItemCategoria);


            itemView.setOnClickListener(this);
        }
        public void setCategoria(Categoria categoria){
            this.categoria=categoria;
            textViewCategoria.setText(categoria.getDescricao());

        }

        @Override
        public void onClick(View v) {if (viewHolderCategoriaSelecionada != null) {
            viewHolderCategoriaSelecionada.desSeleciona();
        }

            viewHolderCategoriaSelecionada = this;

            ((Categorias) context).atualizaOpcoesMenu();

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

