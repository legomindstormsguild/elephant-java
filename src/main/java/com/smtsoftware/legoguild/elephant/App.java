package com.smtsoftware.legoguild.elephant;

/**
 * Hello world!
 */
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
