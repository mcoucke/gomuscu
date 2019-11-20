package com.app.gomuscu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Repetition;
import com.app.gomuscu.entity.Seance;

@Database(entities = {Exercice.class, ExerciceDansHistorique.class, ExerciceDansSeance.class,
        Historique.class, Journee.class, Repetition.class, Seance.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class GoMuscuDatabase extends RoomDatabase {

    private static final String DB_NAME = "GoMuscuDatabase.db";
    private static volatile GoMuscuDatabase instance;

    static GoMuscuDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), GoMuscuDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract GoMuscuDao getGoMuscuDao();
}
