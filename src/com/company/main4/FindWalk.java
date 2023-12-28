package com.company.main4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Класс который обходит дерево предложенного каталога используя метод Files.walk(), который в свою очередь передает
// найденные в этом каталоге файлы в Stream<Path>, и из них мы уже нужные себе отфильтровываем
public class FindWalk implements Callable<List<Path>> {
    Path path;
    String fileExtension;

    public FindWalk(Path path, String fileExtension){
        this.path = path;
        this.fileExtension = fileExtension;
    }

    @Override
    public List<Path> call() throws Exception {
        if (!Files.isDirectory(path))  {  throw new IllegalArgumentException("Path must be a directory!");   }
        List<Path> result = null;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
