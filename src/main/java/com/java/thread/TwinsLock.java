package com.java.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-07-25 10:20
 */
public class TwinsLock {
    public Sync sync = new Sync(2);

    public static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("count must large then zero!");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newCount = current - arg;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newCount = current + arg;
                if (compareAndSetState(current, newCount))
                    return true;
            }
        }
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock(){
        sync.tryReleaseShared(1);
    }
}
