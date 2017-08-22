package com.ssl.questionnaire.bean;

import java.util.List;

public class Question {
    private int id;
    private String number;
    private int type;
    private List<String> question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }

    public enum TYPE {
        RADIO,
        CHECK,
        JUDGE,
        QA;
    }
}
