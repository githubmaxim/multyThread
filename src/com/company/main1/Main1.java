package com.company.main1;

import java.util.concurrent.Phaser;


//Использование Phaser с потоками автоматически получаемыми из ForkJoinPool + подклассы наследующие Thread
public class Main1 {

    private static final int THREAD_POOL_SIZE = 3;

    public static void main(String[] args) {
        Phaser phaser = new Phaser(THREAD_POOL_SIZE);

        new Washer1(phaser);
        new Washer2(phaser);
        new Washer3(phaser);
    }
}
