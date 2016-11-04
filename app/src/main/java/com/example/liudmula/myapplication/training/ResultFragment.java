package com.example.liudmula.myapplication.training;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liudmula.myapplication.MainActivity;
import com.example.liudmula.myapplication.R;


public class ResultFragment extends Fragment implements View.OnClickListener{

    Button btnComplete, btnRepeat;
    TextView tvQuestions, tvAnswers;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        tvAnswers = (TextView)v.findViewById(R.id.tv_answ_correct);
        tvQuestions = (TextView)v.findViewById(R.id.tv_answ_numbers);
        btnComplete = (Button)v.findViewById(R.id.btn_answ_end);
        btnComplete.setOnClickListener(this);
        btnRepeat = (Button)v.findViewById(R.id.btn_answ_repeat);
        btnRepeat.setOnClickListener(this);

        context = inflater.getContext();

        String answ = getArguments().getString("answers");
        tvAnswers.setText(answ);
        String questions = getArguments().getString("questions");
        tvQuestions.setText(questions);


        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_answ_end:

                intent.setClass(context, MainActivity.class);
                intent.putExtra("frag", R.id.nav_training);
                startActivity(intent);
                break;

            case R.id.btn_answ_repeat:
                intent.setClass(context, TrainingActivity.class);
                intent.putExtra("training", getArguments().getInt("trainingType"));
                startActivity(intent);
                break;
        }

    }
}
