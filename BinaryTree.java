package assignment2;

/**
 * A general binary tree of integer values.
 *
 * Insertion fills the next available position in level-order, so the tree is
 * not a binary search tree: values are not arranged by numerical comparison.
 * Duplicate values are allowed. Search and removal find the first matching
 * value encountered in level-order.
 */
public class BinaryTree {
    private static final class Node {
        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    /**
     * Inserts a value at the first available child position in level-order.
     * This keeps the tree as compact as possible.
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            size = 1;
            return;
        }

        Node[] queue = new Node[size];
        int front = 0;
        int rear = 0;
        queue[rear++] = root;

        while (front < rear) {
            Node current = queue[front++];
            if (current.left == null) {
                current.left = newNode;
                size++;
                return;
            }
            queue[rear++] = current.left;

            if (current.right == null) {
                current.right = newNode;
                size++;
                return;
            }
            queue[rear++] = current.right;
        }

        // A valid non-empty tree always has an available position.
        throw new IllegalStateException("Could not find an insertion position.");
    }

    /** Searches the tree from top to bottom, left to right. */
    public boolean contains(int value) {
        if (root == null) {
            return false;
        }
        Node[] queue = new Node[size];
        int front = 0;
        int rear = 0;
        queue[rear++] = root;

        while (front < rear) {
            Node current = queue[front++];
            if (current.value == value) {
                return true;
            }
            if (current.left != null) {
                queue[rear++] = current.left;
            }
            if (current.right != null) {
                queue[rear++] = current.right;
            }
        }
        return false;
    }

    /**
     * Removes the first matching value in level-order. To retain a compact
     * shape, its node is replaced by the deepest, rightmost node, which is
     * then detached. Returns false if the value is absent.
     */
    public boolean remove(int value) {
        if (root == null) {
            return false;
        }

        Node[] queue = new Node[size];
        Node[] parents = new Node[size];
        int front = 0;
        int rear = 0;
        queue[rear] = root;
        parents[rear++] = null;

        Node target = null;
        Node deepest = null;
        Node deepestParent = null;

        while (front < rear) {
            Node current = queue[front];
            Node parent = parents[front];
            front++;

            if (target == null && current.value == value) {
                target = current;
            }
            deepest = current;
            deepestParent = parent;

            if (current.left != null) {
                queue[rear] = current.left;
                parents[rear++] = current;
            }
            if (current.right != null) {
                queue[rear] = current.right;
                parents[rear++] = current;
            }
        }

        if (target == null) {
            return false;
        }

        target.value = deepest.value;
        if (deepestParent == null) {
            root = null;
        } else if (deepestParent.left == deepest) {
            deepestParent.left = null;
        } else {
            deepestParent.right = null;
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Height is measured in nodes: empty tree = 0; a leaf = 1. */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /** Left subtree, node, right subtree. This is not necessarily sorted. */
    public String inOrder() {
        StringBuilder result = new StringBuilder();
        appendInOrder(root, result);
        return result.toString().trim();
    }

    private void appendInOrder(Node node, StringBuilder result) {
        if (node == null) {
            return;
        }
        appendInOrder(node.left, result);
        appendValue(result, node.value);
        appendInOrder(node.right, result);
    }

    /** Node, left subtree, right subtree. */
    public String preOrder() {
        StringBuilder result = new StringBuilder();
        appendPreOrder(root, result);
        return result.toString().trim();
    }

    private void appendPreOrder(Node node, StringBuilder result) {
        if (node == null) {
            return;
        }
        appendValue(result, node.value);
        appendPreOrder(node.left, result);
        appendPreOrder(node.right, result);
    }

    /** Left subtree, right subtree, node. */
    public String postOrder() {
        StringBuilder result = new StringBuilder();
        appendPostOrder(root, result);
        return result.toString().trim();
    }

    private void appendPostOrder(Node node, StringBuilder result) {
        if (node == null) {
            return;
        }
        appendPostOrder(node.left, result);
        appendPostOrder(node.right, result);
        appendValue(result, node.value);
    }

    /** Visits nodes top-to-bottom, left-to-right using an array-backed queue. */
    public String levelOrder() {
        if (root == null) {
            return "";
        }
        Node[] queue = new Node[size];
        int front = 0;
        int rear = 0;
        queue[rear++] = root;
        StringBuilder result = new StringBuilder();

        while (front < rear) {
            Node current = queue[front++];
            appendValue(result, current.value);
            if (current.left != null) {
                queue[rear++] = current.left;
            }
            if (current.right != null) {
                queue[rear++] = current.right;
            }
        }
        return result.toString().trim();
    }

    private void appendValue(StringBuilder result, int value) {
        if (result.length() > 0) {
            result.append(' ');
        }
        result.append(value);
    }
}
