package com.example.liudmula.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;


public class TrainingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        int trainingFragmentIndex = getIntent().getIntExtra("training", -1);
        boolean trainingType = getIntent().getBooleanExtra("type", true);
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();
        if(trainingFragmentIndex == 0){
            fragmentClass = WordTranslationFragment.class;
            bundle.putBoolean("type", getIntent().getBooleanExtra("type", true));
        }
        if(trainingFragmentIndex == 1){
            fragmentClass = WordTranslationFragment.class;
            bundle.putBoolean("type", getIntent().getBooleanExtra("type", false));
        }

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        FragmentManager fragmentManager = getFragmentManager();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.training_frame, fragment).commit();


    }

}