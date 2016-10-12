package com.example.liudmula.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DictionaryFragment extends Fragment {

    private DBManager dbManager;

    private DictionaryCursorAdapter adapter;

    private ListView listView;

    private View view;

//    final String[] from = new String[] { DatabaseHelper._ID,    its unused
//        DatabaseHelper.WORD, DatabaseHelper.DESC, DatabaseHelper.PROGRESS };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DBManager(this.getContext());
        dbManager.open();
        //make the cursor global and private?
        Cursor cursor = dbManager.fetch();

        listView = (ListView) view.findViewById(R.id.lvDict);

       // listView.setEmptyView(); що за єрєсь?

        adapter = new DictionaryCursorAdapter(this.getContext(), cursor);
        listView.setAdapter(adapter);

        //what the hell is that AdapterView???
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvWord = (TextView) view.findViewById(R.id.tvWord);
                TextView tvDesc = (TextView) view.findViewById(R.id.tvDescription);
                TextView tvProgr = (TextView) view.findViewById(R.id.tvProgress);

                String wordKey = tvWord.getText().toString();
                String descKey = tvDesc.getText().toString();
                String progrKey = tvProgr.getText().toString();

                Intent modify_intent = new Intent(getActivity().getApplicationContext(), ModifyWordsActivity.class);
                modify_intent.putExtra("word", wordKey);
                modify_intent.putExtra("desc", descKey);
                modify_intent.putExtra("progress", progrKey);

                startActivity(modify_intent);


            }
        });
    }



}
