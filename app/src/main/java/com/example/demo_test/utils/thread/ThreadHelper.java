package com.example.demo_test.utils.thread;

import android.os.Looper;


public class ThreadHelper {

    public static void runOnUiThread(Runnable run) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            run.run();
        } else {
            Handlers.sUIHandler.post(run);
        }
    }

    public static void runOnUiThread(Runnable run, long delayMillis) {
        Handlers.sUIHandler.postDelayed(run, delayMillis);
    }

    public static void runOnIOThread(Runnable run) {
        if (Looper.myLooper() == Handlers.sIOHandler.getLooper()) {
            run.run();
        } else {
            Handlers.sIOHandler.post(run);
        }
    }

    public static void runOnIOThread(Runnable run, long delayMillis) {
        Handlers.sIOHandler.postDelayed(run, delayMillis);
    }

    public static void runOnBackgroundThread(Runnable run) {
        if (Looper.myLooper() == Handlers.sBackgroundHandler.getLooper()) {
            run.run();
        } else {
            Handlers.sBackgroundHandler.post(run);
        }
    }

    public static void runOnBackgroundThread(Runnable run, long delayMillis) {
        Handlers.sBackgroundHandler.postDelayed(run, delayMillis);
    }

}
