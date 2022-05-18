package com.example.quizzie.dataStructs;

public class SignupRequest {
    private String email;
    private int type;
    private String pass;

    public SignupRequest(String email, int type, String pass) {
        this.email = email;
        this.type = type;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
