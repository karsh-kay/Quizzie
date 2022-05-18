package com.example.quizzie;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterDashList extends FragmentStatePagerAdapter {
    private Context context;
    int numTabs;

    public AdapterDashList(FragmentManager fm, Context context, int numTabs) {
        super(fm);
        this.context = context;
        this.numTabs = numTabs;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragOngoing();
            case 1:
                return new FragUpcoming();
            case 2:
                return new FragHistory();
            default:
                return null;
        }
    }
}
































//public class AdapterDashList extends RecyclerView.Adapter<AdapterDashList.ViewHolder> {
//    private final ArrayList<QuizStruct> quizList;
//
//    public AdapterDashList(ArrayList<QuizStruct> quizList) {
//        this.quizList = quizList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row, parent, false);
//        view.setBackgroundResource(R.drawable.view_row);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.title.setText(quizList.get(position).title);
//        holder.due.setText(quizList.get(position).due);
//        holder.className.setText(quizList.get(position).className);
//    }
//
//    @Override
//    public int getItemCount() {
//        return quizList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView title;
//        TextView due;
//        TextView className;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            title = itemView.findViewById(R.id.quiz_row_title);
//            due = itemView.findViewById(R.id.quiz_row_due);
//            className = itemView.findViewById(R.id.quiz_row_class);
//        }
//    }
//}
