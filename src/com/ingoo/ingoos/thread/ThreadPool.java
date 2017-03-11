package com.ingoo.ingoos.thread;

import com.ingoo.ingoos.utils.Singleton;

import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.ThreadPoolExecutor;  
import java.util.concurrent.TimeUnit;

import android.util.Log;  


public class ThreadPool {  
	public static String TAG = "ingoo/ThreadPool";

	private static int mCorePoolSize = 2;
	private static int mMaxPoolSize = 4;
	private static int mKeepIdleTime = 3;

	// The singleton thread pool
	private volatile static ThreadPoolExecutor mThreadPool;

	private ThreadPool() {
	}

	public static ThreadPoolExecutor getThreadPool() {
		if (null == mThreadPool) {
			synchronized (Singleton.class) {
				if (null == mThreadPool) {
					 mThreadPool = new ThreadPoolExecutor(mCorePoolSize, mMaxPoolSize,
								   mKeepIdleTime, TimeUnit.SECONDS,
    					    	   new ArrayBlockingQueue<Runnable>(3),
    					    	   new ThreadPoolExecutor.DiscardOldestPolicy()); 
				}
			}
		}
		return mThreadPool;
	}
    
    public void execute(Runnable runnable) {
    	try {    
			Log.d(TAG, "<execute> runnable: " + runnable);
			mThreadPool.execute(runnable);   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
   
     
}  