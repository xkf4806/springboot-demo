package com.example.demo;

import java.util.concurrent.*;

/**
 * @author xj.x@hnair.com
 * @date 2018-07-02 11:23
 **/
public class ThreadPoolExitDemo {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        for(int i = 1; i <= 100 ; i++){
            workQueue.add(new Task(String.valueOf(i)));
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, workQueue);
        executor.execute(new Task("0"));
        executor.shutdown();
        System.out.println("workQueue size = " + workQueue.size() + " after shutdown");
    }

    static class Task implements Runnable{
        String name;

        Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for(int i = 1; i <= 10; i++){
                System.out.println("task " + name + " is running");
            }
            System.out.println("task " + name + " is over");
        }
    }

}
