package nestedlist;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    private List<NestedInteger> m_NestedList;
    private ListIterator<NestedInteger> itr;
    private Stack<ListIterator<NestedInteger>> stack = new Stack<>();
    private NestedInteger m_NestedInteger;

    public NestedIterator(List<NestedInteger> nestedList) {
        m_NestedList = nestedList;
        itr = m_NestedList.listIterator();
    }

    @Override
    public Integer next() {
        return m_NestedInteger.getInteger();

    }

    @Override
    public boolean hasNext() {
        //Puts it to right integral element
        while (stack.isEmpty() == false && itr.hasNext() == false) {
            itr = stack.pop();
        }

        m_NestedInteger = itr.next();
        while (m_NestedInteger.isInteger() == false) {
            itr.previous();
            stack.push(itr);
            itr = m_NestedInteger.getList().listIterator();
            m_NestedInteger = itr.next();
        }
        return itr.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class Solution {
}
