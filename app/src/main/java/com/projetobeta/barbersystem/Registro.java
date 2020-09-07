package com.projetobeta.barbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.projetobeta.barbersystem.general.FullScrean;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_registro);
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
    }
}