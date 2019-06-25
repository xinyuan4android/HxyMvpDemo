package com.example.hxy_baseproject;

/**
 * Created by hxy on  2019/1/23.
 */

import android.support.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author hxy
 * @date 2019/1/23
 */
public class AppExecutor {
    /**
     * 默认线程数
     */
    private int corePoolSize = 3;

    private int maximumPoolSize = 3;
    //保活最长时间
    private int keeyAliveTime = 10;

    private Executor disIO;
    public static volatile AppExecutor appExecutor;

    public static AppExecutor getInstance() {
        if (appExecutor == null) {
            synchronized (AppExecutor.class) {
                if (appExecutor == null) {
                    appExecutor = new AppExecutor();
                }
            }
        }
        return appExecutor;
    }

    private AppExecutor() {
        try {
            int size = Runtime.getRuntime().availableProcessors();
            //通常核心线程数可以设为CPU数量+1，而最大线程数可以设为CPU的数量*2+1。
            corePoolSize = size + 1;
            maximumPoolSize = size * 2 + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyThreadFactory myThreadFactory = new MyThreadFactory("demo-pool-%d");
        disIO = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keeyAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(20), myThreadFactory);
    }

    public Executor getDisIO() {
        return disIO;
    }

    private class MyThreadFactory implements ThreadFactory {
        private String threadName;

        public MyThreadFactory(String threadNameFormat) {
            this.threadName = threadNameFormat;
        }

        private int num = 0;

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(String.format(threadName, ++num));
            return thread;
        }
    }
}
