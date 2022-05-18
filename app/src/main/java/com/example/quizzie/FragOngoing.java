package com.example.quizzie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzie.dataStructs.QuizStruct;

import java.util.ArrayList;

public class FragOngoing extends Fragment implements AdapterDashRecycle.OnQuizRowListener {
    RecyclerView recyclerView;
    ArrayList<QuizStruct> quizList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.ongoing_frag, container, false);

        recyclerView = root.findViewById(R.id.ongoing_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        // This will be fetched from api, created a dummy lists here
        quizList.add(new QuizStruct("12312", "System Modelling Quiz 1", "Software Engineering", "25/05/22, 12:00PM"));
        quizList.add(new QuizStruct("12313", "Mean and Variance of Distribution Quiz 1", "Probability and Statistics", "26/05/22, 12:00PM"));
        quizList.add(new QuizStruct("12314", "Analysis Modelling Quiz 1", "Software Engineering", "29/05/22, 12:00PM"));

        for(int i = 0; i < 15; i++) {
            quizList.add(new QuizStruct("id: " + i, "Data Structures Algorithms Quiz " + i, "Class of DS " + i, "30/05/22, 12:00PM"));
        }

        final AdapterDashRecycle adapter = new AdapterDashRecycle(quizList, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onQuizRowClick(int position) {
        String id = quizList.get(position).id;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        final View popupView = getLayoutInflater().inflate(R.layout.quiz_details_popup, null);

        TextView title = popupView.findViewById(R.id.textViewTitle);
        TextView className = popupView.findViewById(R.id.textViewClass);
        TextView desc = popupView.findViewById(R.id.textViewDesc);
        TextView attempts = popupView.findViewById(R.id.textViewAttempts);
        TextView timer = popupView.findViewById(R.id.textViewTimer);
        TextView dueString = popupView.findViewById(R.id.textViewDue);
        Button attemptsBut = popupView.findViewById(R.id.button_attemptsView);
        Button startBut = popupView.findViewById(R.id.button_startQuiz);

        // This will be fetched from api with id, created a dummy object here
        String s1 = "System modeling is the process of developing abstract models of a system, with each model presenting a different view or perspective of that system. This is a brief quiz on this topic";
        String s2 = "Mean of the distribution is “the expected mean” and the variance of the distribution is “the expected variance” of a very large sample of outcomes from the distribution. This is a brief quiz on this topic";
        String s3 = "Analysis Model is a technical representation of the system. It acts as a link between system description and design model. This is a brief quiz on this topic";
        int[] a1 = {100};
        int[] a2 = {};
        int[] a3 = {76, 98};
        int[] scoreHis = {77, 96, 83};
        QuizStruct quizStudentView1 = new QuizStruct("12312", "System Modelling Quiz 1", s1, "Software Engineering", "25/05/22, 12:00PM", "15/05/22, 12:00PM", 2, 1, 1, a1, true);
        QuizStruct quizStudentView2 = new QuizStruct("12313", "Mean and Variance of Distribution Quiz 1", s2, "Probability and Statistics", "27/05/22, 12:00PM", "15/05/22, 12:00PM", 2, 0, 0, a2, true);
        QuizStruct quizStudentView3 = new QuizStruct("12314", "Analysis Modelling Quiz 1", s3, "Software Engineering", "29/05/22, 12:00PM", "15/05/22, 12:00PM", 2, 1, 2, a3, true);
        QuizStruct qz;
        String ID = null;

        if(id.equals("12312") || id.equals("12313") || id.equals("12314")) {
            if(id.equals("12312")) qz = quizStudentView1;
            else if(id.equals("12313")) qz = quizStudentView2;
            else qz = quizStudentView3;

            title.setText(qz.title);
            className.setText(qz.className);
            desc.setText(qz.desc);

            int remaining = qz.attemptsGiven - qz.attemptCount;
            attempts.setText(new StringBuilder().append("Attempts: ").append(qz.attemptsGiven).append(" (").append(remaining).append(" remaining)").toString());
            dueString.setText(new StringBuilder().append("Due: ").append(qz.dueDate).toString());

            String timerText;
            switch (qz.timerDuration) {
                case 0: timerText = "Off";
                    break;

                case 1: timerText = "Slow";
                    break;

                case 2: timerText = "Normal";
                    break;

                default: timerText = "Fast";
            }
            timer.setText(new StringBuilder().append("Timer: ").append(timerText).toString());

            scoreHis = qz.scoreHistory;
            ID = qz.id;

            if(remaining == 0) {
                startBut.setVisibility(View.GONE);
            } else if (remaining == qz.attemptsGiven) {
                attemptsBut.setVisibility(View.GONE);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) startBut.getLayoutParams();
                params.topToBottom = R.id.separator_5;
                startBut.requestLayout();
            }
        }

        builder.setView(popupView);
        AlertDialog dialog = builder.create();
        dialog.show();

        int[] finalScoreHis = scoreHis;
        attemptsBut.setOnClickListener(view -> {
            final View popupView1 = getLayoutInflater().inflate(R.layout.quiz_attempts_popup, null);
            dialog.cancel();

            TextView title1 = popupView1.findViewById(R.id.textViewTitle1);
            TextView className1 = popupView1.findViewById(R.id.textViewClass1);
            TextView desc1 = popupView1.findViewById(R.id.textViewDesc1);

            StringBuilder attemptList = new StringBuilder();
            for(int i = 0; i < finalScoreHis.length; i++) {
                attemptList.append("Attempt ").append(i + 1).append(": ").append(finalScoreHis[i]).append("%\n\n");
            }

            title1.setText(title.getText());
            className1.setText(className.getText());
            desc1.setText(attemptList.toString());

            builder.setView(popupView1);
            AlertDialog dialog1 = builder.create();
            dialog1.show();
        });

        String finalID = ID;
        startBut.setOnClickListener(view -> new AlertDialog.Builder(view.getContext())
                .setTitle("Starting Quiz")
                .setMessage("Are you sure you want to proceed?")
                .setPositiveButton("Yes", (dialog1, id1) -> {
                    dialog.cancel();
                    Intent i = new Intent(view.getContext(), QuizMain.class);
                    i.putExtra("id", finalID);
                    startActivity(i);
                    requireActivity().finish();
                })
                .setNegativeButton("No", (dialog1, id1) -> dialog1.cancel())
                .setCancelable(false)
                .show());
    }
}
