package com.example.android.doctorrh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.doctorrh.R;
import com.example.android.doctorrh.model.Symptom;

import java.util.ArrayList;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder> {
    private ArrayList<Symptom> dataList;

    public SymptomAdapter(ArrayList<Symptom> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SymptomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.symptom_list_item, viewGroup, false);
        return new SymptomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomViewHolder symptomViewHolder, int position) {
        symptomViewHolder.symptomTitle.setText(dataList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class SymptomViewHolder extends RecyclerView.ViewHolder {

        TextView symptomTitle;

        public SymptomViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomTitle = itemView.findViewById(R.id.symptom_name);
        }
    }
}
