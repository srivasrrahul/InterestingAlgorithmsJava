package longestpalindromicsubstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class Tuple {
    private int low;
    private int high;

    public Tuple(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return low == tuple.low && high == tuple.high;
    }

    @Override
    public int hashCode() {
        return Objects.hash(low, high);
    }
}
class PalidnromeChecker {
    private String s;

    private HashMap<Tuple,Boolean> cache = new HashMap<>();
    public PalidnromeChecker(String s) {
        this.s = s;
    }

    String dp() {
        ArrayList<ArrayList<Boolean>> dp = new ArrayList<>();
        for (int j = 0;j<s.length();++j) {
            ArrayList<Boolean> lst = new ArrayList<>();
            for (int k = 0;k<s.length();++k) {
                lst.add(false);
            }

            dp.add(lst);
        }

        for (int j = 0;j<s.length();++j) {
            dp.get(j).set(j,true);
            if (j+1 < s.length()) {
                if (s.charAt(j) == s.charAt(j + 1)) {
                    dp.get(j).set(j + 1, true);
                } else {
                    dp.get(j).set(j + 1, false);
                }
            }

        }

        int begin = 2;
        int j = 0;
        int k = begin;

        while (j < s.length() && k < s.length()) {


            if (s.charAt(j) == s.charAt(k)) {
                if (j+1 < s.length() && k-1>=0 && dp.get(j+1).get(k-1)) {
                    dp.get(j).set(k,true);
                }
            }


            j = j + 1;
            k = k + 1;

            if (j >= s.length() || k >= s.length()) {
                ++begin;
                j = 0;
                k = begin;
            }
        }


        // for (ArrayList<Boolean> lst : dp) {
        //     System.out.println(lst);
        // }
        int low = 0;
        int high = 0;
        int maxLen = 1;
        for (int a = 0;a<s.length();++a) {
            for (int b = a;b<s.length();++b) {
                if (dp.get(a).get(b)) {
                    if (b-a+1 >= maxLen) {
                        low = a ;
                        high = b;
                        maxLen = b-a+1;
                    }
                }
            }
        }

        return s.substring(low,high+1);


    }

    boolean isPalindrome(int start,int end) {
        if (start >= end) {
            return true;
        }else {
            final boolean b = s.charAt(start) == s.charAt(end);
            if (start+1 == end) {
                return b;
            }else {
                Tuple tuple = new Tuple(start,end);
                if (cache.containsKey(tuple)) {
                    return cache.get(tuple);
                }

                boolean retValue = b && isPalindrome(start+1,end-1);
                cache.put(tuple,retValue);
                return retValue;
            }
        }
    }
}
class Solution {
    boolean isPalindrome(String s,int start,int end) {
        if (start >= end) {
            return true;
        }else {
            final boolean b = s.charAt(start) == s.charAt(end);
            if (start+1 == end) {
                return b;
            }else {
                return b && isPalindrome(s,start+1,end-1);
            }
        }
    }
    public String longestPalindrome(String s) {
        PalidnromeChecker palidnromeChecker = new PalidnromeChecker(s);
        return palidnromeChecker.dp();
    }
}