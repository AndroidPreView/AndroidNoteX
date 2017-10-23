package com.hansheng.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hansheng on 2016/7/8.
 * put(anObject):��anObject�ӵ�BlockingQueue��,���BlockQueueû�пռ�,����ô˷������̱߳����ֱ��BlockingQueue�����пռ��ټ���.
 * LinkedBlockingQueue
 * ����LinkedBlockingQueueʵ�����̰߳�ȫ�ģ�ʵ�����Ƚ��ȳ������ԣ�����Ϊ�����������ߵ���ѡ��LinkedBlockingQueue ����ָ��������
 * Ҳ���Բ�ָ������ָ���Ļ���Ĭ�������Integer.MAX_VALUE��������Ҫ�õ�put��take������put�����ڶ�������ʱ�������ֱ���ж��г�Ա
 * �����ѣ�take�����ڶ��пյ�ʱ���������ֱ���ж��г�Ա���Ž�����
 */
public class ArrayBlockQueueTest {

    public static void main(String... args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "׼��������");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + "�Ѿ���������,����Ŀǰ��" + queue.size() + "������");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "׼��ȡ����");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ������,����Ŀǰ��" + queue.size() + ",������");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
