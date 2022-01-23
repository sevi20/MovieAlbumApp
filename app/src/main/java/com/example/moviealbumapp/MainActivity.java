package com.example.moviealbumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText title, genre, city, movieDay, moviePrice;
    protected Button btnSave, btnMovieReg, btnMap;
    protected Intent intent;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.editTitle);
        genre = findViewById(R.id.editGenre);
        city = findViewById(R.id.editCity);
        movieDay = findViewById(R.id.editDay);
        moviePrice = findViewById(R.id.editPrice);

        btnSave = findViewById(R.id.btnMovieAdd);
        btnMovieReg = findViewById(R.id.btnMovieReg);
        btnMap = findViewById(R.id.btnMap);

        try {

            db = new DataBase(this);
            db.createTable();

        }catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMovie();
            }
        });

        btnMovieReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieList();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });

    }

    private void AddMovie(){
        try {

            db.insertMovie(
                    title.getText().toString(),
                    genre.getText().toString(),
                    city.getText().toString(),
                    movieDay.getText().toString(),
                    Float.parseFloat(moviePrice.getText().toString())
            );
            Toast.makeText(this, "You successfully added a movie!", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "Exception: "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void MovieList(){
        intent = new Intent(this, MovieRegister.class);
        startActivity(intent);
    }
}