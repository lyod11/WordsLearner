package com.example.liudmula.myapplication.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.liudmula.myapplication.R;


public class TranslationFragment extends Fragment {



    String[] translations = new String[]{"яблуко", "неділя", "мама"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translation, container, false);

        ListView lv = (ListView) v.findViewById(R.id.lv_tab_translation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_multiple_choice, translations);
        lv.setAdapter(adapter);

        return v;
    }

}
