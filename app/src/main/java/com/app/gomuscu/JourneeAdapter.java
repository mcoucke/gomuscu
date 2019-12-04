package com.app.gomuscu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JourneeAdapter extends RecyclerView.Adapter<JourneeAdapter.ViewHolder> {
    private List<Journee> data;
    private GoMuscuViewModel viewModel;

    public JourneeAdapter(List<Journee> data, GoMuscuViewModel viewModel) {
        this.data = data;
        this.viewModel = viewModel;

    }

    public void setData(List<Journee> journeeList) {
        this.data = journeeList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.editer_planning_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Seance seance = viewModel.getSeanceById(this.data.get(position).getIdSeance());
        holder.tv_journee_nom.setText(seance.getNom());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = this.data.get(position).getDate();
        holder.tv_journee_date.setText(df.format(date));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_journee_nom;
        private TextView tv_journee_date;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.tv_journee_nom = view.findViewById(R.id.tv_journee_nom);
            this.tv_journee_date = view.findViewById(R.id.tv_journee_date);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.tv_journee_nom.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
