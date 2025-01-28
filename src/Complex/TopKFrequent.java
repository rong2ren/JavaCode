package Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    public static void main(String[] args) {
        
    }

    /*
     * Question: Given a list of reviews, keywords & an integer k, return a list of top K most frequently appearing keywords in the reviews based on the following conditions:

The comparison of strings is case-insensitive.
Multiple occurances of a keyword in a review should be considred as a single mention.
If keywords are mentioned an equal number of times in reviews, sort alphabetically.
     */
    public List<String> topKFrequent(String words, int k) {
        //top k - priority queue
        String[] words_arr = words.split(" ");
        List<String> ans = new ArrayList<>();

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words_arr) {
            String lowercase_word = word.toLowerCase();
            map.put(lowercase_word, map.getOrDefault(lowercase_word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((a,b)->(map.get(a.toLowerCase()) - map.get(b.toLowerCase())));

        for (String word : map.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) ans.add(minHeap.poll());
        Collections.reverse(ans);

        return ans;
    }
}
