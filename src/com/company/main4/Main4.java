package com.company.main4;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

//Тут реализован многопоточный механизм работы с файлами:
// 1. Заходим в необходимую (заданную нами) папку, находим там файлы с необходимым расширением из путей к которым делаем коллекцию.
// 2. Если такие файлы в папке есть, то создаем новую папку для копирования.
// 3. Разбиваем коллекцию путей файлов на группы (в зависимости от заданного нами количества потоков) и копируем каждый файл в новую папку
// потоком которому досталась эта группа файлов(имитируя какую то работу с каждым файлом). + На каждом шаге записываем в коллекцию String-ов текстовые
// сообщения(имитируя результат работы с каждым файлом), которые после окончания работы потока со всей своей группой путей объединяем и
// присваиваем одной переменной. Данная переменная уже передается как результат работы потока со всей своей группой файлов главному методу.
// 4. Главный метод: а). в случае использования "CompletionService" просто закрывает работу пула потоков. б) в случае использования
// "ListeningExecutorService" закрывает работу пула потоков + обрабатывает результаты всех потоков только после завершения работы всех потоков.
public class Main4 {
    static final int THREAD_POOL_SIZE = 4;

    public static void main(String[] args) throws Exception {
        //Пишем откуда и куда копировать файлы какого расширения
        Path sourcePath = Paths.get("D:\\Java\\example\\src\\classes\\exception");
//        Path sourcePath = Paths.get("D:\\Java\\example\\src\\classes\\exception");
        Path finishPath = Paths.get("D:\\Java\\example\\src\\classes\\exception22");
        String fileExtension = ".java";

        //Создаем коллекцию с путями к файлам нужного нам расширения, используя метод Files.walk() (который обходит дерево предложенного
        // каталога и передает из него файлы в Stream<Path>, из них мы уже нужные себе отфильтровываем)
        List<Path> paths = new FindWalk(sourcePath, fileExtension).call();

        if (!paths.isEmpty()) {
            int quantityAllFiles = paths.size();  //Получаем количество найденных  файлов
            System.out.println("quantity founded files " + quantityAllFiles);

            int quantityFilesOnThread =(int) Math.ceil((double) quantityAllFiles / (double) THREAD_POOL_SIZE);  //Получаем количество файлов на каждый поток (вначале переводим числа из int в double, т.к. иначе дроби не понимает)
            System.out.println("maximum quantity files on every thread " + quantityFilesOnThread);

            ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            //для CompletionService (используем если задачи не связаны друг с другом, иначе теряется смысл освобождения потока для
            // следующей задачи не по очередности, а по тому какая быстрее выполнится)
//            CompletionService<String> completionService = new ExecutorCompletionService<>(service);
            //для ListeningExecutorService из Guava(используем если необходимо будет выполнить какие-то действия только по окончанию работы
            // над всеми поставленными потокам задачами). Можно использовать для этого также механизм CompletableFuture.
            ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);

            //создаем новую папку для копирования, если такой еще нет
            new CreateDirectory(finishPath).run();

            //Дальше в цикле "for" перебираем полученную коллекцию в собственных потоках completionService либо listeningExecutorService,
            // выполняя над каждым файлом необходимый набор действий (у нас будет просто копирования файла из папки в папку и
            // передача на выход текстовой информации)
            int from = 0;
            int to = quantityFilesOnThread;
            //для ListeningExecutorService
            //механизм для автоматического создания с собственным именем на каждом шаге чего-то, что будет иметь в себе результаты работы потока и
            // потом может быть вызвано для перебора и обработки (у нас используется для этого массив и его элементы)
            ListenableFuture<String>[] listenableFuturesArray = new ListenableFuture[THREAD_POOL_SIZE];

            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                CopyFile copyFile = new CopyFile(paths, finishPath, from, to);

                //для CompletionService
//                completionService.submit(copyFile);
                //для ListeningExecutorService
                listenableFuturesArray[i] = listeningExecutorService.submit(copyFile);

                from = to;
                to += quantityFilesOnThread;
            }

            service.shutdown();

            //для ListeningExecutorService
            //Чтобы обработать информацию полученную от всех потоков, количество элементов вложенных в "allAsList" должно быть равно THREAD_POOL_SIZE
            String greeting = (String) Futures.allAsList(listenableFuturesArray[0], listenableFuturesArray[1], listenableFuturesArray[2], listenableFuturesArray[3]).get().stream().collect(Collectors.joining(" "));
            System.out.println(greeting);
        } else {
            System.out.println("Files with extension \""  + fileExtension + "\" is absent");
        }
    }
}
