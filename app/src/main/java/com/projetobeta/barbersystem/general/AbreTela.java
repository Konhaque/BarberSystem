package com.projetobeta.barbersystem.general;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.projetobeta.barbersystem.R;

public class AbreTela {

    public AbreTela(@NonNull FragmentManager activity, Fragment fragment){
        activity.beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .replace(R.id.set_tela,fragment).commit();
    }

}
