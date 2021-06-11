package com.ckj.base.data;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class QueueCore {

    public class ArrayQueue {
        // 数组：items，数组大小：n
        private String[] items;
        private int n = 0;
        // head表示队头下标，tail表示队尾下标
        private int head = 0;
        private int tail = 0;

        // 申请一个大小为capacity的数组
        public ArrayQueue(int capacity) {
            items = new String[capacity];
            n = capacity;
        }

        // 入队
        public boolean enqueue(String item) {
            // 如果tail == n 表示队列已经满了
            if (tail == n) return false;
            items[tail] = item;
            ++tail;
            return true;
        }

        // 优化入队 ，发现不能入队，但是数组还没有满 ，进行数据迁移
        public boolean enqueueV2(String item) {

            if (tail == n) {
                if (head == 0) {
                    return false;
                }
                for (int i = 0; i < tail - head; i++) {
                    items[i] = items[head + i];
                }
                tail = tail - head;
                head = 0;
            }
            items[tail] = item;
            ++tail;
            return true;
        }

        // 出队
        public String dequeue() {
            // 如果head == tail 表示队列为空
            if (head == tail) return null;
            // 为了让其他语言的同学看的更加明确，把--操作放到单独一行来写了
            String ret = items[head];
            ++head;
            return ret;
        }
    }

    public static void main(String[] args) {

        ArrayQueue queue = new QueueCore().new ArrayQueue(16);

        for (int i = 0; i <= 15; i++) {
            boolean enqueue = queue.enqueue("item_is :" + i);
            log.info("enqueue result is " + enqueue);
        }

        while (queue.head != queue.tail) {
            String dequeue = queue.dequeue();
            log.info("dequeue item is " + dequeue);
        }

        for (int i = 0; i <= 15; i++) {
            boolean enqueue = queue.enqueue("item_is :" + i);
            log.info("enqueue result is " + enqueue);
        }

        for (int i = 0; i <= 17; i++) {
            boolean enqueue = queue.enqueueV2("item_is :" + i);
            log.info("enqueueV2 result is" + enqueue);
        }


    }
}
