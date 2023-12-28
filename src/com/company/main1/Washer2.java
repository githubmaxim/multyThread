package com.company.main1;

import java.util.concurrent.Phaser;

public class Washer2  extends Thread{
    Phaser phaser;

    public Washer2(Phaser phaser) {
        this.phaser = phaser;
        start();
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + " washing the exterior1");
        phaser.arriveAndAwaitAdvance();
        System.out.println(currentThread().getName() + " washing the exterior2");
        phaser.arriveAndDeregister();
    }
}
