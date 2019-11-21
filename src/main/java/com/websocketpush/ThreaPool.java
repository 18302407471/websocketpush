package com.websocketpush;

import com.websocketpush.socket.MyWebSocket;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreaPool {

    private static BlockingDeque<String> queue;

    private static List<Thread> listThread;

    public static class Worker extends Thread{

        private ThreaPool pool;

        @Override
        public void run() {
            while (true){
                try {
                    String message = this.pool.queue.take();
//                    System.out.println(message);
                    Map<String,Object> map = new HashMap<>();
                    map.put("key",message);
                    MyWebSocket.broadcast(map);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public ThreaPool(Integer queueSize,Integer threadSize){
        queue = new LinkedBlockingDeque<>(queueSize);
        listThread = new ArrayList<>(threadSize);
        for (int i=0;i<threadSize;i++){
            Worker worker = new Worker();
            worker.start();
            listThread.add(worker);
        }
    }


    public boolean submitTask(String meaasge){
        return this.queue.offer(meaasge);
    }


    public static void main(String[] args) {
        ThreaPool threaPool = new ThreaPool(5,5);
        while (true){
            int i=0;
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            threaPool.submitTask(i+"str");
        }
    }
}
