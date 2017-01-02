package com.example.liudmula.myapplication.dictionary;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudmula on 02.01.17.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    private static final String SIMPLE_JSON_RESPONSE = "{\"result\":\"ok\",\"tuc\":[{\"phrase\":{\"text\":\"ласкаво просимо\",\"language\":\"uk\"},\"meaningId\":6044520889283314198,\"authors\":[1]},{\"phrase\":{\"text\":\"добрий день\",\"language\":\"uk\"},\"meaningId\":-6045382253620402355,\"authors\":[2695]},{\"phrase\":{\"text\":\"привіт\",\"language\":\"uk\"},\"meaningId\":-7096157738609663182,\"authors\":[2695]},{\"phrase\":{\"text\":\"до́брий день\",\"language\":\"uk\"},\"meaningId\":1174109294372041574,\"authors\":[83058]},{\"phrase\":{\"text\":\"ласка́во про́симо\",\"language\":\"uk\"},\"meaningId\":362778162971315176,\"authors\":[83058]},{\"phrase\":{\"text\":\"приві́т\",\"language\":\"uk\"},\"meaningId\":-7647382959423469003,\"authors\":[83058]}],\"phrase\":\"witaj\",\"from\":\"pl\",\"dest\":\"uk\",\"authors\":{\"1\":{\"U\":\"http://en.wiktionary.org\",\"id\":1,\"N\":\"en.wiktionary.org\",\"url\":\"https://glosbe.com/source/1\"},\"83058\":{\"U\":\"\",\"id\":83058,\"N\":\"Dbnary: Wiktionary as Linguistic Linked Open Data\",\"url\":\"https://glosbe.com/source/83058\"},\"2695\":{\"U\":\"http://dumps.wikimedia.org/enwiktionary/latest/enwiktionary-latest-pages-articles.xml.bz2\",\"id\":2695,\"N\":\"Wiktionary\",\"url\":\"https://glosbe.com/source/2695\"}}}";

    private QueryUtils() {
    }

    public static List<String> fetchData(String requestedUrl){
        URL url = createUrl(requestedUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making http request", e);
        }

        ArrayList<String> translations = extractTranslations(jsonResponse);
        return translations;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building url", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null){
            return null;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);  //milliseconds
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET"); //??? maybe should be TRANSLATE?
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream  = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving JSON results", e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<String> extractTranslations(String jsonResponse) {

        ArrayList<String> definitions = new ArrayList<>();
        JSONObject baseJsonResponse = null;

        try {
            baseJsonResponse = new JSONObject(jsonResponse);
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