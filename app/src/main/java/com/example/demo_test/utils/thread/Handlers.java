package com.example.demo_test.utils.thread;

import android.os.Handler;
import android.os.Looper;

public class Handlers {
    static final Handler sUIHandler = new Handler(Looper.getMainLooper());
    static final Handler sIOHandler = IOHandlerThread.getInstance().getHandler();
    static final Handler sBackgroundHandler = BackgroundHandlerThread.getInstance().getHandler();
}
