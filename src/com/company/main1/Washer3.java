package com.company.main1;

import java.util.concurrent.Phaser;

public class Washer3  extends Thread{
    Phaser phaser;

    public Washer3(Phaser phaser) {
        this.phaser = phaser;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(currentThread().getName() + " washing the wills");
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
    }
}
