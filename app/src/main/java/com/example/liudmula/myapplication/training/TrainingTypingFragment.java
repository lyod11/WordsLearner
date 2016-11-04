package com.example.liudmula.myapplication.training;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liudmula.myapplication.R;


public class TrainingTypingFragment extends Fragment implements View.OnClickListener{

    EditText etAnsw;
    TextView tvWord, tvCorrectAnsw;
    Button btnOK;
    Training training;
    final Handler handler = new Handler();
    int delayTime = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training_typing, container, false);
        etAnsw = (EditText)v.findViewById(R.id.typ_et_answ);
        tvWord = (TextView)v.findViewById(R.id.typ_tv_word);
        tvCorrectAnsw = (TextView)v.findViewById(R.id.typ_tv_correctAnsw);
        btnOK = (Button)v.findViewById(R.id.typ_btn_ok);
        btnOK.setOnClickListener(this);
        training = new Training(v.getContext(), 5);
        tvCorrectAnsw.setVisibility(View.GONE);
        tvWord.setText(training.learningWordsCursor.getString(training.indexDesc));


        return v;
    }

    @Override
    public void onClick(View v) {
        if(etAnsw.getText().toString().equalsIgnoreCase(training.learningWordsCursor.getString(training.indexWord))){
            etAnsw.setTextColor(getResources().getColor(R.color.colorGreen));
            training.updateProgress();
        }else{
            tvCorrectAnsw.setText(training.learningWordsCursor.getString(training.indexWord));
            tvCorrectAnsw.setVisibility(View.VISIBLE);
            etAnsw.setTextColor(getResources().getColor(R.color.colorRed));
        }

        if(training.isNext()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvCorrectAnsw.setVisibility(View.GONE);
                    etAnsw.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvWord.setText(training.learningWordsCursor.getString(training.indexDesc));
                    etAnsw.setText("");
                }
            }, delayTime);
        }
        else {
            Fragment fragment = this;
            training.callResultFragment(2, fragment);

        }
    }



}
