package com.example.quizzie.dataStructs;

public class QuestionStruct {
    public String ques;
    public String optCorr;
    public String optA;
    public String optB;
    public String optC;
    public String optD;
    public String optE;

    public QuestionStruct(String ques, String optCorr, String optA, String optB, String optC, String optD, String optE) {
        this.ques = ques;
        this.optCorr = optCorr;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.optE = optE;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getOptCorr() {
        return optCorr;
    }

    public void setOptCorr(String optCorr) {
        this.optCorr = optCorr;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getOptE() {
        return optE;
    }

    public void setOptE(String optE) {
        this.optE = optE;
    }
}
