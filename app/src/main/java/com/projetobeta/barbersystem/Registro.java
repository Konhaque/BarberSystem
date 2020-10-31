package com.projetobeta.barbersystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetobeta.barbersystem.bd.Usuarios;
import com.projetobeta.barbersystem.general.FullScrean;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Registro extends AppCompatActivity {
    private EditText cpf_cnpj;
    private EditText nome;
    private EditText nome_fantasia;
    private EditText cep;
    private EditText logradouro;
    private EditText cidade;
    private EditText estado;
    private EditText telefone;
    private EditText senha;
    private EditText confirmar_senha;
    private Button cadastrar;
    private AlertDialog dialog;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_registro);
        iniciar_Objetos();
        buscaCep();
        setCpf_cnpj();
        setCadastrar();
    }


    private void iniciar_Objetos(){
        cpf_cnpj = (EditText) findViewById(R.id.cpf_cnpj);
        nome = (EditText) findViewById(R.id.nome_razaoSocial);
        nome_fantasia = (EditText) findViewById(R.id.nome_fantasia);
        cep = (EditText) findViewById(R.id.cep);
        logradouro = (EditText) findViewById(R.id.logradouro);
        cidade = (EditText) findViewById(R.id.cidade);
        estado = (EditText) findViewById(R.id.estado);
        telefone = (EditText) findViewById(R.id.telefone);
        senha = (EditText) findViewById(R.id.senha);
        confirmar_senha = (EditText) findViewById(R.id.confirmar_senha);
        cadastrar = (Button) findViewById(R.id.cadastrar);
        usuarios = new Usuarios();
    }

    private void buscaCep(){
        cep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(cep.getText().length() == 8) new procurarCep().execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setCadastrar() {
    cadastrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            alerta.setView(layoutInflater.inflate(R.layout.activity_main,null));
            alerta.setCancelable(true);
            dialog = alerta.create();
            dialog.show();
            verifica();
        }
    });
    }

    private void verifica() {
        if(cpf_cnpj.getText().length() == 0) {
        dialog.dismiss();
        cpf_cnpj.setError("Este campo precisa ser preenchido");
        cpf_cnpj.requestFocus();
        }else if(nome.getText().length() == 0) {
        dialog.dismiss();
        nome.setError("Este campo precisa ser preenchido");
        nome.requestFocus();
        }else if(cpf_cnpj.getText().length() > 11 && nome_fantasia.getText().length() == 0) {
        dialog.dismiss();
        nome_fantasia.setError("Este campo precisa ser preenchido");
        nome_fantasia.requestFocus();
        }else if(cep.getText().length() == 0) {
        dialog.dismiss();
        cep.setError("Este campo precisa ser preenchido");
        cep.requestFocus();
        }else if(logradouro.getText().length() == 0) {
        dialog.dismiss();
        logradouro.setError("Este campo precisa ser preenchido");
        logradouro.requestFocus();
        }else if(cidade.getText().length() == 0) {
        dialog.dismiss();
        cidade.setError("Este campo precisa ser preenchido");
        cidade.requestFocus();
        }else if(estado.getText().length() == 0) {
        dialog.dismiss();
        estado.setError("Este campo precisa ser preenchido");
        estado.requestFocus();
        }else if(telefone.getText().length() == 0) {
        dialog.dismiss();
        telefone.setError("Este campo precisa ser preenchido");
        telefone.requestFocus();
        }else if(senha.getText().length() == 0) {
        dialog.dismiss();
        senha.setError("Este campo precisa ser preenchido");
        senha.requestFocus();
        }else if(!confirmar_senha.getText().toString().equals(senha.getText().toString())) {
         dialog.dismiss();
        confirmar_senha.setError("As senhas devem coincidir");
        confirmar_senha.requestFocus();
        }else {
        salvar();
        }
    }

    private void salvar() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Usuarios");
        String id = db.push().getKey();
        db.child(id).setValue(carregarUsuario()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(Registro.this,Funcionalidades.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("Algo deu Errado!");
                builder.setMessage("Verifique a sua conexão com a internet e tente novamente!");
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                dialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("Algo deu Errado!");
                builder.setMessage("Verifique a sua conexão com a internet e tente novamente!");
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

    }

    private Usuarios carregarUsuario() {
        usuarios.setCpf_cnpj(cpf_cnpj.getText().toString());
        usuarios.setNome(nome.getText().toString());
        usuarios.setNomeFantasia(nome_fantasia.getText().toString());
        usuarios.setCep(cep.getText().toString());
        usuarios.setLogradouro(logradouro.getText().toString());
        usuarios.setCidade(cidade.getText().toString());
        usuarios.setEstado(estado.getText().toString());
        usuarios.setSenha(senha.getText().toString());
        usuarios.setTelefone(telefone.getText().toString());
        return usuarios;
    }


    private void setCpf_cnpj() {
        cpf_cnpj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(cpf_cnpj.getText().length() == 11)  nome_fantasia.setEnabled(false);
                if(cpf_cnpj.getText().length() > 11) {
                    nome.setHint("Razão Social");
                    nome_fantasia.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private class procurarCep extends AsyncTask<Void,Void,Void>{
        private JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            alerta.setView(layoutInflater.inflate(R.layout.activity_main,null));
            alerta.setCancelable(true);
            dialog = alerta.create();
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cep();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            try{
                logradouro.setText(jsonObject.getString("logradouro")+" "+ jsonObject.getString("bairro"));
                estado.setText(jsonObject.getString("uf"));
                cidade.setText(jsonObject.getString("localidade"));
                logradouro.setEnabled(true);
                estado.setEnabled(false);
                cidade.setEnabled(false);
            }catch (JSONException e){
                e.getMessage();
            }
            super.onPostExecute(aVoid);
        }
        private void cep(){
            try{
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url("http://viacep.com.br/ws/"+cep.getText().toString()+"/json/")
                        .method("GET",null)
                        .build();
                Response response = client.newCall(request).execute();
                String as = response.body().string();
                try{
                    jsonObject = new JSONObject(as);
                }catch (JSONException e){
                    e.getMessage();
                }
            }catch (IOException e){
                e.getMessage();
            }
        }
    }
    }
