package com.example.uifirst;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputLayout;

public class NuevaNotaDialogFragment extends DialogFragment {

    private View view;
    private TextInputLayout tilTitulo,tilContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorita;
    private NuevaNotaDialogViewModel mViewModel;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton(R.string.guardarNota, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = tilTitulo.getEditText().getText().toString().trim();
                        String contenido = tilContenido.getEditText().getText().toString().trim();
                        String color = "azul";

                        switch (rgColor.getCheckedRadioButtonId()){
                            case R.id.btnVerde:
                                color= "verde"; break;
                            case R.id.btnRojo:
                                color= "rojo"; break;
                        }

                        boolean esFavorita = swNotaFavorita.isChecked();

                    }
                })
                .setNegativeButton(R.string.cancelarNota, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        //root:null => no devolvera al dialog se ejecutara en esta clase
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment,null);

        tilContenido = view.findViewById(R.id.tilContenido);
        tilTitulo = view.findViewById(R.id.tilTitulo);
        rgColor = view.findViewById(R.id.rgColor);
        swNotaFavorita = view.findViewById(R.id.btnFavorita);

        builder.setView(view);


        // Create the AlertDialog object and return it
        return builder.create();
    }



}
