package com.company.main4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CopyFile implements Callable<String> {
    private final List<Path> paths;
    private final Path finishPath;
    private final int from;
    private final int to;
    public CopyFile (List<Path> paths, Path finishPath, int from, int to) {
        this.paths = paths;
        this.finishPath = finishPath;
        this.from = from;
        this.to = to;
    }
    @Override
    public String call () throws Exception {
        String result = new String();
        List<String> arrayResult = new ArrayList<>(paths.size());

        for(int i = from; i < to; i++) {
            try {
                Path path = paths.get(i);
                Path finishPathAll = finishPath.resolve(path.getFileName());
                try {
                    Files.copy(path, finishPathAll, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("\"" + Thread.currentThread().getName() + "\"" + " Source file " + "\"" + path + "\"" + " To: " + "\"" + finishPath + "\"" + " copied successfully");
                    arrayResult.add("\"" + Thread.currentThread().getName() + "\" " + " received data of cycle " + i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //"IndexOutOfBoundsException" вызовется, когда в массиве не будет элемента с таким номером (когда количество
                // обрабатываемых файлов не будет кратно количеству потоков и на последнем шаге цикла часть потоков останется без файлов).
                //И такую ситуацию мы обрабатываем отсутствием любых действий, ничего не передавая в List т.к. информации на этом шаге не будет.
            } catch (IndexOutOfBoundsException e){}
        }

        for (String i:arrayResult) { result += " [" + i + "]"; }
        return result;
    }
}
