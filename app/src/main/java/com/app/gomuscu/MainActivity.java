package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
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
    private List<ImageView> listeImages = new ArrayList<>();
    private List<TextView> listeTextes = new ArrayList<>();
    private GoMuscuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Liste d'ImageView permettant d'accéder à l'image correspondant a un indice donné
        this.listeImages.add((ImageView)findViewById(R.id.img_day1));
        this.listeImages.add((ImageView)findViewById(R.id.img_day2));
        this.listeImages.add((ImageView)findViewById(R.id.img_day3));
        this.listeImages.add((ImageView)findViewById(R.id.img_day4));

        //Liste de TextView permettant de définir la date de chaque jour
        this.listeTextes.add((TextView)findViewById(R.id.text_jour1));
        this.listeTextes.add((TextView)findViewById(R.id.text_jour2));
        this.listeTextes.add((TextView)findViewById(R.id.text_jour3));
        this.listeTextes.add((TextView)findViewById(R.id.text_jour4));

        // Récupération du ViewModel pour interroger la base
        this.viewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        /**
         * SAMPLE
         */
        Calendar cal = Calendar.getInstance();
        // Les dates insérées doivent impérativement être définies sans heure/minutes/...
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateFor = cal.getTime();

        if(this.viewModel.getSeanceById(1) == null) {
            Seance s = new Seance("Test");
            long id_seance = this.viewModel.insertSeance(s);
            for(int i = 1; i < 7; i++) {
                ExerciceDansSeance ex = new ExerciceDansSeance((int) id_seance, i);
                this.viewModel.insertExerciceDansSeance(ex);
            }
            Journee j = new Journee(dateFor, (int) id_seance);
            this.viewModel.insertJournee(j);
        }
        /**
         * END SAMPLE
         */

        // Statistiques

        this.viewModel.getAllHistoriques().observe(this, new Observer<List<Historique>>() {
            @Override
            public void onChanged(List<Historique> historiques) {
                setStatistiquesHistoriques(historiques);
            }
        });

        this.viewModel.getExerciceDansHistoriqueCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer nbExercices) {
                TextView tv_total_exos = (TextView) findViewById(R.id.tv_total_exos);
                tv_total_exos.setText(String.valueOf(nbExercices));
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        buildPlanning();
    }

    /**
     * Génère le planning à partir de la base de données
     */
    public void buildPlanning() {
        Calendar cal = Calendar.getInstance();
        // Les dates insérées doivent impérativement être définies sans heure/minutes/...
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateFor = cal.getTime();
        this.listeJournees = new ArrayList<>();
        // Insert dans listeJournees les objets Journee correspondants à aujourd'hui
        // et aux 3 prochains jours (null si pas de Journee), définition des dates du planning
        String dateAffichee;
        for (int i = 0 ; i < this.NB_JOURS; i++) {
            // Jour écrit en français si l'appareil est réglé en fr, anglais sinon
            if(Locale.getDefault().getLanguage().equals("fr")) {
                dateAffichee = new SimpleDateFormat("EE", Locale.FRANCE).format(dateFor.getTime()) + "\n";
            } else {
                dateAffichee = new SimpleDateFormat("EE", Locale.ENGLISH).format(dateFor.getTime()) + "\n";
            }
            // On ajoute la date du jour
            dateAffichee += new SimpleDateFormat("dd/MM").format(dateFor.getTime());
            listeTextes.get(i).setText(dateAffichee);
            // On insère la journée dans la liste
            this.listeJournees.add(this.viewModel.getJourneeByDate(dateFor));
            // Date suivante
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

        // Bouton démarrage de séance
        Button boutonLancerSeance = (Button) findViewById(R.id.btn_demarrer_seance);
        // Si il y a une séance aujourd'hui on active le bouton
        if (this.listeJournees.get(0) != null) {
            boutonLancerSeance.setEnabled(true);
        } else {
            boutonLancerSeance.setEnabled(false);
        }
    }

    public void onClickImage(View view) {
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
        Intent intent = new Intent(this, EditerPlanning.class);
        startActivity(intent);
    }

    public void onClickEditSeances(View view) {
        Intent intent = new Intent(this, EditerSeance.class);
        startActivity(intent);
    }

    public void onClickViewHistorique(View view) {
        Intent intent = new Intent(this, HistoriqueSeances.class);
        startActivity(intent);
    }

    public void onCLickDemarrerSeance(View view) {
        Intent intent = new Intent(this, DemarrerSeance.class);
        // On passe l'id de la séance du jour à l'activity
        if(this.listeJournees.get(0) != null) {
            intent.putExtra("id_seance", this.listeJournees.get(0).getIdSeance());
        }
        startActivity(intent);
    }

    public void setStatistiquesHistoriques(List<Historique> historiques) {
        int total_duree_sec = 0;
        for(int i = 0; i < historiques.size(); i++) {
            total_duree_sec += historiques.get(i).getDureeSecondes();
        }
        int total_duree_min = 0;
        int duree_min_moy = 0;
        if (total_duree_sec != 0) {
            total_duree_min = total_duree_sec/60;
            duree_min_moy = (total_duree_sec / historiques.size())/60;
        }
        int nb_seances = historiques.size();

        TextView tv_total_sec = (TextView) findViewById(R.id.tv_total_sec);
        tv_total_sec.setText(total_duree_min + "min");
        TextView tv_total_seances = (TextView) findViewById(R.id.tv_total_seances);
        tv_total_seances.setText(String.valueOf(nb_seances));
        TextView tv_duree_moyenne = (TextView) findViewById(R.id.tv_duree_moyenne);
        tv_duree_moyenne.setText(duree_min_moy + "min");
    }
}
