package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public final int NB_JOURS = 4;

    private List<Journee> listeJournees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Liste d'ImageView permettant d'accéder à l'image correspondant a un indice donné
        List<ImageView> listeImages = new ArrayList<>();
        listeImages.add((ImageView)findViewById(R.id.img_day1));
        listeImages.add((ImageView)findViewById(R.id.img_day2));
        listeImages.add((ImageView)findViewById(R.id.img_day3));
        listeImages.add((ImageView)findViewById(R.id.img_day4));

        //Liste de TextView permettant de définir la date de chaque jour
        List<TextView> listeTextes = new ArrayList<>();
        listeTextes.add((TextView)findViewById(R.id.text_jour1));
        listeTextes.add((TextView)findViewById(R.id.text_jour2));
        listeTextes.add((TextView)findViewById(R.id.text_jour3));
        listeTextes.add((TextView)findViewById(R.id.text_jour4));

        // Récupération du ViewModel pour interroger la base
        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        Seance sampleSeance = new Seance("SampleSeance");
        long idSampleSeance = goMuscuViewModel.insertSeance(sampleSeance);

        Calendar sampleCalendar = Calendar.getInstance();
        sampleCalendar.set(Calendar.HOUR_OF_DAY, 0);
        sampleCalendar.set(Calendar.MINUTE, 0);
        sampleCalendar.set(Calendar.SECOND, 0);
        sampleCalendar.set(Calendar.MILLISECOND, 0);
        sampleCalendar.add(Calendar.DAY_OF_YEAR, 3);
        Date dateSample = sampleCalendar.getTime();
        Journee sampleJournee = new Journee(dateSample, (int)idSampleSeance);

        goMuscuViewModel.insertJournee(sampleJournee);

        System.out.println("TEST SAMPLE DATE SEANCE SHOULD : " + dateSample + " IS : " + goMuscuViewModel.getJourneeByDate(dateSample).getDate());

        // Remplissage du planning avec séances si existantes
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date dateFor = cal.getTime();
        // Insert dans listeJournees les objets Journee correspondants à aujourd'hui
        // et aux 3 prochains jours (null si pas de Journee), définition des dates du planning
        String dateAffichee;
        for (int i = 0 ; i < this.NB_JOURS; i++) {
            System.out.println("DATE LOOP : "+dateFor);
            // Jour écrit en français si l'appareil est réglé en fr, anglais sinon
            if(Locale.getDefault().getLanguage().equals("fr")) {
                dateAffichee = new SimpleDateFormat("EE", Locale.FRANCE).format(dateFor.getTime()) + "\n";
            } else {
                dateAffichee = new SimpleDateFormat("EE", Locale.ENGLISH).format(dateFor.getTime()) + "\n";
            }
            // On ajoute la date du jour
            dateAffichee += new SimpleDateFormat("dd/MM").format(dateFor.getTime());
            listeTextes.get(i).setText(dateAffichee);
            Journee journeeFor = goMuscuViewModel.getJourneeByDate(dateFor);
            System.out.println("JOURNEE FOR : " + journeeFor);
            this.listeJournees.add(journeeFor);
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateFor = cal.getTime();
        }

        // Définition des images du planning en fonction de la bdd
        for (int i = 0; i < this.NB_JOURS; i++) {
            System.out.println("TEST LOOP"+i+" : " + this.listeJournees.get(i));
            if(this.listeJournees.get(i) != null) {
                listeImages.get(i).setImageResource(R.drawable.work_day);
            } else {
                listeImages.get(i).setImageResource(R.drawable.rest_day);
            }
        }


//        goMuscuViewModel.getAllExercicesDansSeancesById(testGetJournee.getIdSeance()).observe(this, new Observer<List<ExerciceDansSeance>>() {
//            @Override
//            public void onChanged(List<ExerciceDansSeance> exercices) {
//                afficherExos(exercices);
//            }
//        });

    }

//    public void afficherExos(List<ExerciceDansSeance> exercices) {
//        for (int i = 0; i < exercices.size(); i++) {
//            System.out.println(exercices.get(i));
//        }
//    }

    public void onImageClick(View view) {
        System.out.println("click image planning");
        switch (view.getId()) {
            case R.id.img_day1 :
                if (this.listeJournees.get(0) != null) {
                    // todo
                }
                break;
            case R.id.img_day2:
                if (this.listeJournees.get(1) != null) {
                    // todo
                }
                break;
            case R.id.img_day3:
                if (this.listeJournees.get(2) != null) {
                    // todo
                }
                break;
            case R.id.img_day4:
                if (this.listeJournees.get(3) != null) {
                    // todo
                }
                break;
        }
    }

    public void onClickEditPlanning(View view) {
        System.out.println("click edit planning");
    }

    public void onClickEditSeances(View view) {
        Intent intent = new Intent(this, EditerSeance.class);
        startActivity(intent);
    }

    public void onClickViewHistorique(View view) {
        System.out.println("click edit planning");
    }
}
