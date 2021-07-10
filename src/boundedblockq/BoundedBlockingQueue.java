package boundedblockq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {

    private LinkedList<Integer> m_Queue = new LinkedList<>();
    private int m_Capacity = 0;
    private ReentrantLock m_Lock = new ReentrantLock();
    private Condition m_Full = m_Lock.newCondition();
    private Condition m_Empty = m_Lock.newCondition();
    public BoundedBlockingQueue(int capacity) {
        m_Capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        try {
            m_Lock.lock();
            while (m_Queue.size() >= m_Capacity) {
                m_Full.await();
            }

            m_Queue.add(element);
            m_Empty.signal();
        } finally {
            m_Lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        int retValue = 0;
        try {
            m_Lock.lock();
            while (m_Queue.isEmpty()) {
                m_Empty.await();
            }

            retValue = m_Queue.pollFirst();
            m_Full.signal();
            return retValue;
        } finally {
            m_Lock.unlock();
        }
    }

    public int size() {
        try {
            m_Lock.lock(); //read lock
            return m_Queue.size();
        } finally {
            m_Lock.unlock();
        }
    }
}
