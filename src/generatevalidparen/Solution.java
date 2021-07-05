package generatevalidparen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    List<String> gen(String current,int open,int closed) {
        if (open == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(current);
            for (int j = 0;j<closed;++j) {
                stringBuilder.append(")");
            }
            return new LinkedList<>(Arrays.asList(stringBuilder.toString()));
        }else {
            //Three combinations
            //Open a new one
            //Close a new one
            List<String> r1 = gen(current+"(",open-1,closed);
            if (open < closed) {
                List<String> r2 = gen(current + ")", open, closed - 1);
                r1.addAll(r2);
            }


            return r1;

        }
    }
    public List<String> generateParenthesis(int n) {
        return gen("",n,n);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(8));
    }
}
