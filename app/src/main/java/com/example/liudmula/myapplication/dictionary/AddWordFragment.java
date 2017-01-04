package com.example.liudmula.myapplication.dictionary;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.liudmula.myapplication.database.DBManager;
import com.example.liudmula.myapplication.MainActivity;
import com.example.liudmula.myapplication.R;

/**
 * Created by liudmula on 18.10.16.
 */

public class AddWordFragment extends DialogFragment implements View.OnClickListener{


    private EditText etAddWord;
    TranslationFragment frag;
    String word;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Нове слово");

        frag = new TranslationFragment();
        View v = inflater.inflate(R.layout.add_word_dialog, null);
        v.findViewById(R.id.btn_AddDialog_add).setOnClickListener(this);
        v.findViewById(R.id.btn_AddDialog_cancel).setOnClickListener(this);
        etAddWord = (EditText)v.findViewById(R.id.etAddWord);
        //etAddDesc = (EditText)v.findViewById(R.id.etAddDesc);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_AddDialog_add:
                word = etAddWord.getText().toString();
                TabParentFragment tabFrag = new TabParentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("word", word);
                tabFrag.setArguments(bundle);
                tabFrag.getFragmentManager();
                android.support.v4.app.FragmentManager fragmentManager =  getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, tabFrag).commit();
                break;
        }

        dismiss();
    }
}
