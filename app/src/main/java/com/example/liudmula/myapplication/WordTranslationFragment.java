package com.example.liudmula.myapplication;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordTranslationFragment extends Fragment implements View.OnClickListener{


    Button btnTrans1, btnTrans2, btnTrans3, btnTrans4, btnTrans5, btnTrans6;
    TextView tvWord;
    final Handler handler = new Handler();
    int delayTime = 2000;
    int correctAnswers = 0;
    Integer qnumber = 3;
    Training training;
    View v;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_word_translation, container, false);
        // Inflate the layout for this fragment
        tvWord = (TextView)v.findViewById(R.id.tv_trn1_word);

        btnTrans1 = (Button)v.findViewById(R.id.btn_trn1_w1);
        btnTrans1.setOnClickListener(this);
        btnTrans2 = (Button)v.findViewById(R.id.btn_trn1_w2);
        btnTrans2.setOnClickListener(this);
        btnTrans3 = (Button)v.findViewById(R.id.btn_trn1_w3);
        btnTrans3.setOnClickListener(this);
        btnTrans4 = (Button)v.findViewById(R.id.btn_trn1_w4);
        btnTrans4.setOnClickListener(this);
        btnTrans5 = (Button)v.findViewById(R.id.btn_trn1_w5);
        btnTrans5.setOnClickListener(this);
        btnTrans6 = (Button)v.findViewById(R.id.btn_trn1_w6);

        training = new Training(v.getContext());
        setButtonsText();
        return v;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onClick(View v) {
        Button btnClicked = (Button)v.findViewById(v.getId());
        final Button btnCorrectAnswer = findAnswerButton(training.learningWordsCursor.getString(training.indexDesc));
        if(btnClicked.equals(btnCorrectAnswer)){
            training.updateProgress();
            training.changeButtonColor(true, btnClicked, delayTime);
        }else{
            training.changeButtonColor(false, btnClicked, delayTime/2);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    training.changeButtonColor(true, btnCorrectAnswer, delayTime/2);
                }
            }, delayTime/2);

        }
        if(training.isNext()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setButtonsText();
                }
            }, delayTime);
        }
//        else
//            викликати результатуючий фрагмент
    }







    public void setButtonsText(){
        ArrayList<String> randoms = training.getRandoms("5", true);
        tvWord.setText(training.learningWordsCursor.getString(training.indexWord));
        btnTrans1.setText(randoms.get(0));
        btnTrans2.setText(randoms.get(1));
        btnTrans3.setText(randoms.get(2));
        btnTrans4.setText(randoms.get(3));
        btnTrans5.setText(randoms.get(4));
        btnTrans6.setText(randoms.get(5));


    }







    public Button findAnswerButton(String desc){
        if(desc.equalsIgnoreCase(btnTrans1.getText().toString()))
            return btnTrans1;
        if(desc.equalsIgnoreCase(btnTrans2.getText().toString()))
            return btnTrans2;
        if(desc.equalsIgnoreCase(btnTrans3.getText().toString()))
            return btnTrans3;
        if(desc.equalsIgnoreCase(btnTrans4.getText().toString()))
            return btnTrans4;
        if(desc.equalsIgnoreCase(btnTrans5.getText().toString()))
            return btnTrans5;
        if(desc.equalsIgnoreCase(btnTrans6.getText().toString()))
            return btnTrans6;
        return null;
    }
}





