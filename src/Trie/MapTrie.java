package Trie;

import java.util.HashMap;
import java.util.Map;

public class MapTrie implements Trie {
    class TrieNode{
        public Map<Character, TrieNode> children = new HashMap<>();
        public boolean isWord = false;
    }

    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (final char c : word.toCharArray()) {
            if(!node.children.containsKey(c)) node.children.put(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isWord = true;
      }
  
      private TrieNode find(String prefix) {
          TrieNode node = root;
          for (final char c : prefix.toCharArray()) {
            if(!node.children.containsKey(c)) return null;
            node = node.children.get(c);
          }
          return node;
      }
    
      public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isWord;
      }
    
      public boolean startsWith(String prefix) {
        return find(prefix) != null;
      }
  
      public int searchLongestMatch(String text, int start){
          TrieNode node = root;
          int end = -1;
          for(int i = start; i < text.length(); i++){
              char c = text.charAt(i);
              if(!node.children.containsKey(c)) break;
              else {
                node = node.children.get(c);
                if(node.isWord){
                    end = i;
                }
              }
          }
          //return the end index of the longest match
          return end;
      }


}
