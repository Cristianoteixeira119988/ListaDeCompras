package pt.ipg.listadecompras;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdaptadorListas extends RecyclerView.Adapter<AdaptadorListas.ViewHolderListas> {
    private Cursor cursor;
    private Context context;

    public AdaptadorListas(Context context) {
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
    public AdaptadorListas.ViewHolderListas onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLivro = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);

        return new ViewHolderListas(itemLivro);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListas.ViewHolderListas holderListas, int position) {
        cursor.moveToPosition(position);
        Listas listas = Listas.fromCursor(cursor);
        holderListas.setLista(listas);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;

        return cursor.getCount();
    }

    public class ViewHolderListas extends RecyclerView.ViewHolder {

        private TextView textViewNomeLista;


        private Listas lista;
        public ViewHolderListas(@NonNull View itemView) {
            super(itemView);
            textViewNomeLista = (TextView)itemView.findViewById(R.id.textViewNomeLista);

        }
        public void setLista(Listas lista){
            this.lista=lista;
            textViewNomeLista.setText(lista.getNomelista());
        }
    }
}



