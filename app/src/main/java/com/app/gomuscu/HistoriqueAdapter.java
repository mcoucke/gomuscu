package com.app.gomuscu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Historique;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.ViewHolder> {
    private List<Historique> data;
    private GoMuscuViewModel viewModel;

    public HistoriqueAdapter(List<Historique> data, GoMuscuViewModel viewModel) {
        this.data = data;
        this.viewModel = viewModel;
    }

    public void setData(List<Historique> historiquesSeances) {
        this.data = historiquesSeances;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.historique_seances_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIdHistorique(this.data.get(position).getId());
        holder.tvNom.setText(this.data.get(position).getNom());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = this.data.get(position).getDate();
        holder.tvDate.setText(df.format(date));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int idHistorique = -1;
        private TextView tvNom;
        private TextView tvDate;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.tvNom = view.findViewById(R.id.tv_historique_nom);
            this.tvDate = view.findViewById(R.id.tv_historique_date);
        }

        public void setIdHistorique(int id) {
            this.idHistorique = id;
        }

        @Override
        public void onClick(View v) {
            if(this.idHistorique != -1) {
                Intent intent = new Intent(v.getContext(), BilanSeance.class);
                intent.putExtra("id_historique", this.idHistorique);
                v.getContext().startActivity(intent);
            }
        }
    }
}
