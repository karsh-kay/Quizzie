package com.example.quizzie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragLogin extends Fragment {

    AutoCompleteTextView email;
    AutoCompleteTextView pass;
    TextView forget;
    Button loginB;
    float op = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_frag, container, false);

        email = root.findViewById(R.id.login_email);
        pass = root.findViewById(R.id.login_pass);
        forget = root.findViewById(R.id.login_forget);
        loginB = root.findViewById(R.id.login_button);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget.setTranslationX(800);
        loginB.setTranslationX(800);

        email.setAlpha(op);
        pass.setAlpha(op);
        forget.setAlpha(op);
        loginB.setAlpha(op);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        loginB.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }
}