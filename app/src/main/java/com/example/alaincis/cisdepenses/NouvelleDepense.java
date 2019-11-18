package com.example.alaincis.cisdepenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NouvelleDepense extends AppCompatActivity {

    private static final int BOITE_DIALOG = 0;
    int annee, mois, jour;
    EditText et_des, et_mont, et_del;
    Spinner et_urg;
    Button ajouter,btn_choisir_date;
    BasedeDonnees mabdd;
    private DatePickerDialog.OnDateSetListener datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Initialistion des vues*/
        setContentView(R.layout.activity_nouvelle_depense);
        et_des = (EditText)findViewById(R.id.et_des);
        et_mont = (EditText)findViewById(R.id.et_mont);
        et_urg = (Spinner)findViewById(R.id.et_urg);
        et_del = (EditText)findViewById(R.id.et_del);
        ajouter = (Button)findViewById(R.id.ajouter);
        final String rel;
        rel="NON";
        mabdd = new BasedeDonnees(this);
        /*Fin Initialisation*/
        ArrayAdapter<CharSequence>  monspinner = ArrayAdapter.createFromResource(this, R.array.urgence_degree, android.R.layout.simple_spinner_item);
        monspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_urg.setAdapter(monspinner);
        et_urg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String texte = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), texte, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        et_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendrier = Calendar.getInstance();
                annee = calendrier.get(Calendar.YEAR);
                mois = calendrier.get(Calendar.MONTH);
                jour = calendrier.get(Calendar.DAY_OF_MONTH);
                mois = mois+1;
                DatePickerDialog dialog = new DatePickerDialog(NouvelleDepense.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datePickerDialog, annee, mois, jour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dateChoisit = (year + "/" + month + "/" + dayOfMonth + "/"  );
                et_del.setText(dateChoisit);
            }
        };
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        /*Fin initialistion des vues*/
        /*Casting des vues en strings*/
                String desstr = et_des.getText().toString();
                String montstr = et_mont.getText().toString();
                String urgstr = et_urg.getSelectedItem().toString();
                String delstr = et_del.getText().toString();
                String relstr = rel.toString();

                if (desstr!="" && montstr!="" && urgstr!="" && delstr!="" && relstr!=""){
                            /*Fin casting des vues en strings*/
        /*Création dun objet de type depense et insertion dans la bdd*/
                    depense d = new depense(desstr, montstr, urgstr, delstr, relstr);
                    d.setDes(desstr);
                    d.setMont(montstr);
                    d.setUrg(urgstr);
                    d.setDel(delstr);
                    d.setRel(relstr);
                    mabdd.insertionDesDonnees(d);
                    Toast.makeText(NouvelleDepense.this, "Insertion réaliseée avec succès !", Toast.LENGTH_SHORT).show();
        /*Fin création dun objet de type depense et insertion dans la bdd*/
                }else{
                    Toast.makeText(NouvelleDepense.this, "Veuillez compléter les champs sont vides !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
