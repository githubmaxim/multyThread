package com.company.main3;

import java.util.ArrayList;
import java.util.concurrent.*;

import static java.lang.String.format;

//Использование Phaser с потоками получаемыми из собственного ExecutorService + подклассы наследующие Callable.
//Кроме того в классе Washer2 реализован однопоточный механизм копирования файлов с расширением .java из папки в папку.
//Этот механизм использует для прохода по дереву файлов и при нахождении тут же обработки найденого файла
// метод Files.walkFileTree. Данный метод требует реализации (у нас это класс MyFileFindVisitor) хотя бы одного из 4-х интерфейсов.
public class Main3 {

    private static final int THREAD_POOL_SIZE = 3; //число должно быть не меньше чем максимальное количество потоков на любой из фаз

    public static void main(String[] args) {
        Phaser phaser = new Phaser(THREAD_POOL_SIZE);
        
        ExecutorService execService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CompletionService<String> completionService = new ExecutorCompletionService<>(execService);
        ArrayList<Future<String>> futures = new ArrayList<>();

        futures.add(completionService.submit(new Washer1(phaser)));
        futures.add(completionService.submit(new Washer2(phaser)));
        futures.add(completionService.submit(new Washer3(phaser)));

        execService.shutdown(); //будет выполнено только после окончания работы всех потоков

// Т.к. потоки будут какое то время выполнять свои задачи и только по окончанию всех будут закрыты, то можем пока выполнить какую то задачу
//        try {
//            while (!execService.isTerminated()) {
//                String result = completionService.take().get();
//                System.out.println(format("Result is: %s", result));
//            }
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
