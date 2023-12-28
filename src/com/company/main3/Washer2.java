package com.company.main3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

public class Washer2 implements Callable<String> {
    Phaser phaser;

    public Washer2(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public String call() {

        Path startPath = Paths.get("D:\\Java\\example\\src\\classes\\comparator"); //Введите сюда путь к каталогу для поиска
        String pattern = "glob:*.java";//Строка с glob-шаблоном
        //String pattern = "regex:\\S+\\.java"; //Строка с regex-шаблоном
        try {
            Files.walkFileTree(startPath, new MyFileFindVisitor(pattern)); //в классе MyFileFindVisitor реализованы 2(нужно хотя бы 1) из 4 методов класса SimpleFileVisitor
            System.out.println(Thread.currentThread().getName() + "File search completed!");
        } catch (IOException e) { e.printStackTrace(); }
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + " washing the exterior2");
        phaser.arriveAndDeregister();
        return "Washer2 finished his work ";
    }
}
