package com.app.gomuscu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

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
    public JourneeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.editer_planning_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(JourneeAdapter.ViewHolder holder, int position) {
        Seance seance = viewModel.getSeanceById(this.data.get(position).getIdSeance());
        holder.textView.setText(this.data.get(position).getDate() + " : " + seance.getNom());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.tvJournee);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
