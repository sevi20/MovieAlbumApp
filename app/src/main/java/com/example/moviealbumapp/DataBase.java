package com.example.moviealbumapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    SQLiteDatabase dataBase;

    private final String movies = "CREATE TABLE If not exists Movies(" +
            "movieID Integer primary key autoincrement," +
            "movieTitle nvarchar(255) not null," +
            "movieGenre nvarchar(255) not null," +
            "movieCity nvarchar(50) not null," +
            "movieDate nvarchar(50) not null," +
            "moviePrice decimal not null" +
            ")";

    public DataBase(MainActivity context) throws SQLException{
        dataBase = SQLiteDatabase.openOrCreateDatabase(
                context.getFilesDir()+"/"+"Movies.db",null);
    }

    public DataBase(MovieRegister context) throws SQLException{
        dataBase = SQLiteDatabase.openOrCreateDatabase(
                context.getFilesDir()+"/"+"Movies.db",null);
    }

    public DataBase(UpdateDelete context) throws SQLException{
        dataBase = SQLiteDatabase.openOrCreateDatabase(
                context.getFilesDir()+"/"+"Movies.db",null);
    }

    public void createTable() throws SQLException{
        dataBase.execSQL(movies);
    }

    public void insertMovie(String title, String genre, String city, String date, float price) throws SQLException {
        String q = "Insert into Movies(movieTitle, movieGenre, movieCity, movieDate ,moviePrice)" +
                "Values(?,?,?,?,?)";
        dataBase.execSQL(q,new Object[]{title,genre,city,date,price});
    }

    public void updateMovie(int movieID, String title, String genre, String city, String date, float price) throws SQLException {
        String q = "Update Movies " +
                "Set " +
                "movieTitle = ?, " +
                "movieGenre = ? ," +
                "movieCity = ? ," +
                "movieDate = ?, " +
                "moviePrice = ? " +
                "Where movieID = ?";
        dataBase.execSQL(q, new Object[]{title,genre,city,date,price, movieID});
    }

    public void deleteMovie(int movieID) throws SQLException {
        String q = "Delete from Movies " +
                "Where movieID = " + movieID;
        dataBase.execSQL(q);
    }

    public ArrayList<String> selectMovies() throws SQLException {
        List<String> list = new ArrayList<String>();
        dataBase.beginTransaction();
        String q = "Select movieID, movieTitle, movieGenre, movieCity, movieDate ,moviePrice from Movies " +
                "Order By movieCity";
        Cursor cursor = dataBase.rawQuery(q, null);
        while(cursor.moveToNext()){

            @SuppressLint("Range") int movieID = cursor.getInt(cursor.getColumnIndex("movieID"));
            @SuppressLint("Range") String movieTitle = cursor.getString(cursor.getColumnIndex("movieTitle"));
            @SuppressLint("Range") String movieGenre = cursor.getString(cursor.getColumnIndex("movieGenre"));
            @SuppressLint("Range") String movieCity = cursor.getString(cursor.getColumnIndex("movieCity"));
            @SuppressLint("Range") String movieDate = cursor.getString(cursor.getColumnIndex("movieDate"));
            @SuppressLint("Range") float moviePrice = cursor.getFloat(cursor.getColumnIndex("moviePrice"));

            String elements = movieID + "/"
                    + movieTitle + "/"
                    + movieGenre + "/"
                    + movieCity + "/"
                    + movieDate + "/"
                    + moviePrice;
            list.add(elements);
        }
        dataBase.endTransaction();
        cursor.close();
        return (ArrayList<String>) list;
    }
}
