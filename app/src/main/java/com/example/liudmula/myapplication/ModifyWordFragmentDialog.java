package com.example.liudmula.myapplication;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liudmula on 19.10.16.
 */

public class ModifyWordFragmentDialog extends DialogFragment implements View.OnClickListener
{

    private EditText wordText, descText;
    private Button btnModify, btnDelete;
    private long _id;
    private String id, word, desc;


    private DBManager dbManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Редагувати слово");
        View v = inflater.inflate(R.layout.modify_words_dialog, null);

        dbManager = new DBManager(inflater.getContext());
        dbManager.open();

        wordText = (EditText)v.findViewById(R.id.etModifyWord);
        descText = (EditText)v.findViewById(R.id.etModifyDesc);

        btnDelete = (Button)v.findViewById(R.id.btn_Delete_word);
        btnDelete.setOnClickListener(this);
        btnModify = (Button)v.findViewById(R.id.btn_Modify_word);
        btnModify.setOnClickListener(this);

        word = getArguments().getString("word");
        id = getArguments().getString("_id");
        desc = getArguments().getString("desc");

        _id = Long.parseLong(id);

        wordText.setText(word);
        descText.setText(desc);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Modify_word:
                word = wordText.getText().toString();
                desc = descText.getText().toString();

                dbManager.update(_id, word, desc);
                this.dismiss();
                break;
            case R.id.btn_Delete_word:
                dbManager.delete(_id);
                this.dismiss();
                break;
        }
    }


}
