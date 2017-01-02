package com.example.liudmula.myapplication.dictionary;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by liudmula on 02.01.17.
 */

public class TranslationLoader extends AsyncTaskLoader<List<String>>{

    private String mUrl;

    public TranslationLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<String> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        List<String> translations = QueryUtils.fetchData(mUrl);
        return translations;
    }
}
