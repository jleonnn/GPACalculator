package com.example.alvin.gpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public static final String TOTAL_PERCENTGAGE_EXTRA_STRING = "HI";

    private EditText mNameField;
    private EditText mMarksField;
    private EditText mTotalMarksField;
    private EditText mWeightageField;
    private Button mDoneButton;
    private Button mMoreButton;
    private float mMarks;
    private float mTotalMarks;
    private float mPercentage;
    private float mTotalPercentage;
    private float mWeightage;
    private float mTotalWeightage;
    private float mFinalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencing text fields
        mNameField = (EditText) findViewById(R.id.assessment_name_field);
        mMarksField = (EditText) findViewById(R.id.marks_field);
        mTotalMarksField = (EditText) findViewById(R.id.total_marks_field);
        mWeightageField = (EditText) findViewById(R.id.weightage_field);

        //Referencing buttons
        mDoneButton = (Button) findViewById(R.id.done_button);
        mMoreButton = (Button) findViewById(R.id.more_button);

        //When MORE button is clicked
        mMoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                getValues();
                //% score for this assignment, according to its weightage
                mPercentage = ((mMarks / mTotalMarks) * (mWeightage / 100));

                //Accumulating all the % and weightage values to get overall % score
                mTotalPercentage += mPercentage;
                mTotalWeightage += mWeightage;

                mNameField.setText("");
                mMarksField.setText("");
                mTotalMarksField.setText("");
                mWeightageField.setText("");

            }
        });

        //When DONE button is clicked
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                getValues();

                //Finding percentage score
                mPercentage = ((mMarks / mTotalMarks) * (mWeightage / 100));

                //Finding total score, based on weightage and the percentage score
                mTotalPercentage += mPercentage;
                mTotalWeightage += mWeightage;

                //Added (100/mTotalWeightage) part because the user's assessments may not add up to 100%, eg still MYCT
                mFinalScore = mTotalPercentage * (100 / mTotalWeightage);

                //Call new ScoreActivity
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra(ScoreActivity.MARKS_EXTRA_STRING, mFinalScore);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Getting values from text fields
    private void getValues(){
        mMarks = Float.parseFloat(mMarksField.getText().toString());
        mTotalMarks = Float.parseFloat(mTotalMarksField.getText().toString());
        mWeightage = Float.parseFloat(mWeightageField.getText().toString());
    }
}
