package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Finish extends AppCompatActivity {
    int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish2);

        // Gets the current score value from the main activity
        Intent mainActivity = getIntent();
        int currentScore = mainActivity.getIntExtra("CurrentScore", 0);
        highScore = mainActivity.getIntExtra("SendCurrentHighScore", 0);
        TextView yourScore = findViewById(R.id.yourScore);
        yourScore.setText("Your Score: " + currentScore*10 + "%");

        TextView displayHighScore = findViewById(R.id.highScore);

        // Checks if the highscore needs to be changed
        if(currentScore > highScore){
            highScore = currentScore;
            displayHighScore.setText("High Score: " + highScore*10 + "%");
        }
        else{
            displayHighScore.setText("High Score: " + highScore*10 + "%");
        }


        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send the current high score to the main activity
                Intent sendHighScore = new Intent(getApplicationContext(), MainActivity.class);
                sendHighScore.putExtra("CurrentHighScore", highScore);  // Send the high score value to the main activity

                startActivity(new Intent(Finish.this, MainActivity.class));
                startActivity(sendHighScore);
            }
        });

    }

}