package com.company.main4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Создаем директорию для копирования (если такой еще нет)
public class CreateDirectory implements Runnable {
    private Path finishPath;
    public CreateDirectory(Path finishPath){
        this.finishPath = finishPath;
    }

    public void run() {
        if (!Files.exists(finishPath)) {
            try {
                Files.createDirectory(finishPath);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

}
