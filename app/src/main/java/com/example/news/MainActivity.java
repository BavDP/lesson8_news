package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream articlesStream = getResources().openRawResource(R.raw.articles);
        Reader articlesReader = new InputStreamReader(articlesStream);
        BufferedReader articlesJson = new BufferedReader(articlesReader);
        final StringBuilder jsonText = new StringBuilder();
        articlesJson.lines().forEach(line -> jsonText.append(line));
        LinearLayout articleList = findViewById(R.id.articleList);
        try {
            JSONArray json = new JSONArray(jsonText.toString());
            LayoutInflater inflater = getLayoutInflater();
            for (int i = 0; i < json.length(); i++) {
                View listItemView = inflater.inflate(R.layout.article, null);
                JSONObject article = (JSONObject) json.get(i);
                TextView title = listItemView.findViewById(R.id.title);
                TextView summary = listItemView.findViewById(R.id.summary);
                title.setText(article.getString("title"));
                summary.setText(article.getString("summary"));
                articleList.addView(listItemView);
            }
        } catch(JSONException e) {
            e.getStackTrace();
        }
    }
}