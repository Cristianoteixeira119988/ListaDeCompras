package pt.ipg.listadecompras;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AdaptadorListas extends RecyclerView.Adapter<AdaptadorListas.ViewHolderListas> {
    private Cursor cursor;

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }


    @NonNull
    @Override
    public AdaptadorListas.ViewHolderListas onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListas.ViewHolderListas viewHolderListas, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderListas extends RecyclerView.ViewHolder {

        public ViewHolderListas(@NonNull View itemView) {
            super(itemView);
        }
    }
}



