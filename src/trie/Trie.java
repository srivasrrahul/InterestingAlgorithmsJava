package trie;

import java.util.HashMap;

class Node {
    private boolean isTerminal = false;
    private HashMap<Character,Node> charMap = new HashMap<>();

    public Node() {
    }

    void addChar(String s,int index) {
        if (index == s.length()-1) {
            isTerminal = true;
        }else {
            Node node = charMap.getOrDefault(s.charAt(index+1),new Node());
            charMap.put(s.charAt(index+1),node);
            node.addChar(s,index+1);
        }
    }

    boolean ifTrie(String s,int index) {
        if (index == s.length()-1) {
            return isTerminal;
        }else {
            if (charMap.containsKey(s.charAt(index+1))) {
                return charMap.get(s.charAt(index+1)).ifTrie(s,index+1);
            }else {
                return false;
            }
        }
    }

    boolean prefix(String s,int index) {
        if (index == s.length()-1) {
            return true;
        }else {
            if (charMap.containsKey(s.charAt(index+1))) {
                return charMap.get(s.charAt(index+1)).prefix(s,index+1);
            }else {
                return false;
            }
        }
    }
}

class Root {
    private HashMap<Character,Node> charMap = new HashMap<>();

    public Root() {
    }

    void addChar(String s,int index) {
        Node node = charMap.getOrDefault(s.charAt(index),new Node());
        charMap.put(s.charAt(index),node);
        node.addChar(s,index);
    }

    boolean ifTrie(String s,int index) {
        if (charMap.containsKey(s.charAt(index))) {
            return charMap.get(s.charAt(index)).ifTrie(s,index);
        }else {
            return false;
        }
    }

    boolean prefix(String s,int index) {
        if (charMap.containsKey(s.charAt(index))) {
            return charMap.get(s.charAt(index)).prefix(s,index);
        }else {
            return false;
        }
    }
}

class Trie {

    Root root = new Root();
    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.addChar(word,0);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return root.ifTrie(word,0);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return root.prefix(prefix,0);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("jams");
        System.out.println(trie.startsWith("jam"));

    }
}
