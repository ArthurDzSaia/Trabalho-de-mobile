package com.example.projetofinal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AnimeAdapter extends BaseAdapter {

    private List<Anime> animes;
    private Activity activity;

    public AnimeAdapter(Activity activity, List<Anime> animes) {
        this.activity = activity;
        this.animes = animes;
    }

    @Override
    public int getCount() {
        return animes.size();
    }

    @Override
    public Object getItem(int i) {
        return animes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return animes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup,false );
        TextView personagem = v.findViewById(R.id.txtPersonagem);
        TextView animeC = v.findViewById(R.id.txtAnime);
        TextView lancamento = v.findViewById(R.id.txtLancamento);
        Anime time = animes.get(i);
        personagem.setText(time.getPersonagem());
        animeC.setText(time.getAnime());
        lancamento.setText(time.getLancamento());

        return v;
    }
}
