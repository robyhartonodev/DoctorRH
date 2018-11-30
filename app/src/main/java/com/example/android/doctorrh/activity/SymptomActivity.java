package com.example.android.doctorrh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.doctorrh.R;
import com.example.android.doctorrh.adapter.SymptomAdapter;
import com.example.android.doctorrh.model.Symptom;
import com.example.android.doctorrh.model.SymptomList;
import com.example.android.doctorrh.my_interface.GetSymptomDataService;
import com.example.android.doctorrh.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymptomActivity extends AppCompatActivity {

    private SymptomAdapter mAdapter;
    private RecyclerView recyclerView;

    private String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InJvYnloYXJ0b25vZGV2MzEyQGdtYWlsLmNvbSIsInJvbGUiOiJVc2VyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc2lkIjoiNDI4MCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6IjIwMCIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGltaXQiOiI5OTk5OTk5OTkiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXAiOiJQcmVtaXVtIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9sYW5ndWFnZSI6ImVuLWdiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjA5OS0xMi0zMSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcHN0YXJ0IjoiMjAxOC0xMS0yNSIsImlzcyI6Imh0dHBzOi8vc2FuZGJveC1hdXRoc2VydmljZS5wcmlhaWQuY2giLCJhdWQiOiJodHRwczovL2hlYWx0aHNlcnZpY2UucHJpYWlkLmNoIiwiZXhwIjoxNTQzNjA1NjExLCJuYmYiOjE1NDM1OTg0MTF9.AbxWmQ5bT0kG1kWe8c9lyFQZOB3FOa89ReMUKPVEr-o";
    //private String LANGUAGE_PARAMETER = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);

        /** Create handle for the RetrofitInstance interface*/
        GetSymptomDataService service = RetrofitInstance.
                getRetrofitInstance().create(GetSymptomDataService.class);


        /** Call the method with parameter in the interface to get the symptom data*/
        Call<ArrayList<Symptom>> call = service.getSymptomData();

        /** Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ArrayList<Symptom>>() {
            @Override
            public void onResponse(Call<ArrayList<Symptom>> call, Response<ArrayList<Symptom>> response) {
                generateSymptomList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Symptom>> call, Throwable t) {
                Toast.makeText(SymptomActivity.this, "Something went wrong...Error message: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateSymptomList(ArrayList<Symptom> symptomArrayList) {
        recyclerView = findViewById(R.id.recycler_view_symptom_list);
        mAdapter = new SymptomAdapter(symptomArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SymptomActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
