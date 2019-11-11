package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizAppController {
    private List<Quiz> quizzes = new ArrayList<>();
//戻り値はList＜Quiz＞型
    //引数はない
    @GetMapping("/show")
    public List<Quiz> show() {
        return quizzes;
    }
//createメソッドには戻り値はない
    //引数はString型のquestion、boolean型のanswer
    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer){
       Quiz quiz = new Quiz(question,answer);
//       クイズを追加
       quizzes.add(quiz);
    }

    //checkメソッド
    //引数はString型のquestion（質問文）、boolean型のanswer（回答）
    //戻り値　正解・不正解かを文字列で返す
    @GetMapping("/check")
    public String check(@RequestParam String question, boolean answer){
        //TODO:回答が正しいかどうかを判断して結果を返す
        //指定されたquestionを登録済みのクイズから検索する
        for (Quiz quiz: quizzes) {
            //もしクイズが見つかったら
            if(quiz.getQuestion().equals(question)){
            //登録されているanswerが一致している場合、「正解」と返され
                if (quiz.isAnswer() == answer) {
                    return "正解！";
                } else {
                    return "不正解！";
                }
            }
            //一致してなければ「不正解」と返される
        }
        //クイズが見つからない場合は「問題がありません」と返す
        return "問題がありません";
    }
}
