package Problems;

public class BrowserHistory {
    public class Node{
        String url;
        Node next, prev;
        public Node(String url) {
            this.url = url;
            next = null;
            prev = null;
        }
    }
    
    Node head, curr;
    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        curr = head;
    }
    
    public void visit(String url) {
        Node node = new Node(url);
        curr.next = node;
        node.prev = curr;
        curr = node;
    }
    
    public String back(int steps) {
        while (curr.prev != null && steps-- > 0)
            curr = curr.prev;
        return curr.url;
    }
    
    public String forward(int steps) {
        while (curr.next != null && steps-- > 0)
            curr = curr.next;
        return curr.url;
    }
    
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory("homepage");
        history.visit("url");
        System.out.println(history.forward(2));
    }
}
