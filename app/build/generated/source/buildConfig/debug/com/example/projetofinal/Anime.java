package com.example.projetofinal;

import java.io.Serializable;

public class Anime implements Serializable {

    private int id;
    private String personagem;
    private String anime;
    private String lancamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    @Override
    public String toString(){
        return personagem;
    }
}
