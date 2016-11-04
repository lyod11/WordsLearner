package com.example.liudmula.myapplication.dictionary;

import android.app.DialogFragment;
import android.os.Bundle;

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

public class AddWordFragment extends DialogFragment implements View.OnClickListener {

    private DBManager dbManager;
    private EditText etAddWord, etAddDesc;
    public interface my_intf{
        void update_db_cursor();
    }
    my_intf inf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Нове слово");
        inf = (my_intf) ((MainActivity)getActivity()).a;
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
        inf.update_db_cursor();
        //((MainActivity)getActivity()).startFragment(R.id.nav_dictionary);
        dismiss();


    }
}
