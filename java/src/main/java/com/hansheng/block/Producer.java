package com.hansheng.block;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 2016/7/8.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        String data = null;
        Random r = new Random();

        System.out.println("�����������߳�");

        while (isRunning) {
            System.out.println("������������");
            try {
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = "data" + count.incrementAndGet();
                System.out.println("������" + data + "������С�����");

                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("��������ʧ��" + data);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("�˳��������߳�");
            }

        }


    }

    public void stop() {
        isRunning = false;
    }
}
