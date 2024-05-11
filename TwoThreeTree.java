package Test_8;

import java.util.ArrayList;
import java.util.List;

class Node {
    List<Integer> keys;
    List<Node> children;
    boolean isLeaf;

    public Node() {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isLeaf = true;
    }
}

public class TwoThreeTree {
    private Node root;
    private long totalInsertTime;
    private long totalInsertIterations;
    private long totalSearchTime;
    private long totalSearchIterations;
    private long totalDeleteTime;
    private long totalDeleteIterations;

    public TwoThreeTree() {
        root = null;
        totalInsertTime = 0;
        totalInsertIterations = 0;
        totalSearchTime = 0;
        totalSearchIterations = 0;
        totalDeleteTime = 0;
        totalDeleteIterations = 0;
    }

    public void insert(int key) {
        long startTime = System.nanoTime();
        if (root == null) {
            root = new Node();
            root.keys.add(key);
        } else {
            insertHelper(root, key);
        }
        long endTime = System.nanoTime();
        totalInsertTime += (endTime - startTime);
    }

    private void insertHelper(Node node, int key) {
        totalInsertIterations++;
        if (node.isLeaf) {
            node.keys.add(key);
            node.keys.sort(Integer::compareTo);
            if (node.keys.size() == 3) {
                split(node);
            }
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) {
                i++;
            }
            insertHelper(node.children.get(i), key);
        }
    }

    private void split(Node node) {
        Node left = new Node();
        Node right = new Node();

        left.keys.add(node.keys.get(0));
        right.keys.add(node.keys.get(2));

        if (!node.children.isEmpty()) {
            left.children.add(node.children.get(0));
            left.children.add(node.children.get(1));
            right.children.add(node.children.get(2));
            right.children.add(node.children.get(3));
            left.isLeaf = false;
            right.isLeaf = false;
        }

        if (node == root) {
            Node newRoot = new Node();
            newRoot.keys.add(node.keys.get(1));
            newRoot.children.add(left);
            newRoot.children.add(right);
            newRoot.isLeaf = false;
            root = newRoot;
        } else {
            Node parent = getParent(root, node);
            parent.keys.add(node.keys.get(1));
            parent.children.remove(node);
            parent.children.add(left);
            parent.children.add(right);
            if (parent.keys.size() == 3) {
                split(parent);
            }
        }
    }

    private Node getParent(Node current, Node child) {
        if (current.children.contains(child)) {
            return current;
        } else {
            for (Node node : current.children) {
                Node parent = getParent(node, child);
                if (parent != null) {
                    return parent;
                }
            }
            return null;
        }
    }

    public boolean search(int key) {
        long startTime = System.nanoTime();
        boolean result = searchHelper(root, key);
        long endTime = System.nanoTime();
        totalSearchTime += (endTime - startTime);
        return result;
    }

    private boolean searchHelper(Node node, int key) {
        totalSearchIterations++;
        if (node == null) {
            return false;
        }
        if (node.keys.contains(key)) {
            return true;
        } else if (node.isLeaf) {
            return false;
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) {
                i++;
            }
            return searchHelper(node.children.get(i), key);
        }
    }

    public void delete(int key) {
        long startTime = System.nanoTime();
        if (root == null) {
            return;
        }
        deleteHelper(root, key);
        long endTime = System.nanoTime();
        totalDeleteTime += (endTime - startTime);
    }

    private void deleteHelper(Node node, int key) {
        totalDeleteIterations++;
        if (node == null) {
            return;
        }
        if (node.isLeaf) {
            node.keys.remove(Integer.valueOf(key));
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) {
                i++;
            }
            deleteHelper(node.children.get(i), key);
        }
    }
    public double getAverageInsertTime() {
        return totalInsertTime / (double) totalInsertIterations;
    }

    public double getAverageSearchTime() {
        return totalSearchTime / (double) totalSearchIterations;
    }

    public double getAverageDeleteTime() {
        return totalDeleteTime / (double) totalDeleteIterations;
    }

    public double getAverageInsertIterations() {
        return totalInsertIterations / 10000.0; // Общее количество итераций для всех вставок
    }

    public double getAverageSearchIterations() {
        return totalSearchIterations / 100.0; // Общее количество итераций для всех поисков
    }

    public double getAverageDeleteIterations() {
        return totalDeleteIterations / 1000.0; // Общее количество итераций для всех удалений
    }

    public long getTotalInsertTime() {
        return totalInsertTime;
    }

    public void setTotalInsertTime(long totalInsertTime) {
        this.totalInsertTime = totalInsertTime;
    }

    public long getTotalInsertIterations() {
        return totalInsertIterations;
    }

    public void setTotalInsertIterations(long totalInsertIterations) {
        this.totalInsertIterations = totalInsertIterations;
    }

    public long getTotalSearchTime() {
        return totalSearchTime;
    }

    public void setTotalSearchTime(long totalSearchTime) {
        this.totalSearchTime = totalSearchTime;
    }

    public long getTotalSearchIterations() {
        return totalSearchIterations;
    }

    public void setTotalSearchIterations(long totalSearchIterations) {
        this.totalSearchIterations = totalSearchIterations;
    }

    public long getTotalDeleteTime() {
        return totalDeleteTime;
    }

    public void setTotalDeleteTime(long totalDeleteTime) {
        this.totalDeleteTime = totalDeleteTime;
    }

    public long getTotalDeleteIterations() {
        return totalDeleteIterations;
    }

    public void setTotalDeleteIterations(long totalDeleteIterations) {
        this.totalDeleteIterations = totalDeleteIterations;
    }
}
