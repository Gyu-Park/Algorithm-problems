/**
A TrainComposition is built by attaching and detaching wagons from the left and the right sides, 
efficiently with respect to time used.

For example, if we start by attaching wagon 7 from the left followed by attaching wagon 13, 
again from the left, we get a composition of two wagons (13 and 7 from left to right). 
Now the first wagon that can be detached from the right is 7 and the first that can be detached from the left is 13.

Implement a TrainComposition that models this problem.
**/
package Problems;

import java.util.LinkedList;
import java.util.List;

public class TrainComposition {

    class Node {
        public int value;
        public Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    Node node;
    List<Node> list = new LinkedList<>();

    public void attachWagonFromLeft(int wagonId) {
        if (list.isEmpty()) {
            node = new Node(wagonId, null, null);
            list.add(node);
        } else {
            node = new Node(wagonId, null, list.get(0));
            list.add(0, node);
        }
    }

    public void attachWagonFromRight(int wagonId) {
        if (list.isEmpty()) {
            node = new Node(wagonId, null, null);
            list.add(node);
        } else {
            node = new Node(wagonId, list.get(list.size() - 1), null);
            list.add(node);
        }
    }

    public int detachWagonFromLeft() {
        if (list.isEmpty())
            return -1;
        else if (list.size() == 1) {
            return list.remove(0).value;
        } else {
            list.get(1).left = null;
            return list.remove(0).value;
        }
    }

    public int detachWagonFromRight() {
        if (list.isEmpty())
            return -1;
        else if (list.size() == 1) {
            return list.remove(0).value;
        } else {
            list.get(list.size() - 2).right = null;
            return list.remove(list.size() - 1).value;
        }
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}