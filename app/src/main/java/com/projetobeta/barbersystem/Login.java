package com.projetobeta.barbersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetobeta.barbersystem.general.FullScrean;

public class Login extends AppCompatActivity {
    private EditText cnpj_cpf;
    private EditText senha;
    private Button entrar;
    private Button cadastrar;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_login);
        iniciarObjetos();
        setCadastrar();
        setEntrar();
    }

    private void iniciarObjetos(){
        cnpj_cpf = (EditText) findViewById(R.id.cpf_cnpj);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.logar);
        cadastrar = (Button) findViewById(R.id.cadastrar);
    }

    private void setCadastrar(){
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registro.class));
                finish();
            }
        });
    }

    private void verificar(){
        if(cnpj_cpf.getText().length() == 0) {
            dialog.dismiss();
            cnpj_cpf.setError("Este campo precisa ser preenchido");
            cnpj_cpf.requestFocus();
        }else if(senha.getText().length() == 0){
            dialog.dismiss();
            senha.setError("Este campo precisa ser preenchido");
            senha.requestFocus();
        }else{
            logar();
        }
    }

    private void setEntrar(){
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Login.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                alerta.setView(layoutInflater.inflate(R.layout.activity_main,null));
                alerta.setCancelable(true);
                dialog = alerta.create();
                dialog.show();
                verificar();
            }
        });
    }

    private void logar(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Usuarios");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            boolean existe = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.child("cpf_cnpj").getValue().toString().equals(cnpj_cpf.getText().toString()) &&
                    ds.child("senha").getValue().toString().equals(senha.getText().toString())){
                        existe = true;
                        startActivity(new Intent(Login.this,Funcionalidades.class));
                        finish();
                        break;
                    }
                }
                if(!existe){
                    dialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Algo deu Errado!");
                    builder.setMessage("Verifique o CPF/CNPJ e Senha digitados e tente novamente!");
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog = builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}