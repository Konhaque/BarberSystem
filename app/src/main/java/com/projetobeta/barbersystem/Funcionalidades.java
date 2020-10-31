package com.projetobeta.barbersystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.projetobeta.barbersystem.funcionalidades.Tela_Principal;
import com.projetobeta.barbersystem.general.FullScrean;

public class Funcionalidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_funcionalidades);
        abre_Tela(new Tela_Principal());
    }

    private void abre_Tela(Fragment fragment){
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .replace(R.id.set_tela,fragment).commit();
    }

}