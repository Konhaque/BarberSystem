package com.projetobeta.barbersystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.projetobeta.barbersystem.funcionalidades.Tela_Principal;
import com.projetobeta.barbersystem.general.AbreTela;
import com.projetobeta.barbersystem.general.FullScrean;

public class Funcionalidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_funcionalidades);
        new AbreTela(getSupportFragmentManager(),new Tela_Principal());
    }



}