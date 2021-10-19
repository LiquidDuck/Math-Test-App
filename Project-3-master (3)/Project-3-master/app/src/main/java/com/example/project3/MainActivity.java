package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int questionCount = 1;
    int totalCorrect = 0;
    int currrentHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next = findViewById(R.id.nextQuestion);
        RadioGroup choices = findViewById(R.id.choices);

        TextView currentScore = findViewById(R.id.currentScore);    // test text

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionCount++;

                checkAnswer();  // Checks if the selected answer is correct

                updateQuestion();
                choices.clearCheck();   // Clears the previous answers
                currentScore.setText("Current Score: " + totalCorrect*10 + "%");

                // Special case for question 10
                if(questionCount == 10){
                    next.setText("Submit");
                    configureNextButton();
                }

            }
        });

    }

    // Updates the answer choices of each question
    private void updateQuestion(){
        ImageView question = findViewById(R.id.question);
        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        if(questionCount == 2){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q2));
            choice1.setText("x = 2");
            choice2.setText("x = 7");   // Correct Answer
            choice3.setText("x = 3");
            choice4.setText("x = 4");
        }
        else if(questionCount == 3){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q3));
            choice1.setText("(-x-7)(x-7)");
            choice2.setText("(x+7)(x+7)");
            choice3.setText("(x-7)(x-7)");
            choice4.setText("(x-7)(x+7)");  // Correct Answer
        }
        else if(questionCount == 4){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q4));
            choice1.setText("x = 2");
            choice2.setText("(x-5)(x+4)");  // Correct Answer
            choice3.setText("(x+4)(x+5)");
            choice4.setText("(-x+4)(x-5)");
        }
        else if(questionCount == 5){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q5));
            choice1.setText("x = 4");
            choice2.setText("x = 1");   // Correct Answer
            choice3.setText("x = -1");
            choice4.setText("x = 0");
        }
        else if(questionCount == 6){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q6));
            choice1.setText("y=2x+7");  // Correct Answer
            choice2.setText("y=-2x+5");
            choice3.setText("y=2x-7");
            choice4.setText("y=-x+4");
        }
        else if(questionCount == 7){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q7));
            choice1.setText("-15"); // Correct Answer
            choice2.setText("16");
            choice3.setText("-14");
            choice4.setText("-16");
        }
        else if(questionCount == 8){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q8));
            choice1.setText("m = 5");
            choice2.setText("m = 4/3");
            choice3.setText("m = 3");
            choice4.setText("m = 4");   // Correct Answer
        }
        else if(questionCount == 9){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q9));
            choice1.setText("y = x^4 + 8x^2 - 12");
            choice2.setText("y = x^4 - 8x^2 + 10");
            choice3.setText("y = x^4 - 8x^2 + 12"); // Correct Answer
            choice4.setText("y = x^4 - 8x^2 - 20");
        }
        else if(questionCount == 10){
            question.setImageDrawable(getDrawable(R.drawable.project_3_quiz_q10));
            choice1.setText("x = -2"); // Correct Answer
            choice2.setText("x = -2, 2");
            choice3.setText("x = 2");
            choice4.setText("None");
        }

    }

    // Tracks how many answers were correct
    private void checkAnswer(){
        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCount == 6 || questionCount == 7 || questionCount == 10){
                    totalCorrect++;
                }
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCount == 2 || questionCount == 4 || questionCount == 5){
                    totalCorrect++;
                }
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCount == 1 || questionCount == 9){
                    totalCorrect++;
                }
            }
        });

        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCount == 3 || questionCount == 8){
                    totalCorrect++;
                }
            }
        });

    }

    // Prepares to switch to the next activity
    private void configureNextButton(){
        Button nextButton = findViewById(R.id.nextQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Finish.class));

                Intent sendHighScore = getIntent();
                currrentHighScore = sendHighScore.getIntExtra("CurrentHighScore", 0); // Gets the high score value

                Intent sendScore = new Intent(getApplicationContext(), Finish.class);
                sendScore.putExtra("CurrentScore", totalCorrect);
                sendScore.putExtra("SendCurrentHighScore", currrentHighScore);  // Send the current high score
                startActivity(sendScore);

            }
        });
    }

}