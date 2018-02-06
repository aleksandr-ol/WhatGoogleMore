package com.example.immortal.whatgooglemore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[][] questions_array = {
            {"one", "123"},
            {"two", "234"},
            {"three", "243"},
            {"four", "577"},
            {"five", "2345"},
            {"six", "235467"}
    };

    Button btn1, btn2;
    TextView strike;
    int questions_count = questions_array.length;
    int first_question_index, second_question_index;

    int correctButtonId;
    int correctAnswersCount;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button_first);
        btn2 = (Button) findViewById(R.id.button_second);
        strike = (TextView) findViewById(R.id.strike_textView);

        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        correctAnswersCount = 0;
        initButtons();
    }

    private void initButtons(){
        int variants = 1;
        first_question_index = random.nextInt(questions_count);

        while (variants <= 2) {
            second_question_index = random.nextInt(questions_count);
            if (first_question_index != second_question_index)
                ++variants;
        }
        btn1.setText(questions_array[first_question_index][0]);
        btn2.setText(questions_array[second_question_index][0]);

        if(Integer.parseInt(questions_array[first_question_index][1]) > Integer.parseInt(questions_array[second_question_index][1]))
            correctButtonId = btn1.getId();
        else
            correctButtonId = btn2.getId();
    }

    private View.OnClickListener clickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == correctButtonId)
                correctAnswersCount++;
            else
                correctAnswersCount = 0;

            strike.setText(String.valueOf(correctAnswersCount));
            initButtons();
        }
    };
}
