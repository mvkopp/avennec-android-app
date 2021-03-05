package com.example.myawesomeapp.presentation.mealdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myawesomeapp.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Meal Activity
 */
public class MealActivity extends AppCompatActivity {

    private TextView mealTitle;
    private TextView mealDescription;
    private ImageView mealThumbnail;
    private CheckBox isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal);

        Intent intent =  getIntent();
        String title = intent.getStringExtra("mealTitle");
        String description = intent.getStringExtra("mealDescription");
        String thumbnail = intent.getStringExtra("mealThumbnail");
        boolean favorite = intent.getBooleanExtra("isFavorite", false);

        mealTitle = findViewById(R.id.mealTitle);
        mealDescription = findViewById(R.id.mealDescription);
        mealThumbnail = findViewById(R.id.mealThumbnail);
        isFavorite = findViewById(R.id.isFavorite);

        mealTitle.setText(title);
        mealDescription.setText(description);
        Glide.with(this)
            .load(thumbnail)
            .into(mealThumbnail);
        isFavorite.setChecked(favorite);

        findViewById(R.id.mealBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
