package com.example.moviealbumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.List;

public class MovieRegister extends AppCompatActivity {

    protected Button btnBack;
    protected Intent intent;
    protected ListView movieListView;
    protected Bundle bundle;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_register);

        db = new DataBase(this);

        btnBack = findViewById(R.id.btnBack);
        movieListView = findViewById(R.id.movieListView);

        GetList();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMain();
            }
        });
    }

    private void GetList() {
        List<String> elementArray = db.selectMovies();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, elementArray);
        movieListView.setAdapter(arrayAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Show(arrayAdapter.getItem(position));
            }
        });
    }

    private void Show(String element) {
        //Toast.makeText(this, element, Toast.LENGTH_LONG).show();
        intent = new Intent(this, UpdateDelete.class);
        bundle = new Bundle();
        bundle.putString("element", element);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    public void BackToMain(){
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
