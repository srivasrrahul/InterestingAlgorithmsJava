package browserhistory;

import java.util.HashMap;
import java.util.Stack;

class BrowserHistory {

    private Stack<String> current = new Stack<>();
    private Stack<String> forward = new Stack<>();
    private  String home;
    public BrowserHistory(String homepage) {
        this.home = homepage;
        current.add(homepage);
    }

    public void visit(String url) {
        current.add(url);
        forward.clear();
    }

    public String back(int steps) {
        for (int j = 0;j<steps;++j) {
            if (current.size() == 1) {
                break;
            }else {
                forward.push(current.peek());
                current.pop();
            }
        }



        return current.peek();

    }

    public String forward(int steps) {
        for (int j = 0;j<steps;++j) {
            if (forward.isEmpty() == false) {
                current.add(forward.peek());
                forward.pop();
            }else {
                break;
            }
        }



        return current.peek();
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */