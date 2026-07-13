package com.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class KFrequentWords {
    public static void main(String[] args) {
        KFrequentWords sol = new KFrequentWords();

        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(sol.topKFrequent(words1, 2));
        // Output: [i, love]

        String[] words2 = {"the","day","is","sunny","the","the","the","sunny","is","is", "day"};
        System.out.println(sol.topKFrequent(words2, 4));
        // Output: [the, is, sunny, day]
    }

    private List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String s : words) {
            hm.put(s, hm.getOrDefault(s, 0)+1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a,b) -> {
                    int freqA = hm.get(a);
                    int freqB = hm.get(b);
                    if(freqA != freqB) return freqB - freqA;
                    else return a.compareTo(b);
        });

        for (String s : hm.keySet()){
            pq.offer(s);
        }

        for (int i = 0 ; i < k ; i++) {
            result.add(pq.poll());
        }
        return result;
    }
}
