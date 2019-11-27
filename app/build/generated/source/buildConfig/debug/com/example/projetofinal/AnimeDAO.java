package com.example.projetofinal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AnimeDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AnimeDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Anime anime){
        ContentValues values = new ContentValues();
        values.put("personagem", anime.getPersonagem());
        values.put("anime", anime.getAnime());
        values.put("lancamento", anime.getLancamento());

        return banco.insert("produto", null, values);
    }
    public List<Anime> obterTodos(){
        List<Anime> animes = new ArrayList<>();
        Cursor cursor = banco.query("Anime", new String[]{"id", "personagem", "anime", "lancamento"}, null, null, null,
                null,null);
        while (cursor.moveToNext()){
            Anime anime = new Anime();
            anime.setId(cursor.getInt(0));
            anime.setPersonagem(cursor.getString(1));
            anime.setAnime(cursor.getString(2));
            anime.setLancamento(cursor.getString(3));
            animes.add(anime);
        }
        return animes;
    }

    public void excluir(Anime anime){
        banco.delete("Anime", "id = ?", new String[]{String.valueOf(anime.getId())});
    }

    public void atualizar(Anime anime){
        ContentValues values = new ContentValues();
        values.put("personagem", anime.getPersonagem());
        values.put("anime", anime.getAnime());
        values.put("lancamento", anime.getLancamento());
        banco.update("Anime", values, "id = ?", new String[]{String.valueOf(anime.getId())});

    }
}