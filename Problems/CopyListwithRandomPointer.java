/**
 * A linked list of length n is given such that each node contains an additional random pointer, 
 * which could point to any node in the list, or null.
 * 
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, 
 * where each new node has its value set to the value of its corresponding original node. 
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list 
 * and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * 
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, 
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * 
 * Return the head of the copied linked list.
 * 
 * The linked list is represented in the input/output as a list of n nodes. 
 * Each node is represented as a pair of [val, random_index] where:
 * 
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 */
package Problems;

import java.util.*;

public class CopyListwithRandomPointer {
    public static Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Map<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        newHead = recursionForValAndNext(head.next, newHead, map);
        recursionForRandomPointer(head, newHead, map);
        return newHead;
    }

    private static Node recursionForValAndNext(Node node, Node copyNode, Map<Node, Node> map) {
        if (node == null)
            return copyNode;
        Node newNode = new Node(node.val);
        copyNode.next = newNode;
        map.put(node, newNode);
        newNode = recursionForValAndNext(node.next, newNode, map);
        return copyNode;
    }

    private static void recursionForRandomPointer(Node node, Node copyNode, Map<Node, Node> map) {
        if (node == null)
            return;
        if (node.random != null) {
            Node randomNode = map.get(node.random);
            copyNode.random = randomNode;
        }
        recursionForRandomPointer(node.next, copyNode.next, map);
    }

    // iterative solution (faster)
    public Node iterativeCopyRandomList(Node head) {
        if (head == null)
            return null;

        Map<Node, Node> map = new HashMap<Node, Node>();

        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        head.random = null;
        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;
        Node res = copyRandomList(head);
        while (res != null) {
            System.out.print("[" + res.val + ", ");
            if (res.random == null)
                System.out.print("null" + "]\n");
            else
                System.out.print(res.random.val + "]\n");
            res = res.next;
        }
    }
}
