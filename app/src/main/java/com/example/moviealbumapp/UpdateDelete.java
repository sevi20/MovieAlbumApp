package com.example.moviealbumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateDelete extends AppCompatActivity {

    protected EditText title, genre, city, movieDay, moviePrice;
    protected Button btnUpdate,btnDelete,btnBack;
    protected Intent intent;
    protected int id;
    protected String element;
    protected Bundle bundle;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        db = new DataBase(this);

        title = findViewById(R.id.editTitle);
        genre = findViewById(R.id.editGenre);
        city = findViewById(R.id.editCity);
        movieDay = findViewById(R.id.editDay);
        moviePrice = findViewById(R.id.editPrice);

        btnUpdate = findViewById(R.id.btnMovieAdd);
        btnDelete = findViewById(R.id.btnMovieReg);
        btnBack = findViewById(R.id.btnBack);

        ShowService();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMovie();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMovie();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToList();
            }
        });

    }

    private void ShowService() {
        bundle = getIntent().getExtras();
        element = bundle.getString("element");

        String[] elementArray = element.split("/");

        id = Integer.parseInt(elementArray[0]);
        title.setText(elementArray[1]);
        genre.setText(elementArray[2]);
        city.setText(elementArray[3]);
        movieDay.setText(elementArray[4]);
        moviePrice.setText(elementArray[5]);



    }

    private void UpdateMovie() {

        db.updateMovie(
                id,
                title.getText().toString(),
                genre.getText().toString(),
                city.getText().toString(),
                movieDay.getText().toString(),
                Float.parseFloat(moviePrice.getText().toString())
        );

        BackToList();
    }

    private void DeleteMovie() {
        db.deleteMovie(id);
        BackToList();
    }

    public void BackToList(){
        intent = new Intent(this, MovieRegister.class);
        startActivity(intent);
    }
}