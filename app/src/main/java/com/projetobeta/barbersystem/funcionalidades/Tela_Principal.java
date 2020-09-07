package com.projetobeta.barbersystem.funcionalidades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.projetobeta.barbersystem.R;

public class Tela_Principal extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tela_principal,container,false);
        return viewGroup;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
