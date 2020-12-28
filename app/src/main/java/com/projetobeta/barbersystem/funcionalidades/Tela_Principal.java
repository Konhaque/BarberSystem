package com.projetobeta.barbersystem.funcionalidades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.projetobeta.barbersystem.R;
import com.projetobeta.barbersystem.general.AbreTela;

public class Tela_Principal extends Fragment {
    private ImageView clientes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.tela_principal,container,false);
    }

    @Override
    public void onStart() {
        iniciarObjetos();
        setClientes();
        super.onStart();
    }


    private void iniciarObjetos(){
        clientes = (ImageView) getActivity().findViewById(R.id.clientes);
    }

    private void setClientes(){
        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AbreTela(getActivity().getSupportFragmentManager(),new Clientes());
            }
        });
    }


}
