package com.example.android.doctorrh;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

public class YourInformationActivity extends AppCompatActivity {

    private ImageView maleImageView;
    private ImageView femaleImageView;
    private TextView ageTextView;

    private boolean genderChoiceSelected = false;
    private boolean userSetAge = false;
    private boolean isFemale;
    private boolean isMale;
    private int userAge;

    private final String GENDER_FEMALE = "This is for female";
    private final String GENDER_MALE = "This is for male";
    private final String AGE_COUNT = "This is for age";
    private final String GENDER_CHOICE_SELECTED = "This is to check if user has choosen their gender";
    private final String USER_SET_AGE = "This is to check if user has set their age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_information);

        maleImageView = findViewById(R.id.male_image);
        femaleImageView = findViewById(R.id.female_image);
        ageTextView = findViewById(R.id.age_count_text);

        // Restore State if App rotated or tabbed out
        if (savedInstanceState != null) {
            // Retrieve and set the value from savedInstanceState
            isFemale = savedInstanceState.getBoolean(GENDER_FEMALE);
            isMale = savedInstanceState.getBoolean(GENDER_MALE);
            userAge = savedInstanceState.getInt(AGE_COUNT);
            genderChoiceSelected = savedInstanceState.getBoolean(GENDER_CHOICE_SELECTED);
            userSetAge = savedInstanceState.getBoolean(USER_SET_AGE);

            // Set Age TextView to visible and set the Text
            ageTextView.setVisibility(View.VISIBLE);
            ageTextView.setText(String.valueOf(userAge));
        }

        maleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMaleImageClick(view);
                Toast maleToast = Toast.makeText(view.getContext(), "Choosen Male", Toast.LENGTH_SHORT);
                maleToast.show();
            }
        });

        femaleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFemaleImageClick(view);
                Toast femaleToast = Toast.makeText(view.getContext(), "Choosen Female", Toast.LENGTH_SHORT);
                femaleToast.show();
            }
        });

        if (isFemale) {
            maleImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.border_image_deselected));
            femaleImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor));
        }

        if (isMale) {
            maleImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor));
            femaleImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.border_image_deselected));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(GENDER_MALE, isMale);
        outState.putBoolean(GENDER_FEMALE, isFemale);
        outState.putBoolean(GENDER_CHOICE_SELECTED, genderChoiceSelected);
        outState.putBoolean(USER_SET_AGE, userSetAge);
        outState.putInt(AGE_COUNT, userAge);
    }

    public void onMaleImageClick(View view) {
        maleImageView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.secondaryLightColor));
        femaleImageView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.border_image_deselected));

        // Set boolean
        isMale = true;
        isFemale = false;

        // Set genderChoiceSelected
        genderChoiceSelected = true;
    }

    public void onFemaleImageClick(View view) {
        maleImageView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.border_image_deselected));
        femaleImageView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.secondaryLightColor));

        // Set boolean
        isFemale = true;
        isMale = false;

        // Set genderChoiceSelected
        genderChoiceSelected = true;
    }

    public void showAgeAlertDialog(View view) {
        final Context mContext = view.getContext();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Your Age");
        final EditText inputAge = new EditText(this);
        inputAge.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputAge.setRawInputType(Configuration.KEYBOARD_12KEY);
        alert.setView(inputAge);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for OK button here

                //Set Age
                userAge = Integer.valueOf(inputAge.getText().toString());
                ageTextView.setText(inputAge.getText().toString());
                ageTextView.setVisibility(View.VISIBLE);

                //Toast Setup
                Toast ageToast = Toast.makeText(mContext, String.format("Set Age to %s", inputAge.getText().toString()),
                        Toast.LENGTH_SHORT);
                ageToast.show();

                // Set userSetAge
                userSetAge = true;
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for CANCEL button here, or leave in blank
            }
        });
        alert.show();
    }

    /**
     * This button set the TapTarget Animation on FloatingActionButton
     *
     * @param view
     */
    public void showUserInputTap(View view) {
        // Create Intent to go to YourInformationActivity
        final Intent yourInfoIntent = new Intent(this, YourInformationActivity.class);

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.submit_fab), "Are You Sure?", "Make sure your inputs are correct" +
                        "\n\nPress the button again to proceed")
                        // All options below are optional
                        .outerCircleColor(R.color.secondaryColor)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.whiteTarget)   // Specify a color for the target circle
                        .titleTextSize(30)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.whiteTarget)      // Specify the color of the title text
                        .descriptionTextSize(15)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.redTarget)  // Specify the color of the description text
                        .textColor(R.color.whiteTarget)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.blackTarget)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(true)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .icon(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_stethoscope))          // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional

                        // If User hasn't choosen the gender toast prompt will come out to verify
                        // If User has choosen the gender, then user will proceed to next activity
                        if (genderChoiceSelected && userSetAge) {
                            //Start Activity
                            startActivity(yourInfoIntent);
                        } else {
                            Toast.makeText(view.getContext(), "Failed! Make sure to set Age and select Gender",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

}
