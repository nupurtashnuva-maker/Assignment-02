package assignment2;

/**
 * A singly linked list of integers implemented from scratch.
 * The list keeps both head and tail references so adding at either end is O(1).
 */
public class SinglyLinkedList {
    private static final class Node {
        private int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /** Adds a value at the beginning of the list. */
    public void addFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    /** Adds a value at the end of the list. */
    public void addLast(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Inserts a value at an index from 0 through size, inclusive. */
    public void insertAt(int index, int value) {
        checkPositionIndex(index);
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }

        Node previous = nodeAt(index - 1);
        Node node = new Node(value);
        node.next = previous.next;
        previous.next = node;
        size++;
    }

    /** Removes and returns the value at the requested index. */
    public int removeAt(int index) {
        checkElementIndex(index);
        if (index == 0) {
            int removed = head.value;
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return removed;
        }

        Node previous = nodeAt(index - 1);
        Node removedNode = previous.next;
        previous.next = removedNode.next;
        if (removedNode == tail) {
            tail = previous;
        }
        size--;
        return removedNode.value;
    }

    /** Returns the value at an index. */
    public int get(int index) {
        checkElementIndex(index);
        return nodeAt(index).value;
    }

    /** Replaces the value at an index and returns the old value. */
    public int set(int index, int value) {
        checkElementIndex(index);
        Node node = nodeAt(index);
        int oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    /** Returns the first index of value, or -1 when the value is absent. */
    public int indexOf(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.value == value) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    /** Reverses the links in place. */
    public void reverse() {
        Node previous = null;
        Node current = head;
        tail = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    /** Removes every node and resets the list to an empty state. */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns a copy of the stored values in list order. */
    public int[] toArray() {
        int[] values = new int[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            values[index++] = current.value;
            current = current.next;
        }
        return values;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            result.append(current.value);
            if (current.next != null) {
                result.append(" -> ");
            }
            current = current.next;
        }
        return result.append(']').toString();
    }

    private Node nodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is invalid for list size " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Insertion index " + index + " is invalid for list size " + size);
        }
    }
}
