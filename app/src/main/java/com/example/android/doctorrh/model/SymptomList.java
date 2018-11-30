package com.example.android.doctorrh.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SymptomList {
    private ArrayList<Symptom> symptomList;

    public ArrayList<Symptom> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(ArrayList<Symptom> symptomList) {
        this.symptomList = symptomList;
    }
}
