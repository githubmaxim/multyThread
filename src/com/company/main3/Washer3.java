package com.company.main3;

import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

public class Washer3 implements Callable<String> {
    Phaser phaser;

    public Washer3(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public String call(){
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " washing the wills");
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
        return "Washer3 finished his work ";
    }
}
