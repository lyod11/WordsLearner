package com.example.liudmula.myapplication.dictionary;



import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.liudmula.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class TranslationFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<String>> {


    private static final String REQUEST_URL = "https://glosbe.com/gapi/translate?from=eng&dest=ukr&format=json";
    private static final String LOG_TAG = TranslationFragment.class.getSimpleName();

    private static final int LOADER_ID = 0;

    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translation, container, false);

        // ArrayList<String> definitions = QueryUtils.extractTranslations();

        ListView lv = (ListView) v.findViewById(R.id.lv_tab_translation);
        adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_list_item_multiple_choice, new ArrayList<String>());
        lv.setAdapter(adapter);

        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();

        return v;
    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        //phrase=sorry&pretty=true
        String word = getArguments().getString("word");
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("phrase", word);
        uriBuilder.appendQueryParameter("pretty", "false");
        return new TranslationLoader(this.getContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<String>> loader, List<String> translations) {
      adapter.clear();
        if(translations!=null && !translations.isEmpty()){
            adapter.addAll(translations);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        adapter.clear();
    }
}



