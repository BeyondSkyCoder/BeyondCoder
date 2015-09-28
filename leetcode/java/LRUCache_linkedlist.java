package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRUCache_linkedlist {

	/*
	 *  Design and implement a data structure for Least Recently Used (LRU) cache. It should support 
	 *  the following operations: get and set.
	 *		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
	 *			otherwise return -1.
	 *		set(key, value) - Set or insert the value if the key is not already present. When the cache 
	 *			reached its capacity, it should invalidate the least recently used item before inserting 
	 *			a new item. 
	 *	need an efficient algorithm with O(1) search
	 */
	
	public class LRUCache {
	    private Map<Integer, Entry> map;	// for fast search
	    private Entry head;			// linked list
	    private int size;
	    private final int CAPACITY;
	    
	    private class Entry {
	        private int key;
	        private int val;
	        
	        // must use circular double linked list for O(1) search
	        private Entry prev, next;

	        private Entry ( ) {
			}	// this constructor is useless ?

	        public Entry (int key, int val) {
	            this.key = key;
	            this.val = val;
	        }
	    }
	    
	    public LRUCache(int capacity) {
	        map = new HashMap<>();	// map Integer to Entry class
	        head = new Entry();	// initialize the class
	        head.next = head;
	        head.prev = head;
	        CAPACITY = capacity;
	        size = 0;
	    }
	   
	    // LRU get, this is the key LRU algorithm portion
	    //	find the key from MAP, if found, remove from mid or somewhere, add to the first
	    //	This is to meet the so-called LRU cache algorithm
	    // 
	    public int get(int key) {
	        Entry node = map.get(key);
	        
	        if (node == null) return -1; // not found
	      
	        // found, use it (remove it from mid, add to first)
	        detach(node);
	        addFirst(node);
	        return node.val;
	    }
	   
	    // LRU set
	    //	add a new key and value pair the LRU cache
	    public void set(int key, int value) {
	        Entry node = map.get(key);
	        
	        /* existing, update value, remove and add to first */
	        if (node != null) {
	            detach(node);
	            node.val = value;
	            addFirst(node);
	        } else {	// non-existing
	        	
	        	if (size == CAPACITY) {
	        		// if full, kick the last one out, this what LRU means with LinkedList chaining
	                removeLast();
	                size--;
	            }
	            
	            // build a Entry, add to the first
	            node = new Entry(key, value);
	            addFirst(node);
	            size++;
	            map.put(key, node);
	        }
	    }
	   
	    // circular double linked list
	    public void detach(Entry node) {
	        node.prev.next = node.next;
	        node.next.prev = node.prev;
	    }
	   
	    public void addFirst(Entry node) {
	        node.prev = head;
	        node.next = head.next;
	        head.next = node;
	        node.next.prev = node;
	    }
	   
	    public void removeLast() {
	        Entry node = head.prev;
	        head.prev = node.prev;
	        node.prev.next = head;
	        node.prev = null;
	        node.next = null;
	        map.remove(node.key);
	    }
	}
	
	/* Below simple solution doesn't pass due to TEL(Time Exceed Limit) */
	
	public class LRUCache2 {
	    private int len;
	    private Queue<Integer> ll;
	    private HashMap<Integer, Integer> hm;
	    
	    public LRUCache2(int capacity) {
	        len = capacity;
	        hm = new HashMap<>();
	        ll = new LinkedList<>();
	    }
	    
	    public int get(int key) {
	        if (hm.containsKey(key)) {
	            // update recent using queue
	            ll.remove(key);	// this method is valid ?
	            ll.offer(key);
	            return hm.get(key);   
	        } else {
	            return -1;
	        }
	    }
	    
	    public void set(int key, int value) {
	        if (hm.containsKey(key)) {
	            ll.remove(key);
	        }
	        ll.offer(key);
	        hm.put(key, value);
	        
	        if (ll.size() >= len) {
	            // remove oldest from both map and queue until size is below len
	            int delkey = ll.poll();
	            if (delkey != -1) {
	                hm.remove(delkey);
	            }
	        }
	    }
	}
	
}
