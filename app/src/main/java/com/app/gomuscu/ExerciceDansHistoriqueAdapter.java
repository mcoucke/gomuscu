package com.app.gomuscu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.Repetition;

import java.util.List;

public class ExerciceDansHistoriqueAdapter extends RecyclerView.Adapter<ExerciceDansHistoriqueAdapter.ViewHolder> {
    private List<ExerciceDansHistorique> data;
    private GoMuscuViewModel viewModel;
    private int id_historique;

    public ExerciceDansHistoriqueAdapter(List<ExerciceDansHistorique> data, GoMuscuViewModel viewModel, int id_historique) {
        this.data = data;
        this.viewModel = viewModel;
        this.id_historique = id_historique;
    }

    public void setData(List<ExerciceDansHistorique> exerciceDansHistoriqueList) {
        this.data = exerciceDansHistoriqueList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.bilan_exercices_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExerciceDansHistorique exerciceDansHistorique = viewModel.getExerciceDansHistoriqueById(
                this.id_historique, this.data.get(position).getIdExercice());
        Exercice exercice = viewModel.getExerciceById(exerciceDansHistorique.getIdExercice());
        holder.tvNomExercice.setText(exercice.getNom());
        Repetition repetition = viewModel.getRepetitionByIdExercice(exerciceDansHistorique.getId());
        String textNbRep = "/";
        String textPoids = "/";
        if(repetition.getNombre() != -1) {
            textNbRep = ""+repetition.getNombre();
        }
        if(repetition.getPoids() != -1.0) {
            textPoids = Float.toString(repetition.getPoids());
        }
        holder.tvNbRep.setText(textNbRep);
        holder.tvPoids.setText(textPoids);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNomExercice;
        TextView tvNbRep;
        TextView tvPoids;
        ImageView img;

        public ViewHolder(View view) {
            super(view);
            this.tvNomExercice = view.findViewById(R.id.tvBilanNomExo);
            this.tvNbRep = view.findViewById(R.id.tvBilanNbRepExercice);
            this.tvPoids = view.findViewById(R.id.tvBilanPoidsExercice);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
