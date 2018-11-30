package com.example.android.doctorrh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Symptom {
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symptom(int id, String name){
        this.id = id;
        this.name = name;
    }
}
