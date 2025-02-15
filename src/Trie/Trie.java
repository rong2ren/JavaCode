package Trie;

public interface Trie {
    public void insert(String word);
    public boolean search(String word);
    public boolean startsWith(String prefix);
    public int searchLongestMatch(String text, int start);
}
