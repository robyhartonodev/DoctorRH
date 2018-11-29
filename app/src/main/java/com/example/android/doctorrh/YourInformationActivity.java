package com.example.android.doctorrh;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mikhaellopez.circularimageview.CircularImageView;

public class YourInformationActivity extends AppCompatActivity {

    private CircularImageView maleCircularImageView;
    private CircularImageView femaleCircularImageView;

    private boolean isFemale;
    private boolean isMale;
    private int patientAge;

    private final String GENDER_FEMALE = "This is for female";
    private final String GENDER_MALE = "This is for male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_information);

        maleCircularImageView = findViewById(R.id.male_image);
        femaleCircularImageView = findViewById(R.id.female_image);

        // Restore State if App rotated or tabbed out
        if(savedInstanceState != null){
            isFemale = savedInstanceState.getBoolean(GENDER_FEMALE);
            isMale = savedInstanceState.getBoolean(GENDER_MALE);
        }

        maleCircularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMaleImageClick(view);
                Toast maleToast = Toast.makeText(view.getContext(), "Choosen Male", Toast.LENGTH_SHORT);
                maleToast.show();
            }
        });

        femaleCircularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFemaleImageClick(view);
                Toast femaleToast = Toast.makeText(view.getContext(), "Choosen Female", Toast.LENGTH_SHORT);
                femaleToast.show();
            }
        });

        if(isFemale){
            maleCircularImageView.setBorderColor(ContextCompat.getColor(this, R.color.border_image_deselected));
            femaleCircularImageView.setBorderColor(ContextCompat.getColor(this, R.color.border_image_selected));
        }

        if(isMale){
            maleCircularImageView.setBorderColor(ContextCompat.getColor(this, R.color.border_image_selected));
            femaleCircularImageView.setBorderColor(ContextCompat.getColor(this, R.color.border_image_deselected));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(GENDER_MALE,isMale);
        outState.putBoolean(GENDER_FEMALE,isFemale);
    }

    public void onMaleImageClick(View view){
        maleCircularImageView.setBorderColor(ContextCompat.getColor(view.getContext(), R.color.border_image_selected));
        femaleCircularImageView.setBorderColor(ContextCompat.getColor(view.getContext(), R.color.border_image_deselected));

        // Set boolean
        isMale = true;
        isFemale = false;
    }

    public void onFemaleImageClick(View view){
        maleCircularImageView.setBorderColor(ContextCompat.getColor(view.getContext(), R.color.border_image_deselected));
        femaleCircularImageView.setBorderColor(ContextCompat.getColor(view.getContext(), R.color.border_image_selected));

        // Set boolean
        isFemale = true;
        isMale = false;
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new AgeDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "Select your Birth date");
    }

}
