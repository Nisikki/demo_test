package com.example.demo_test.base;

public class SongguoNative {

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    static {
        System.loadLibrary("songguo-native");
    }

    public static native String getEncryption(String str);

    public static native String stringFromJNI();
}
