package com.example.liudmula.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyWordsActivity extends Activity implements View.OnClickListener
{

    private EditText wordText, descText;
    private Button btnModify, btnDelete;
    private long _id;
    private String id, word, desc;


    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Редагувати слово");

        setContentView(R.layout.activity_modify_words);

        dbManager = new DBManager(this);
        dbManager.open();

        wordText = (EditText)findViewById(R.id.etModifyWord);
        descText = (EditText)findViewById(R.id.etModifyDesc);

        btnDelete = (Button)findViewById(R.id.btn_Delete_word);
        btnModify = (Button)findViewById(R.id.btn_Modify_word);

        Intent intent = getIntent();

        id = intent.getStringExtra("_id");
        word = intent.getStringExtra("word");
        desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        wordText.setText(word);
        descText.setText(desc);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Modify_word:
                word = wordText.getText().toString();
                desc = descText.getText().toString();

                dbManager.update(_id, word, desc);
                this.returnBack();
                break;
            case R.id.btn_Delete_word:
                dbManager.delete(_id);
                this.returnBack();
                break;
        }
    }

    public void returnBack(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Fragment", R.id.nav_dictionary);
        setIntent(intent);
    }
}
