package com.example.quizzie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzie.dataStructs.QuizStruct;

import java.util.ArrayList;

public class FragHistory extends Fragment implements AdapterDashRecycle.OnQuizRowListener {
    RecyclerView recyclerView;
    ArrayList<QuizStruct> quizList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.ongoing_frag, container, false);

        recyclerView = root.findViewById(R.id.ongoing_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        for(int i = 0; i < 20; i++) {
            quizList.add(new QuizStruct("id: " + i, "Algorithms for the c" +
                    "lass variable " + i, "Class of DS " + i, "15/05/22, 12:00PM"));
        }

        final AdapterDashRecycle adapter = new AdapterDashRecycle(quizList, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onQuizRowClick(int position) {
        final QuizStruct quizStruct = quizList.get(position);
        Toast.makeText(getActivity(), "Clicked position "+position , Toast.LENGTH_SHORT).show();
    }
}
