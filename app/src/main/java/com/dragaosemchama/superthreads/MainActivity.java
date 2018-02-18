package com.dragaosemchama.superthreads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textInfo;
    TextView textMoreInfo;
    ProgressBar progressBar;
    Button buttonStart;
    Button buttonCancel;

    LongTask longTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try { getSupportActionBar().hide(); }
        catch (NullPointerException E) {E.printStackTrace();}

        textInfo = (TextView) findViewById(R.id.textInfo);
        textMoreInfo = (TextView) findViewById(R.id.textMoreInfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        progressBar.setScaleY(3f);
    }

    public void buttonStartCallback(View v) {
        longTask = new LongTask();
        longTask.setProgressBar(progressBar);
        longTask.setTextInfo(textInfo);
        longTask.setTextMoreInfo(textMoreInfo);
        longTask.execute();
    }

    public void buttonCancelCallback(View v) {
        if(longTask != null) longTask.cancel(true);
    }
}