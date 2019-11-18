package com.example.alaincis.cisdepenses;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MiseAjour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mise_ajour);
        final BasedeDonnees mabase;
        mabase = new BasedeDonnees(this);
        TextView idb =  (TextView) findViewById(R.id.idb);
        final EditText majdesignation = (EditText)findViewById(R.id.majdesignation);
        final EditText majmontant = (EditText)findViewById(R.id.majmontant);
        final EditText majurgence = (EditText)findViewById(R.id.majurgence);
        final EditText majdelais = (EditText)findViewById(R.id.majdelais);
        final EditText majrel = (EditText)findViewById(R.id.majrel);
        final Button modifier = (Button)findViewById(R.id.modifier);
        final Button supprimer = (Button)findViewById(R.id.supprimer);
        Intent miseAjour = getIntent();
        final String id =miseAjour.getStringExtra("itemid");
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        idb.setText(id);
        String a,b,c,d,e;
        Cursor curseur = mabase.affichageEnregistrementParId(id);
        if (curseur.moveToNext()){
            a = curseur.getString(1);
            b = curseur.getString(2);
            c = curseur.getString(3);
            d = curseur.getString(4);
            e = curseur.getString(5);
            majdesignation.setText(a);
            majmontant.setText(b);
            majurgence.setText(c);
            majdelais.setText(d);
            majrel.setText(e);
        }
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des_str = majdesignation.getText().toString();
                String mont_str = majmontant.getText().toString();
                String urg_str = majurgence.getText().toString();
                String del_str = majdelais.getText().toString();
                String rel_str = majrel.getText().toString();
                //String rel_str = rel.toString();
                //Toast.makeText(MiseAjour.this, "It's work !!"+ des_str  , Toast.LENGTH_SHORT).show();
                depense d = new depense(des_str, mont_str, urg_str, del_str, rel_str);
                d.setDes(des_str);
                d.setMont(mont_str);
                d.setUrg(urg_str);
                d.setDel(del_str);
                d.setRel(rel_str);
                mabase.miseajourDonnees(id, d);
                Toast.makeText(MiseAjour.this, "Dépense actualisée avec succès", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
