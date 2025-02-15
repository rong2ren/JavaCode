package Trie;

import java.util.*;

public class KeywordTagger {

    public static void main(String[] args){

        Map<String, String> keywordMap = new HashMap<>();
        keywordMap.put("San", "person");
        keywordMap.put("Francisco", "person");
        keywordMap.put("San Francisco", "city");
        keywordMap.put("Airbnb", "business");
        keywordMap.put("city", "location");
        //key word -> tag

        String review = "I travelled to San Francisco for work and stayed at Airbnb.\n"
                      + "I really loved the city and the home where I stayed.\n"
                      + "I stayed with San and Francisco.\n"
                      + "They both were really good and san's hospitality was outstanding.";

        /*要求把所有关键词替换成 [<tag>]{<key word>}
         * I travelled to [city]{San Francisco} for work and stayed at [business]{Airbnb}.
I really loved the [location]{city} and the home where I stayed.
I stayed with [person]{San} and [person]{Francisco}.
They both were really good and [person]{san}'s hospitality was outstanding.
         */

        Trie trie = new MapTrie();
        for(Map.Entry<String, String> entry : keywordMap.entrySet()){
            trie.insert(entry.getKey());
        }

        StringBuilder result = new StringBuilder();
        
        int i = 0;
        while(i < review.length()){
            int end = trie.searchLongestMatch(review, i);
            if(end >= 0){
                String matched = review.substring(i, end + 1);
                result.append("[").append(keywordMap.get(matched)).append("]{").append(matched).append("}");
                i = end + 1;
            } else {
                result.append(review.charAt(i));
                i++;
            }
        }

        System.out.println(result.toString());
    }
}
