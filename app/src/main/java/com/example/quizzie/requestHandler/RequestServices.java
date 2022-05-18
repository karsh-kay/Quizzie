package com.example.quizzie.requestHandler;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestServices {
    @POST("register")
    @FormUrlEncoded
    Call<String> signupUser(@Field("email") String email,
                            @Field("type") int type,
                            @Field("pass") String pass);

    @POST("login")
    @FormUrlEncoded
    Call<String> loginUser(@Field("email") String email,
                           @Field("pass") String pass);
}
