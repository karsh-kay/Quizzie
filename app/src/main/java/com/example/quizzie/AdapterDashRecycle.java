package com.example.quizzie;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzie.dataStructs.QuizStruct;

import java.util.ArrayList;

public class AdapterDashRecycle extends RecyclerView.Adapter<AdapterDashRecycle.ViewHolder> {
    private final ArrayList<QuizStruct> quizList;
    private OnQuizRowListener gOnQuizRowListener;

    public AdapterDashRecycle(ArrayList<QuizStruct> quizList, OnQuizRowListener onQuizRowListener) {
        this.quizList = quizList;
        this.gOnQuizRowListener = onQuizRowListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row, parent, false);
        view.setBackgroundResource(R.drawable.view_row);
        return new ViewHolder(view, gOnQuizRowListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(quizList.get(position).title);
        holder.due.setText(quizList.get(position).dueDate);
        holder.className.setText(quizList.get(position).className);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView due;
        TextView className;
        OnQuizRowListener onQuizRowListener;

        public ViewHolder(@NonNull View itemView, OnQuizRowListener onQuizRowListener) {
            super(itemView);
            title = itemView.findViewById(R.id.quiz_row_title);
            due = itemView.findViewById(R.id.quiz_row_due);
            className = itemView.findViewById(R.id.quiz_row_class);
            this.onQuizRowListener = onQuizRowListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuizRowListener.onQuizRowClick(getAdapterPosition());
        }
    }

    public interface OnQuizRowListener {
        void onQuizRowClick(int position);
    }
}
