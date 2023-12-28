package com.company.main3;

import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

public class Washer1 implements Callable<String> {
    Phaser phaser;

    public Washer1(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public String call() {
        System.out.println(Thread.currentThread().getName() + " washing the interior1");
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " washing the interior2");
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " washing the interior3");
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndDeregister();
        return "Washer2 finished his work ";
    }
}
