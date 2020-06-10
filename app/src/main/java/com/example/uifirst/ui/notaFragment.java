package com.example.uifirst.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.uifirst.NuevaNotaDialogFragment;
import com.example.uifirst.NuevaNotaDialogViewModel;
import com.example.uifirst.R;
import com.example.uifirst.db.entity.NotaEntity;
import com.example.uifirst.ui.MynotaRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class notaFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NotaEntity> notaEntityList;
    private MynotaRecyclerViewAdapter mynotaRecyclerViewAdapter;
    private Context ctx;
    private NuevaNotaDialogViewModel nota;

    //private NotasInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public notaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listLandscape) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {

                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                //caracteristcas de la pantalla
                //pixeles de pantalla

                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numColum = (int)dpWidth / 180;
                //cada column ocupa 180 pixeles


                /*diferencia entre alturas*/
                recyclerView.setLayoutManager(
                        new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
            }
            notaEntityList = new ArrayList<>();
            mynotaRecyclerViewAdapter =new MynotaRecyclerViewAdapter(notaEntityList,getActivity());
            recyclerView.setAdapter(mynotaRecyclerViewAdapter);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        //obtner la misma instancia del viewModel
        nota = ViewModelProviders.of(getActivity())
                .get(NuevaNotaDialogViewModel.class);
        // observe => mantiene esperando los cambios
        nota.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                // se actuliza el adapter
                mynotaRecyclerViewAdapter.setNuevasNotas(notaEntities);
            }
        });
    }



    /*
    //ya e no implementamos la interfaz en la actividad eso no se usara
    //segun jectpack

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // en esta comprobacion nos aseguramos en la activity implemente la interfaz @NotasInteractionListener
        if (context instanceof NotasInteractionListener) {
            mListener = (NotasInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NotasInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

}
