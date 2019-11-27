package com.example.projetofinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarAnimes extends AppCompatActivity {
    private ListView listView;
    private AnimeDAO dao;
    private List<Anime> animes;
    private List<Anime> animesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_animes);

        listView = findViewById(R.id.lstAnimes);
        dao = new AnimeDAO(this);
        animes = dao.obterTodos();
        animesFiltrados.addAll(animes);

       // ArrayAdapter<Livro> adaptador = new ArrayAdapter<Livro>(this, android.R.layout.simple_list_item_1, livrosFiltrados);
        AnimeAdapter adaptador = new AnimeAdapter(this, animesFiltrados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraAnime(s);
                return false;
            }
        });
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraAnime(String personagem){
        animesFiltrados.clear();
        for(Anime anime : animes){
            if(anime.getPersonagem().toLowerCase().contains(personagem.toLowerCase())){
                animesFiltrados.add(anime);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Anime animeExcluir = animesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção").setMessage("Deseja realmente excluir esse anime?").setNegativeButton("Não", null).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                animesFiltrados.remove(animeExcluir);
                animes.remove(animeExcluir);
                dao.excluir(animeExcluir);
                listView.invalidateViews();
            }
        }).create();
        dialog.show();

    }

    public void alterar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Anime animeAlterar = animesFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroAnimes.class);
        it.putExtra("Anime", animeAlterar);
        startActivity(it);
    }


    public void cadastrar(MenuItem item){

        Intent it = new Intent(this, CadastroAnimes.class);
        startActivity(it);
    }

    public void onResume(){
        super.onResume();
        animes = dao.obterTodos();
        animesFiltrados.clear();
        animesFiltrados.addAll(animes);
        listView.invalidateViews();
    }

}
