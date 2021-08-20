package evalbracketpair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class RetValue {
    private int limit;
    private String data;

    public RetValue(int limit, String data) {
        this.limit = limit;
        this.data = data;
    }

    public int getLimit() {
        return limit;
    }

    public String getData() {
        return data;
    }
}
class Solution {
    RetValue parseTillNext(String s,int start) {
        int j = start;
        StringBuilder data = new StringBuilder();
        for (;j < s.length() && s.charAt(j) != ')';++j) {
            data.append(s.charAt(j));
        }

        if (j == s.length()) {
            return new RetValue(s.length()-1,data.toString());
        }else {
            return new RetValue(j,data.toString());
        }
    }
    public String evaluate(String s, List<List<String>> knowledge) {
        StringBuilder solution = new StringBuilder();
        HashMap<String,String> values = new HashMap<>();
        for (List<String> keyVal : knowledge) {
            values.put(keyVal.get(0),keyVal.get(1));
        }
        for (int j = 0;j<s.length();) {
            if (s.charAt(j) != '(') {
                solution.append(s.charAt(j));
                ++j;
            }else {
                RetValue retValue = parseTillNext(s,j+1);
                j = retValue.getLimit();
                ++j;
                solution.append(values.get(retValue.getData()));
            }
        }

        return solution.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        LinkedList<List<String>> knowledge = new LinkedList<>();
        knowledge.add(new LinkedList<>(Arrays.asList("name","bob")));
        knowledge.add(new LinkedList<>(Arrays.asList("age","two")));
        System.out.println(solution.evaluate("(name)is(age)yearsold",knowledge));
    }
}
