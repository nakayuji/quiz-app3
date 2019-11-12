package com.example.quizapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("page")
public class QuizAppController {
    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();

    @GetMapping("/quiz")
    public String quiz(Model model){
        int index = new Random().nextInt(quizzes.size());//引数が３の場合　０〜２　乱数

        model.addAttribute("quiz", quizzes.get(index));
        return "quiz";
    }
    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("quizzes",quizzes);
        return "list";
    }

    @PostMapping("/create")
    public String create(@RequestParam String question, @RequestParam boolean answer){
       Quiz quiz = new Quiz(question,answer);
//       クイズを追加
       quizzes.add(quiz);

       return "redirect:/page/show";
    }

    //checkメソッド
    //引数はString型のquestion（質問文）、boolean型のanswer（回答）
    //戻り値　正解・不正解かを文字列で返す
    @GetMapping("/check")
    public String check(@RequestParam String question, boolean answer){
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

    @PostMapping("/save")
    public String save(RedirectAttributes attributes){
        try {
            quizFileDao.write(quizzes);
            attributes.addFlashAttribute("successMessage","ファイルに保存しました");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","ファイルの保存に失敗しました");
        }
        return "redirect:/page/show";
    }

    @GetMapping("/load")
    public String load(RedirectAttributes attributes){
        try {
            quizzes = quizFileDao.read();
            attributes.addFlashAttribute("successMessage","ファイルを読み込みました");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","ファイルの読み込みに失敗しました");
        }
        return "redirect:/page/show";
    }
}
