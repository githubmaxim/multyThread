package com.company.main1;

import java.util.concurrent.Phaser;

public class Washer1 extends Thread{
    Phaser phaser;

    public Washer1(Phaser phaser) {
        this.phaser = phaser;
        start();
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + " washing the interior1");
        phaser.arriveAndAwaitAdvance();
        System.out.println(currentThread().getName() + " washing the interior2");
        phaser.arriveAndAwaitAdvance();
        System.out.println(currentThread().getName() + " washing the interior3");
        phaser.arriveAndDeregister();
    }
}
