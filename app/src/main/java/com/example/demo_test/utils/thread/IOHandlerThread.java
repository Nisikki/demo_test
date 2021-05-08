package com.example.demo_test.utils.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class IOHandlerThread {
	/**
	 * 使用内部类，解决线程安全问题
	 * @author moo
	 */
	private static class Holder{
		private static IOHandlerThread _instance = new IOHandlerThread();
		static{
			_instance.init();
		}
	}

	public static IOHandlerThread getInstance(){
		return Holder._instance;
	}
	
	private HandlerThread mHandlerThread;
	private Handler mHandler;

	private void init(){
		mHandlerThread = new HandlerThread(IOHandlerThread.class.getSimpleName());
		mHandlerThread.start();
		
		mHandler = new Handler(mHandlerThread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
			}
		};
	}
	
	public Looper getLooper(){
		return mHandlerThread.getLooper();
	}
	
	public Handler getHandler(){
		return this.mHandler;
	}
}
