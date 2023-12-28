package com.company.main2;

import java.util.concurrent.Phaser;

//Использование Phaser с потоками автоматически получаемыми из ForkJoinPool  + подклассы наследующие Thread. Все реализовано в одном классе.
public class Main2 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(12);
        new Washer1(phaser);
        new Washer2(phaser);
        new Washer3(phaser);
        new Washer4(phaser);
        new Washer5(phaser);
        new Washer6(phaser);
        new Washer7(phaser);
        new Washer8(phaser);
        new Washer9(phaser);
        new Washer10(phaser);
        new Washer11(phaser);
        new Washer12(phaser);
    }

    static class Washer1 extends Thread{
        Phaser phaser;

        public Washer1(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 21 washing the interior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 21 washing the interior2");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 21 washing the interior3");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer2 extends Thread{
        Phaser phaser;

        public Washer2(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 22 washing the exterior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 22 washing the exterior2");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer3 extends Thread{
        Phaser phaser;

        public Washer3(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                System.out.println(currentThread().getName() + " 23 washing the wills");
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        }
    }

    static class Washer4 extends Thread{
        Phaser phaser;

        public Washer4(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 24 washing the interior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 24 washing the interior2");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 24 washing the interior3");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer5 extends Thread{
        Phaser phaser;

        public Washer5(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 25 washing the exterior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 25 washing the exterior2");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer6 extends Thread{
        Phaser phaser;

        public Washer6(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                System.out.println(currentThread().getName() + " 26 washing the wills");
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        }
    }

    static class Washer7 extends Thread{
        Phaser phaser;

        public Washer7(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 27 washing the interior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 27 washing the interior2");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 27 washing the interior3");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer8 extends Thread{
        Phaser phaser;

        public Washer8(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 28 washing the exterior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 28 washing the exterior2");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer9 extends Thread{
        Phaser phaser;

        public Washer9(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                System.out.println(currentThread().getName() + " 29 washing the wills");
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        }
    }

    static class Washer10 extends Thread{
        Phaser phaser;

        public Washer10(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 210 washing the interior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 210 washing the interior2");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 210 washing the interior3");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer11 extends Thread{
        Phaser phaser;

        public Washer11(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName() + " 211 washing the exterior1");
            phaser.arriveAndAwaitAdvance();
            System.out.println(currentThread().getName() + " 211 washing the exterior2");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }

    static class Washer12 extends Thread{
        Phaser phaser;

        public Washer12(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                System.out.println(currentThread().getName() + " 212 washing the wills");
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        }
    }
}
