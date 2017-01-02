package com.example.liudmula.myapplication.dictionary;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by liudmula on 02.01.17.
 */

public final class QueryUtils {

    private static final String SIMPLE_JSON_RESPONSE = "{\"result\":\"ok\",\"tuc\":[{\"phrase\":{\"text\":\"ласкаво просимо\",\"language\":\"uk\"},\"meaningId\":6044520889283314198,\"authors\":[1]},{\"phrase\":{\"text\":\"добрий день\",\"language\":\"uk\"},\"meaningId\":-6045382253620402355,\"authors\":[2695]},{\"phrase\":{\"text\":\"привіт\",\"language\":\"uk\"},\"meaningId\":-7096157738609663182,\"authors\":[2695]},{\"phrase\":{\"text\":\"до́брий день\",\"language\":\"uk\"},\"meaningId\":1174109294372041574,\"authors\":[83058]},{\"phrase\":{\"text\":\"ласка́во про́симо\",\"language\":\"uk\"},\"meaningId\":362778162971315176,\"authors\":[83058]},{\"phrase\":{\"text\":\"приві́т\",\"language\":\"uk\"},\"meaningId\":-7647382959423469003,\"authors\":[83058]}],\"phrase\":\"witaj\",\"from\":\"pl\",\"dest\":\"uk\",\"authors\":{\"1\":{\"U\":\"http://en.wiktionary.org\",\"id\":1,\"N\":\"en.wiktionary.org\",\"url\":\"https://glosbe.com/source/1\"},\"83058\":{\"U\":\"\",\"id\":83058,\"N\":\"Dbnary: Wiktionary as Linguistic Linked Open Data\",\"url\":\"https://glosbe.com/source/83058\"},\"2695\":{\"U\":\"http://dumps.wikimedia.org/enwiktionary/latest/enwiktionary-latest-pages-articles.xml.bz2\",\"id\":2695,\"N\":\"Wiktionary\",\"url\":\"https://glosbe.com/source/2695\"}}}";

    private QueryUtils() {
    }

    public static ArrayList<String> extractTranslations() {

        ArrayList<String> definitions = new ArrayList<>();
        JSONObject baseJsonResponse = null;

        try {
            baseJsonResponse = new JSONObject(SIMPLE_JSON_RESPONSE);
            JSONArray definitionArray = baseJsonResponse.getJSONArray("tuc");

            for(int i=0; i<definitionArray.length(); i++){
                JSONObject currentItem = definitionArray.getJSONObject(i);
                JSONObject currPhrase = currentItem.getJSONObject("phrase");
                String def = currPhrase.getString("text");
                definitions.add(def);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return definitions;
    }





}