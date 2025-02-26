package Trie;

class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public boolean isWord = false;
}
  
public class ArrayTrie implements Trie {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
      TrieNode node = root;
      for (final char c : word.toCharArray()) {
        final int i = c - 'a';
        if (node.children[i] == null)
          node.children[i] = new TrieNode();
        node = node.children[i];
      }
      node.isWord = true;
    }

    private TrieNode find(String prefix) {
        TrieNode node = root;
        for (final char c : prefix.toCharArray()) {
          final int i = c - 'a';
          if (node.children[i] == null)
            return null;
          node = node.children[i];
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
            if(node.children[c-'a'] == null) break;
            else {
                node = node.children[c-'a'];
                if(node.isWord){
                    end = i;
                }
            }
        }
        //return the end index of the longest match
        return end;
    }
    
  }