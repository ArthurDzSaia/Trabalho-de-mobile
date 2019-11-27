package com.example.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroAnimes extends AppCompatActivity {

    private EditText personagem;
    private EditText nomeAnime;
    private EditText lancamento;
    private AnimeDao dao;
    private Anime anime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animes);

        personagem = findViewById(R.id.edtNomePersonagem);
        nomeAnime = findViewById(R.id.edtNomeAnime);
        lancamento = findViewById(R.id.edtLancamento);
        dao = new AnimeDao(this);

        Intent it = getIntent();
        if (it.hasExtra("Anime")){
            anime = (Anime) it.getSerializableExtra("Anime");
            personagem.setText(anime.getNomePersonagem());
            nomeAnime.setText(anime.getNomeAnime());
            lancamento.setText(anime.getLancamento());
        }

    }

    public void salvar(View view){

        if (anime == null) {
            anime = new Anime();
            anime.setNomePersonagem(personagem.getText().toString());
            anime.setNomeAnime(nomeAnime.getText().toString());
            anime.setLancamento(lancamento.getText().toString());

            long id = dao.inserir(anime);

            Toast.makeText(this, "Anime inserido com id: " + id, Toast.LENGTH_SHORT).show();
        }else {
            anime.setNomePersonagem(personagem.getText().toString());
            anime.setNomeAnime(nomeAnime.getText().toString());
            anime.setLancamento(lancamento.getText().toString());
            dao.alterar(anime);
            Toast.makeText(this, "O anime foi alterado", Toast.LENGTH_SHORT).show();

        }
    }
}
