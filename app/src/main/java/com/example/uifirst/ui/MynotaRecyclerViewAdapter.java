package com.example.uifirst.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.uifirst.NuevaNotaDialogViewModel;
import com.example.uifirst.R;
import com.example.uifirst.db.entity.NotaEntity;

import java.util.List;


public class MynotaRecyclerViewAdapter extends RecyclerView.Adapter<MynotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private  Context context; //metodos para interactuar con la nota
    private NuevaNotaDialogViewModel nuevaNotaDialogViewModel;

    public MynotaRecyclerViewAdapter(List<NotaEntity> items, Context context) {
        mValues = items;
        this.context = context;
        nuevaNotaDialogViewModel= ViewModelProviders.
                of((AppCompatActivity)context). //instancia del activity donde trabajamos
                get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    //se invoca cada vez x cada elemento de la lista de notas // esto lo grafica
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvtitulo.setText(holder.mItem.getTitulo());
        holder.tvcontenido.setText(holder.mItem.getContenido());

        if(holder.mItem.isFavorita()){
            holder.ivfavorita.setImageResource(R.drawable.ic_star_black_24dp);
        }else{
            holder.ivfavorita.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        holder.ivfavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mItem.isFavorita()){
                    holder.mItem.setFavorita(false);
                    holder.ivfavorita.setImageResource(R.drawable.ic_star_border_black_24dp);
                }else{
                    holder.mItem.setFavorita(true);
                    holder.ivfavorita.setImageResource(R.drawable.ic_star_black_24dp);
                }

                //update dato viewModel -> repositorio -> dao -> update en la DB
                nuevaNotaDialogViewModel.updateNota(holder.mItem);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //metodo para update of changes
    public void setNuevasNotas(List<NotaEntity> nuevasNotas){
        this.mValues = nuevasNotas;
        //update the adapter
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvtitulo;
        public final TextView tvcontenido;
        public final ImageView ivfavorita;
        public final CardView card;
        public NotaEntity mItem;

        //referencia los elementos del diseño
        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvtitulo =  view.findViewById(R.id.textViewTitulo);
            tvcontenido = view.findViewById(R.id.textViewContenido);
            ivfavorita =  view.findViewById(R.id.imageViewFavorita);
            card =  view.findViewById(R.id.idCard);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvtitulo.getText() + "'";
        }
    }
}
