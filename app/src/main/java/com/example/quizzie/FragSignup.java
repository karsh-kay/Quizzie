package com.example.quizzie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragSignup extends Fragment {
    EditText email;
    float op = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_frag, container, false);

        email = root.findViewById(R.id.signup_email);
        email.setTranslationX(800);
        email.setAlpha(op);
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        return root;
    }


}
