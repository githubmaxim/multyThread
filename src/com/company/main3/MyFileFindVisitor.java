package com.company.main3;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

class MyFileFindVisitor extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;

    public MyFileFindVisitor(String pattern) {
        try {
            matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.err.println("Invalid pattern; did you forget to prefix \"glob:\" or \"regex:\"?");
            System.exit(1);
        }
    }

    private void find(Path path) throws IOException {
        Path finishPath = Paths.get("D:\\Java\\example\\src\\classes\\comparator33"); //путь куда копировать пишу тут т.к. не знаю как передать его в верхних "вызывающих" классах
        Path name = path.getFileName();
        if (matcher.matches(name)) {
            Path finishPathAll = finishPath.resolve(path.getFileName());
            if (!Files.exists(finishPath)) {
                try {
                    Files.createDirectory(finishPath);
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            try {
                Files.copy(path, finishPathAll, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(Thread.currentThread().getName() + " Source file " + path +  " To: " + finishPath + " copied successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) throws IOException {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    //Зачем тут реализован еще и этот метод я не знаю!!!!!!!!
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) throws IOException {
        find(path);
        return FileVisitResult.CONTINUE;
    }
}

