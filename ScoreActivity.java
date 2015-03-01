package com.example.alvin.gpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoreActivity extends ActionBarActivity {

    public static final String MARKS_EXTRA_STRING = "Hello, world";

    //declaring variables
    private TextView mScoreTextView;
    private TextView mGpaTextView;
    private float mMarks;
    private String mGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //Getting extras values from the other activity
        Intent intent = getIntent();
        mMarks = intent.getFloatExtra(MARKS_EXTRA_STRING, 0);
        mMarks = mMarks * 100;

        //Setting range of marks for GPAs
        if (mMarks < 40){
            mGpa = "0.8";
        } else if (mMarks < 45 && mMarks >= 40) {
            mGpa = "1.2";
        } else if (mMarks < 50 && mMarks >= 45) {
            mGpa = "1.6";
        } else if (mMarks <55 && mMarks >= 50) {
            mGpa = "2.0";
        } else if (mMarks <60 && mMarks >= 55) {
            mGpa = "2.4";
        } else if (mMarks <65 && mMarks >= 60) {
            mGpa = "2.8";
        } else if (mMarks < 70 && mMarks >= 65) {
            mGpa = "3.2";
        } else if (mMarks < 80 && mMarks >= 70) {
            mGpa = "3.6";
        } else {
            mGpa = "4.0";
        }

        mScoreTextView = (TextView) findViewById(R.id.score);
        mScoreTextView.setText("Your score is: " + mMarks + "%");
        mGpaTextView = (TextView) findViewById(R.id.gpa);
        mGpaTextView.setText("Your GPA is: " + mGpa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
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
}
