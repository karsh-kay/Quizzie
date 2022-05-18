package com.example.quizzie;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizzie.requestHandler.RequestServices;
import com.example.quizzie.requestHandler.RetroClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        loginB.setOnClickListener(view1 -> {
//            Intent i = new Intent(getActivity(), DashboardActivity.class);
//            startActivity(i);
//            requireActivity().finish();
//        });
        loginB.setOnClickListener(view1 -> loginUser(email.getText().toString(), pass.getText().toString()));
    }

    private void loginUser(String em, String p) {
        if (TextUtils.isEmpty(em)) {
            Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(p)) {
            Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestServices requestServices = RetroClient.getRetrofitInstance().create(RequestServices.class);
        Log.e(TAG, String.format("beforeRequest : %s, 00, %s", em, p));
        Call<String> call = requestServices.loginUser(em, p);
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                switch (Objects.requireNonNull(response.body())) {
                    case "LO-00":
                        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(getActivity(), DashboardActivity.class);
                        startActivity(i);
                        requireActivity().finish();
                        break;
                    case "LO-01":
                        Toast.makeText(getActivity(), "Email doesn't exist", Toast.LENGTH_SHORT).show();
                        break;
                    case "LO-02":
                        Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFail: "+ response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}