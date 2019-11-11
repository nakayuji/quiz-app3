package com.example.quizapp;

public class Quiz {


    /**
     * 問題文
     */
    private String question;

    /**
    * クイズの正解
     */
    private boolean answer;

    public  Quiz(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return  question;
    }

    public  boolean isAnswer(){
        return  answer;
    }
    //Junit
    @Override
    public String toString() {
        //条件演算子
        String marubatu = answer ? "◯" : "×";
        //以下と意味は同じ
//        String marubatu
//        if (answer) {
//            marubatu = "◯";
//        }else {
//            marubatu = "×";
//        }
        return question + " " + marubatu;
    }
    //line・・・問題文  ◯
    public static Quiz fromString(String line) {
        String question = line.substring(0,line.length() - 2);
        boolean answer = line.endsWith("◯");

        return new Quiz(question, answer);
    }
}
