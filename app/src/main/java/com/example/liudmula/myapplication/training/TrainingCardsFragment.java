package com.example.liudmula.myapplication.training;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.liudmula.myapplication.R;


public class TrainingCardsFragment extends Fragment implements View.OnClickListener{

    TextView tvWord;
    Button btnFlip, btnKnow, btnUnknown;
    Training training;
    AnimatorSet mCardRightOut, mCardLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardLayout;
    CardView card;
    static final int ANIM_DURATION = 1000;
    static final boolean ANIM_TO_DESCRIPTION = true;
    static final boolean ANIM_TO_WORD = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training_cards, container, false);
        tvWord = (TextView)v.findViewById(R.id.cards_tv);
        btnFlip = (Button)v.findViewById(R.id.cards_btn_flip);
        btnFlip.setOnClickListener(this);
        btnKnow = (Button)v.findViewById(R.id.cards_btn_know);
        btnKnow.setOnClickListener(this);
        btnUnknown = (Button)v.findViewById(R.id.cards_btn_unknown);
        btnUnknown.setOnClickListener(this);
//        mCardLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(v.getContext(), R.animator.in_animation);
//        mCardRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(v.getContext(), R.animator.out_animation);

        card = (CardView)v.findViewById(R.id.card_view);
        training = new Training(v.getContext());

        tvWord.setText(training.learningWordsCursor.getString(training.indexWord));

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cards_btn_flip:
                animateCard(ANIM_TO_DESCRIPTION);
                break;
            case R.id.cards_btn_know:
                training.updateProgress();
                animateCard(ANIM_TO_WORD);

                break;
            case R.id.cards_btn_unknown:
                animateCard(ANIM_TO_WORD);
        }
    }

    public void setNewCardWord(){
        if(training.isNext()){
            tvWord.setText(training.learningWordsCursor.getString(training.indexWord));
            btnFlip.setVisibility(View.VISIBLE);
            btnKnow.setVisibility(View.GONE);
            btnUnknown.setVisibility(View.GONE);
        }else{
            Fragment fragment = this;
            training.callResultFragment(3, fragment);

        }
    }

    public void setCardDesc() {
        tvWord.setText(training.learningWordsCursor.getString(training.indexDesc));
        btnFlip.setVisibility(View.GONE);
        btnKnow.setVisibility(View.VISIBLE);
        btnUnknown.setVisibility(View.VISIBLE);
    }


    public void animateCard(final boolean anim){
        card.animate().setInterpolator(new AccelerateDecelerateInterpolator())
                .scaleX(0)
                .setDuration(ANIM_DURATION)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if(anim == ANIM_TO_DESCRIPTION)
                            setCardDesc();
                        else
                            setNewCardWord();

                        card.animate().setInterpolator(new AccelerateDecelerateInterpolator())
                                .scaleX(1)
                                .setDuration(ANIM_DURATION)
                                .start();
                    }
                })
                .start();

    }
}
