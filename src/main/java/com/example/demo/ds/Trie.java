package com.example.demo.ds;

import java.util.*;

public class Trie {
    private static class Node {
        Map<Character, Node> next = new HashMap<>();
        // store top-K product ids for quick suggestions
        Deque<Long> topIds = new ArrayDeque<>();
    }

    private final Node root = new Node();
    private final int k;

    public Trie(int k) { this.k = k; }

    public void insert(String word, long productId) {
        if (word == null) return;
        String norm = word.toLowerCase(Locale.ROOT);
        Node cur = root;
        for (char c : norm.toCharArray()) {
            cur = cur.next.computeIfAbsent(c, ch -> new Node());
            // maintain top-K (simple: add to front if not present)
            if (!cur.topIds.contains(productId)) {
                cur.topIds.addFirst(productId);
                while (cur.topIds.size() > k) cur.topIds.removeLast();
            }
        }
    }

    public List<Long> query(String prefix, int limit) {
        if (prefix == null) return List.of();
        String norm = prefix.toLowerCase(Locale.ROOT);
        Node cur = root;
        for (char c : norm.toCharArray()) {
            cur = cur.next.get(c);
            if (cur == null) return List.of();
        }
        List<Long> ids = new ArrayList<>(cur.topIds);
        return ids.subList(0, Math.min(limit, ids.size()));
    }
}
