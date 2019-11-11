package com.example.quizapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Dao・・・・Date access object
public class QuizFileDao {

    private static final String FILE_PATH = "quizzez.txt";

    public void write(List<Quiz> quizzes) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Quiz quiz:quizzes){
            lines.add(quiz.toString());
        }


        Path path = Paths.get(FILE_PATH);

        Files.write(path, lines);
    }
}
