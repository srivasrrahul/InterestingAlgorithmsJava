package trie.replacewords;

import java.util.*;
import java.util.stream.Collectors;


class Node {
    private HashMap<Character,Node> characterNodeHashMap = new HashMap<>();
    private boolean isTerminal = false;
    void addWord(String s,int index) {
        if (index == s.length()-1) {
            isTerminal = true;
        }else {
            Node next = characterNodeHashMap.getOrDefault(s.charAt(index+1),new Node());
            characterNodeHashMap.put(s.charAt(index+1),next);
            next.addWord(s,index+1);
        }
    }

    List<Character> prefix(String s,int index) {
        if (isTerminal) {
            return new LinkedList<>(Arrays.asList(s.charAt(s.length() - 1)));
        }
        if (index == s.length()-1) {
            if (isTerminal) {
                return new LinkedList<>(Arrays.asList(s.charAt(s.length() - 1)));
            }else {
                return null;
            }
        }else {
            if (characterNodeHashMap.containsKey(s.charAt(index+1))) {
                List<Character> lst = characterNodeHashMap.get(s.charAt(index+1)).prefix(s,index+1);
                if (lst != null) {
                    lst.add(s.charAt(index));
                }
                return lst;
            }else {
                if (isTerminal) {
                    return new LinkedList<>(Arrays.asList(s.charAt(index)));
                }else {
                    return null;
                }
            }
        }
    }

    boolean search(String s,int index) {
        if (index == s.length()-1) {
            return isTerminal;
        }else {
            if (characterNodeHashMap.containsKey(s.charAt(index+1))) {
                return characterNodeHashMap.get(s.charAt(index+1)).search(s,index+1);
            }else {
                return false;
            }
        }
    }


}

class Root {
    HashMap<Character,Node> characterNodeHashMap = new HashMap<>();
    void addWord(String s,int index) {
        Node next = characterNodeHashMap.getOrDefault(s.charAt(index),new Node());
        characterNodeHashMap.put(s.charAt(index),next);
        next.addWord(s,index);
    }

    List<Character> prefix(String s,int index) {
        if (characterNodeHashMap.containsKey(s.charAt(index))) {
            return characterNodeHashMap.get(s.charAt(index)).prefix(s,index);
        }else {
            return null;
        }
    }

    boolean search(String s,int index) {
        if (characterNodeHashMap.containsKey(s.charAt(index))) {
            return characterNodeHashMap.get(s.charAt(index)).search(s,index);
        }else {
            return false;
        }
    }


}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Root root = new Root();
        for (String s : dictionary) {
            root.addWord(s,0);
        }

        String [] chArr = sentence.split(" ");
        StringBuilder retValue = new StringBuilder();
        for (String s : chArr) {
            List<Character> prefix = root.prefix(s,0);
            if (prefix != null) {
                //System.out.println(prefix);
                Collections.reverse(prefix);
                StringBuilder local = new StringBuilder();
                for (Character ch : prefix) {
                    local.append(ch);
                }

                retValue.append(local.toString());
            }else {
                retValue.append(s);
            }
        }

        retValue.setLength(retValue.length()-1);

        return retValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().replaceWords(new LinkedList<>(Arrays.asList("rat","cat")),"cattle"));
    }
}
