package com.app.gomuscu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Repetition;
import com.app.gomuscu.entity.Seance;

import java.util.concurrent.Executors;

@Database(entities = {Exercice.class, ExerciceDansHistorique.class, ExerciceDansSeance.class,
        Historique.class, Journee.class, Repetition.class, Seance.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class GoMuscuDatabase extends RoomDatabase {

    private static final String DB_NAME = "GoMuscuDatabase.db";
    private static volatile GoMuscuDatabase instance;

    static synchronized GoMuscuDatabase getInstance(final Context context) {
        if (instance == null) {

            RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
                @Override
                public void onCreate (SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            getInstance(context).getGoMuscuDao().insertExercices(Exercice.populateExercice());
                        }
                    });
                }

                public void onOpen(SupportSQLiteDatabase db) {

                }
            };

            instance = Room.databaseBuilder(context.getApplicationContext(), GoMuscuDatabase.class, DB_NAME)
                    .addCallback(rdc)
                    .build();
        }
        return instance;
    }

    public abstract GoMuscuDao getGoMuscuDao();
}
