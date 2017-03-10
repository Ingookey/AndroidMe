package com.ingoo.ingoos.thread;

import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.ThreadPoolExecutor;  
import java.util.concurrent.TimeUnit;

import android.util.Log;  


public class ThreadPool {  
	public static String TAG = "ingoo/ThreadPool";
	
	private static int sTaskSleepTime = 2;  
    private static int sTaskMaxNumber = 10;  
   
    public static class ThreadTask implements Runnable{   
        private Object mTaskData;  
  
        ThreadTask(Object taskData) {  
            this.mTaskData = taskData;  
        }  
  
        public void run() {    
			Log.d(TAG, "<ThreadTask> start.." + mTaskData);
            try {   
                Thread.sleep(2000);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            mTaskData = null;  
        }  
  
        public Object getTask() {  
            return this.mTaskData;  
        }  
    }     
      
    public static void creatThread() {   
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                					    new ArrayBlockingQueue<Runnable>(3),
                					    new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= sTaskMaxNumber; i++) {  
            try {   
                String task = "task@ " + i;  
    			Log.d(TAG, "<creatThread> task: " + task);
                threadPool.execute(new ThreadTask(task));   
                Thread.sleep(sTaskSleepTime);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
}  