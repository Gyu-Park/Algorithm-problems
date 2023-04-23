package Problems;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
    Map<Integer, Node> hmap;
    int capacity;
    final Node head = new Node(-1, -1);
    final Node tail = new Node(-1, -1);

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.hmap = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // return value of the key if exists
        // if exists, put the pair at the end of list
        if (!hmap.containsKey(key)) {
            return -1;
        }

        Node node = hmap.get(key); // find the node
        putNodeEnd(node); // put the found node at the end of list
        return node.val;
    }

    public void put(int key, int value) {
        if (hmap.containsKey(key)) {
            // if key exists in the map, find its node and put it at the end
            // update the value
            Node node = hmap.get(key); // find the node
            node.val = value;
            this.hmap.put(key, node);
            putNodeEnd(node); // put the found node at the end of list
            return;
        }

        // key does not exist in the map
        // cache is full
        if (this.capacity <= this.hmap.size()) {
            // remove the head
            removeHead();
        }

        // create new node and put it at the end
        Node newNode = new Node(key, value);
        hmap.put(key, newNode);
        putNodeEnd(newNode);
    }

    private void removeHead() {
        hmap.remove(head.next.key);
        this.head.next = head.next.next;
        this.head.next.prev = head;
    }

    private void putNodeEnd(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.next = this.tail;
        node.prev = this.tail.prev;
        this.tail.prev = node;
        node.prev.next = node;
    }

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
