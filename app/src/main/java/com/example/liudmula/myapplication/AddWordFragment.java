package com.example.liudmula.myapplication;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by liudmula on 18.10.16.
 */

public class AddWordFragment extends DialogFragment implements View.OnClickListener {

    private DBManager dbManager;
    private EditText etAddWord, etAddDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Нове слово");
        View v = inflater.inflate(R.layout.add_word_dialog, null);
        v.findViewById(R.id.btn_Add_word).setOnClickListener(this);
        etAddWord = (EditText)v.findViewById(R.id.etAddWord);
        etAddDesc = (EditText)v.findViewById(R.id.etAddDesc);
        dbManager = new DBManager(inflater.getContext()); //може не пахати
        dbManager.open();
        return v;
    }


    @Override
    public void onClick(View v) {
        String word = etAddWord.getText().toString();
        String desc = etAddDesc.getText().toString();
        dbManager.insert(word, desc, 0);
        dismiss();
    }
}
