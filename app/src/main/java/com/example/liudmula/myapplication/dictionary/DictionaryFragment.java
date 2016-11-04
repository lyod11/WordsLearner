package com.example.liudmula.myapplication.dictionary;



import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liudmula.myapplication.database.DBManager;
import com.example.liudmula.myapplication.R;


public class DictionaryFragment extends Fragment implements AddWordFragment.my_intf {


    private DBManager dbManager;

    private DictionaryCursorAdapter adapter;

    private ListView listView;

    private View view;

    private  Cursor cursor;
//    final String[] from = new String[] { DatabaseHelper._ID,    its unused
//        DatabaseHelper.WORD, DatabaseHelper.DESC, DatabaseHelper.PROGRESS };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        getActivity();
        listView = (ListView) view.findViewById(R.id.lvDict);
        dbManager = new DBManager(inflater.getContext());
        dbManager.open();

        //make the cursor global and private?
         cursor = dbManager.fetch();
        adapter = new DictionaryCursorAdapter(inflater.getContext(), cursor);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                TextView tvId = (TextView) view.findViewById(R.id.tv_id);
                TextView tvWord = (TextView) view.findViewById(R.id.tvWord);
                TextView tvDesc = (TextView) view.findViewById(R.id.tvDescription);
     //           TextView tvProgr = (TextView) view.findViewById(R.id.tvProgress);

                String idKey = tvId.getText().toString();
                String wordKey = tvWord.getText().toString();
                String descKey = tvDesc.getText().toString();
                //           String progrKey = tvProgr.getText().toString();  зачєм передавати прогрес в модіфай? ненада

                Bundle bundle = new Bundle();
                bundle.putString("_id", idKey);
                bundle.putString("word", wordKey);
                bundle.putString("desc", descKey);
                ModifyWordFragmentDialog dialogModify = new ModifyWordFragmentDialog();
                dialogModify.setArguments(bundle);
                dialogModify.show(getFragmentManager(), "DialogModify");
                adapter.notifyDataSetChanged();

            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void update_db_cursor() {
        cursor = dbManager.fetch();
        adapter.notifyDataSetChanged();

    }
}
