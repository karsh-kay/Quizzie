package com.example.quizzie;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzie.dataStructs.QuestionStruct;
import com.example.quizzie.dataStructs.QuizStruct;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizMain extends AppCompatActivity {
    private View decorView;
    CountDownTimer countDownTimer;
    int timerVal;
    int maxTimer;
    ProgressBar progressBar;
    List<QuestionStruct> quesStruct;
    QuizStruct qP;
    ArrayList<QuestionStruct> xqP;
    Button bNext;
    TextView textQ, textA, textB, textC, textD, textE;
    MaterialCardView cardQ, cardA, cardB, cardC, cardD, cardE;
    QuestionStruct current;
    int Score = 0;
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(i -> {
            if (i == 0) {
                decorView.setSystemUiVisibility(hideSystem());
            }
        });

        int[] a1 = {100};
        int[] a2 = {};
        setContentView(R.layout.quiz_main);

        getAllElements();

        ArrayList<QuestionStruct> questionsList1 = new ArrayList<>();
        questionsList1.add(new QuestionStruct("Which perspective in system modelling shows the system or data architecture?", "Structural perspective", "Structural perspective", "Behavioral perspective", "External perspective", "All of the mentioned", "0xx!!xx0"));
        questionsList1.add(new QuestionStruct("Which model in system modelling depicts the static nature of the system?", "Structural Model", "Behavioral Model", "Context Model", "Data Model", "Structural Model", "0xx!!xx0"));
        questionsList1.add(new QuestionStruct("Which model in system modelling depicts the dynamic behaviour of the system?", "Behavioral Model", "Context Model", "Behavioral Model", "Data Model", "Object Model", "0xx!!xx0"));
        questionsList1.add(new QuestionStruct("Activity diagrams are used to model the processing of data.", "True", "True", "False", "0xx!!xx0", "0xx!!xx0", "0xx!!xx0"));

        ArrayList<QuestionStruct> questionsList2 = new ArrayList<>();
        questionsList2.add(new QuestionStruct("If the probability of hitting the target is 0.4, find mean and variance.", "0.4, 0.24", "0.4, 0.24", "0.6, 0.24", "0.4, 0.16", "0.6, 0.16", "0xx!!xx0"));
        questionsList2.add(new QuestionStruct("What is the mean and variance for standard normal distribution?", "Mean is 0 and variance is 1", "Mean is 0 and variance is 1", "Mean is 1 and variance is 0", "Mean is 0 and variance is ∞", "Mean is ∞ and variance is 0", "0xx!!xx0"));
        questionsList2.add(new QuestionStruct("Mean and variance of Poisson’s distribution is the same.", "True", "True", "False", "0xx!!xx0", "0xx!!xx0", "0xx!!xx0"));

        QuizStruct quizProp1 = new QuizStruct("12312", "System Modelling Quiz 1", "lol", "Software Engineering", "25/05/22, 12:00PM", "15/05/22, 12:00PM", 2, 1, 1, a1, true);
        QuizStruct quizProp2 = new QuizStruct("12313", "Mean and Variance of Distribution Quiz 1", "lol", "Probability and Statistics", "27/05/22, 12:00PM", "15/05/22, 12:00PM", 2, 2, 0, a2, true);

        String quizID = getIntent().getExtras().getString("id");
        if (quizID == null) {
            Toast.makeText(this, "Demo Here", Toast.LENGTH_SHORT).show();
            return;
        }
        if (quizID.equals("12312")) {
            qP = quizProp1;
            xqP = questionsList1;
        } else {
            qP = quizProp2;
            xqP = questionsList2;
        }

        quesStruct = xqP;
        Collections.shuffle(quesStruct);
        current = quesStruct.get(index);

        assignData();
    }

    private void assignData() {
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
        cardE.setClickable(true);
        cardA.setCardBackgroundColor(getResources().getColor(R.color.option));
        cardB.setCardBackgroundColor(getResources().getColor(R.color.option));
        cardC.setCardBackgroundColor(getResources().getColor(R.color.option));
        cardD.setCardBackgroundColor(getResources().getColor(R.color.option));
        cardE.setCardBackgroundColor(getResources().getColor(R.color.option));
        cardC.setVisibility(View.VISIBLE);
        cardD.setVisibility(View.VISIBLE);
        cardE.setVisibility(View.VISIBLE);

        textQ.setText(current.getQues());
        textA.setText(current.getOptA());
        textB.setText(current.getOptB());
        textC.setText(current.getOptC());
        textD.setText(current.getOptD());
        textE.setText(current.getOptE());

        if(textC.getText().equals("0xx!!xx0")) cardC.setVisibility(View.GONE);
        if(textD.getText().equals("0xx!!xx0")) cardD.setVisibility(View.GONE);
        if(textE.getText().equals("0xx!!xx0")) cardE.setVisibility(View.GONE);

        bNext.setText(new StringBuilder().append(index + 1).append("/").append(quesStruct.size()).toString());
        bNext.setEnabled(false);

        if(qP.timerDuration != 0) {
            if (qP.timerDuration == 1) {
                progressBar.setMax(20);
                timerVal = 20;
                maxTimer = 20000;
            } else if (qP.timerDuration == 2) {
                progressBar.setMax(60);
                timerVal = 60;
                maxTimer = 60000;
            } else if (qP.timerDuration == 3) {
                progressBar.setMax(120);
                timerVal = 120;
                maxTimer = 120000;
            }

            countDownTimer = new CountDownTimer(maxTimer, 1000) {
                @Override
                public void onTick(long l) {
                    timerVal = timerVal - 1;
                    progressBar.setProgress(timerVal);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(getBaseContext(), "Time's up, move to next question", Toast.LENGTH_SHORT).show();
                    moveFor();
                }
            }.start();
        }
    }

    private void getAllElements() {
        textQ = findViewById(R.id.ques_textCard);
        textA = findViewById(R.id.a_textCard);
        textB = findViewById(R.id.b_textCard);
        textC = findViewById(R.id.c_textCard);
        textD = findViewById(R.id.d_textCard);
        textE = findViewById(R.id.e_textCard);

        progressBar = findViewById(R.id.determinateBar);
        bNext = findViewById(R.id.button_next);

        cardQ = findViewById(R.id.question_card);
        cardA = findViewById(R.id.option_a);
        cardB = findViewById(R.id.option_b);
        cardC = findViewById(R.id.option_c);
        cardD = findViewById(R.id.option_d);
        cardE = findViewById(R.id.option_e);
    }

    private int hideSystem() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    public void moveFor() {
        countDownTimer.cancel();

        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
        cardE.setClickable(false);

        bNext.setEnabled(true);
        if(index == quesStruct.size() - 1) {
            bNext.setText("Finish");
        } else {
            bNext.setText("Next");
        }

        bNext.setOnClickListener(view -> {
            index++;
            if(index < quesStruct.size()) {
                current = quesStruct.get(index);
                assignData();
            } else if(index == quesStruct.size()) {
                endQuiz();
            }
        });
    }

    public void endQuiz() {
        progressBar.setVisibility(View.INVISIBLE);
        cardQ.setVisibility(View.INVISIBLE);
        cardA.setVisibility(View.INVISIBLE);
        cardB.setVisibility(View.INVISIBLE);
        cardC.setVisibility(View.INVISIBLE);
        cardD.setVisibility(View.INVISIBLE);
        cardE.setVisibility(View.INVISIBLE);
        bNext.setVisibility(View.INVISIBLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.result_popup, null);
        builder.setView(popupView);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView tv = popupView.findViewById(R.id.result_Desc);
        tv.setText(new StringBuilder().append(Score).append("/").append(quesStruct.size()).toString());
        Button btn = popupView.findViewById(R.id.button_result_back);
        btn.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), DashboardActivity.class);
            dialog.cancel();
            startActivity(i);
            finish();
        });
    }

    public void aClick(View view) {
        if(current.getOptCorr().contentEquals(textA.getText())) {
            Score++;
            cardA.setCardBackgroundColor(getResources().getColor(R.color.correct));
        } else {
            cardA.setCardBackgroundColor(getResources().getColor(R.color.incorrect));
        }
        moveFor();
    }

    public void bClick(View view) {
        if(current.getOptCorr().contentEquals(textB.getText())) {
            Score++;
            cardB.setCardBackgroundColor(getResources().getColor(R.color.correct));
        } else {
            cardB.setCardBackgroundColor(getResources().getColor(R.color.incorrect));
        }
        moveFor();
    }

    public void cClick(View view) {
        if(current.getOptCorr().contentEquals(textC.getText())) {
            Score++;
            cardC.setCardBackgroundColor(getResources().getColor(R.color.correct));
        } else {
            cardC.setCardBackgroundColor(getResources().getColor(R.color.incorrect));
        }
        moveFor();
    }

    public void dClick(View view) {
        if(current.getOptCorr().contentEquals(textD.getText())) {
            Score++;
            cardD.setCardBackgroundColor(getResources().getColor(R.color.correct));
        } else {
            cardD.setCardBackgroundColor(getResources().getColor(R.color.incorrect));
        }
        moveFor();
    }

    public void eClick(View view) {
        if(current.getOptCorr().contentEquals(textE.getText())) {
            Score++;
            cardE.setCardBackgroundColor(getResources().getColor(R.color.correct));
        } else {
            cardE.setCardBackgroundColor(getResources().getColor(R.color.incorrect));
        }
        moveFor();
    }

}
