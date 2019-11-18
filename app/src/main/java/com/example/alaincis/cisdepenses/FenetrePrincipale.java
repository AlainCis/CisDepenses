package com.example.alaincis.cisdepenses;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class FenetrePrincipale extends AppCompatActivity {

    BasedeDonnees mabdd;
    SimpleCursorAdapter moncurseuradapter;
    ListView listedepenses;
    FloatingActionButton nouvdep;
    Button today,ic_home;
    Calendar calendrier = Calendar.getInstance();
    int y = calendrier.get(Calendar.YEAR);
    int m = calendrier.get(Calendar.MONTH);
    int d = calendrier.get(Calendar.DAY_OF_MONTH);
    String aujourdhui = (y + "/" + m + "/" + d + "/"  );
    String critere1 = "urgence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_principale);
        mabdd = new BasedeDonnees(this);
        listedepenses = (ListView)findViewById(R.id.listedepenses);
        nouvdep = (FloatingActionButton) findViewById(R.id.nouvdep);
        today = (Button)findViewById(R.id.today);
        ic_home = (Button)findViewById(R.id.ic_home);
        affichageDonnees();

        nouvdep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FenetrePrincipale.this, NouvelleDepense.class);
                startActivity(i);
            }
        });
        listedepenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long itemclicked = listedepenses.getItemIdAtPosition(position);
                //Toast.makeText(FenetrePrincipale.this, "" + itemclicked, Toast.LENGTH_SHORT).show();
                ouvrirMiseajour(itemclicked.toString());
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setFocusableInTouchMode(true);
                affichageDonneesToday();
            }
        });
        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                affichageDonnees();
            }
        });
    }
    public void affichageDonneesToday(){
        Cursor curseur = mabdd.affichageDonneesToday(critere1);
        startManagingCursor(curseur);
        String dataDb[] = new String[]{BasedeDonnees.col_designation, BasedeDonnees.col_montant,
                BasedeDonnees.col_urgence, BasedeDonnees.col_delais, BasedeDonnees.col_realise};
        int dataView[] = new int[]{R.id.itdes, R.id.itmont, R.id.iturg, R.id.itdel, R.id.itrel};
        moncurseuradapter = new SimpleCursorAdapter(
                this,
                R.layout.itemliste,
                curseur,
                dataDb,
                dataView);
        listedepenses.setAdapter(moncurseuradapter);
    }
    public void affichageDonnees() {
        Cursor curseur = mabdd.affichageDonnees();
        startManagingCursor(curseur);
        String[] donneesbdd = new String[]{BasedeDonnees.col_designation, BasedeDonnees.col_montant,
                BasedeDonnees.col_urgence, BasedeDonnees.col_delais, BasedeDonnees.col_realise};
        int[] donneesitems = new int[]{R.id.itdes, R.id.itmont, R.id.iturg, R.id.itdel, R.id.itrel};
        moncurseuradapter = new SimpleCursorAdapter(
                this,
                R.layout.itemliste,
                curseur,
                donneesbdd,
                donneesitems
        );
        listedepenses.setAdapter(moncurseuradapter);
    }
    public void ouvrirMiseajour(String itemid){
        Intent miseajour = new Intent(this, MiseAjour.class);
        miseajour.putExtra("itemid",itemid);
        startActivity(miseajour);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem itemRecherche = menu.findItem(R.id.item_recherche);

        SearchView searchview = (SearchView) MenuItemCompat.getActionView(itemRecherche);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == R.id.aPropos){
            Intent apropos = new Intent(FenetrePrincipale.this, Apropos.class);
            startActivity(apropos);
        }
        return super.onOptionsItemSelected(item);
    }
}
