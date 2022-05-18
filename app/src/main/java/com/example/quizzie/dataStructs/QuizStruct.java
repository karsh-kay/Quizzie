package com.example.quizzie.dataStructs;

public class QuizStruct {
    public String id;
    public String title;
    public boolean isActive;
    public String className;
    public String dueDate;
    public String startDate;

    public int participatedCount;
    public int participantsCount;
    public int attemptsGiven;
    public int timerDuration;
    public String desc;

    public int attemptCount;
    public int[] scoreHistory;

    //Constructor for dashboard
    public QuizStruct(String id, String title, String className, String dueDate) {  // add start date here
        this.id = id;
        this.title = title;
        this.className = className;
        this.dueDate = dueDate;
    }

    //Constructor for Teacher's preview
    public QuizStruct(String id, String title, boolean isActive, int participantsCount, String className, String dueDate, String startDate, int participatedCount, int attemptsGiven, int timerDuration, String desc) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.participantsCount = participantsCount;
        this.className = className;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.participatedCount = participatedCount;
        this.attemptsGiven = attemptsGiven;
        this.timerDuration = timerDuration;
        this.desc = desc;
    }

    //Constructor for Student's preview
    public QuizStruct(String id, String title, String desc, String className, String dueDate, String startDate, int attemptsGiven, int timerDuration, int attemptCount, int[] scoreHistory, boolean isActive) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.className = className;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.attemptsGiven = attemptsGiven;
        this.timerDuration = timerDuration;
        this.attemptCount = attemptCount;
        this.scoreHistory = scoreHistory;
        this.desc = desc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setScoreHistory(int[] scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getParticipatedCount() {
        return participatedCount;
    }

    public void setParticipatedCount(int participatedCount) {
        this.participatedCount = participatedCount;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    public int getAttemptsGiven() {
        return attemptsGiven;
    }

    public void setAttemptsGiven(int attemptsGiven) {
        this.attemptsGiven = attemptsGiven;
    }

    public int getTimerDuration() {
        return timerDuration;
    }

    public void setTimerDuration(int timerDuration) {
        this.timerDuration = timerDuration;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
