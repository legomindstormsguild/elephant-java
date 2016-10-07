package com.smtsoftware.legoguild.elephant;

public class App {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                Elephant.getInstance().stop();

            }
        }));

        Elephant.getInstance().start();
    }
}
