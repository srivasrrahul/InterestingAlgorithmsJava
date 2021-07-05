package lettercombinationofphonenumber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Solution {
    HashMap<Character,String> map = new HashMap<>();
    Solution() {
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    public List<String> gen(String digits,String generatedStr,int current) {
        if (current == digits.length()) {
            return new LinkedList<>(Arrays.asList(generatedStr));
        }else {
            String s = map.get(digits.charAt(current));
            //System.out.println(s);
            List<String> retValue = new LinkedList<>();
            for (Character character : s.toCharArray()) {
                String newStr = generatedStr + character;
                //System.out.println(newStr);
                retValue.addAll(gen(digits,newStr,current+1));
            }

            return  retValue;

        }
    }
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new LinkedList<>();
        }
        return gen(digits,"",0);

    }
}
