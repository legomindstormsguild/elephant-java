package com.smtsoftware.legoguild.elephant.sdk;

import java.util.Date;

/**
 * Created by ukaszek on 16/09/16.
 */
public class Logger {

    public static final void print(String message) {
        System.out.println(new Date().toString() + " | " + message);
    }
}
