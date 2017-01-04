package com.example.liudmula.myapplication.dictionary;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
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

public class AddWordFragment extends DialogFragment{

    private EditText etAddWord;
    TranslationFragment frag;
    String word;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_word_dialog, null);
        etAddWord = (EditText)v.findViewById(R.id.etAddWord);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext())
                .setTitle("Нове слово")
                .setMessage("Введіть слово")
                .setView(v);


        builder.setPositiveButton(R.string.btn_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                word = etAddWord.getText().toString();
                TabParentFragment tabFrag = new TabParentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("word", word);
                tabFrag.setArguments(bundle);
                tabFrag.getFragmentManager();
                android.support.v4.app.FragmentManager fragmentManager =  getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, tabFrag).commit();
            }
        });
        AlertDialog dialog = builder.create();
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        dialog.show();
        //builder.show();

//        getDialog().setTitle("Нове слово");
//
//        frag = new TranslationFragment();
//
//        v.findViewById(R.id.btn_AddDialog_add).setOnClickListener(this);
//        v.findViewById(R.id.btn_AddDialog_cancel).setOnClickListener(this);

//        //etAddDesc = (EditText)v.findViewById(R.id.etAddDesc);

        return v;
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_AddDialog_add:
//                word = etAddWord.getText().toString();
//                TabParentFragment tabFrag = new TabParentFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("word", word);
//                tabFrag.setArguments(bundle);
//                tabFrag.getFragmentManager();
//                android.support.v4.app.FragmentManager fragmentManager =  getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_main, tabFrag).commit();
//                break;
//        }
//
//        dismiss();
//    }
}
