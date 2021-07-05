package medianfinder;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    PriorityQueue<Integer> maxPq = new PriorityQueue<>(10, Collections.reverseOrder());
    PriorityQueue<Integer> minPQ = new PriorityQueue<>(10);
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (maxPq.isEmpty()) {
            maxPq.add(num);
        }else {
            int max = maxPq.peek();
            if (num > max) {
                minPQ.add(num);
            }else {
                maxPq.add(num);
            }

            if (maxPq.size() > minPQ.size()+1) {
                int top = maxPq.poll();
                minPQ.add(top);
            }else {
                if (minPQ.size() > maxPq.size()) {
                    int top = minPQ.poll();
                    maxPq.add(top);
                }
            }
        }
    }

    public double findMedian() {
        if (maxPq.size() == minPQ.size()) {
            return (maxPq.peek() + minPQ.peek())/2.0;
        }else {
            return maxPq.peek();
        }
    }
}
