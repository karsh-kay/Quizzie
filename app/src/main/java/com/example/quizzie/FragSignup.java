package com.example.quizzie;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizzie.requestHandler.RequestServices;


import com.example.quizzie.requestHandler.RetroClient;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragSignup extends Fragment {

    MaterialButtonToggleGroup usrTypeBut;
    AutoCompleteTextView regEmail;
    EditText regPass;
    EditText regPassChk;
    Button signupBut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup_tab_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usrTypeBut = view.findViewById(R.id.signup_user_type);
        regEmail = view.findViewById(R.id.signup_email);
        regPass = view.findViewById(R.id.signup_pass);
        regPassChk = view.findViewById(R.id.signup_pass_check);
        signupBut = view.findViewById(R.id.signup_button);

        signupBut.setOnClickListener(vew -> {
            int btn = usrTypeBut.getCheckedButtonId();
            if(btn == R.id.btn_student) btn = 1;
            else btn = 0;

            signupUser(regEmail.getText().toString(), regPass.getText().toString(), regPassChk.getText().toString(), btn);
        });
    }

    private void signupUser(String em, String p1, String p2, int ty) {
        if(TextUtils.isEmpty(em)) {
            Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(p1)) {
            Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(p2)) {
            Toast.makeText(getActivity(), "Re", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(p1)) {
            Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.equals(p1, p2)) {
            Toast.makeText(getActivity(), "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ty != 0 && ty != 1) {
            Toast.makeText(getActivity(), "Select user type", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestServices requestServices = RetroClient.getRetrofitInstance().create(RequestServices.class);
        Log.e(TAG, String.format("beforeRequest : %s, 00, %s, 00, %s", em, ty, p1));
        Call<String> call = requestServices.signupUser(em, ty, p1);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                switch (Objects.requireNonNull(response.body())) {
                    case "SU-00":
                        Toast.makeText(getActivity(), "Signed up successfully", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "SU-01":
                        Toast.makeText(getActivity(), "Email already exist", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFail: "+ response.body());
                        break;
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