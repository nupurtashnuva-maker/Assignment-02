package assignment2;

import java.util.Scanner;

/** Console application used to demonstrate both assignment data structures. */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equalsIgnoreCase(args[0])) {
            runDemo();
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("============================================");
            System.out.println("       DATA STRUCTURES - ASSIGNMENT 2");
            System.out.println("  Singly Linked List + Binary Tree");
            System.out.println("============================================");
            while (true) {
                System.out.println("\nMAIN MENU");
                System.out.println("1. Singly Linked List");
                System.out.println("2. Binary Tree");
                System.out.println("3. Run demonstration");
                System.out.println("0. Exit");
                int choice = readInt(scanner, "Choose an option: ");
                switch (choice) {
                    case 1:
                        linkedListMenu(scanner);
                        break;
                    case 2:
                        treeMenu(scanner);
                        break;
                    case 3:
                        runDemo();
                        break;
                    case 0:
                        System.out.println("Program closed. Thank you.");
                        return;
                    default:
                        System.out.println("Please choose one of the listed options.");
                }
            }
        } catch (InputEndedException e) {
            System.out.println("\nInput ended. Program closed safely.");
        }
    }

    private static void linkedListMenu(Scanner scanner) {
        SinglyLinkedList list = new SinglyLinkedList();
        while (true) {
            System.out.println("\nSINGLY LINKED LIST");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Insert at index");
            System.out.println("4. Remove at index");
            System.out.println("5. Get value at index");
            System.out.println("6. Set value at index");
            System.out.println("7. Search for a value");
            System.out.println("8. Reverse list");
            System.out.println("9. Clear list");
            System.out.println("10. Display list");
            System.out.println("11. Show size / empty state");
            System.out.println("0. Back to main menu");
            int choice = readInt(scanner, "Choose an option: ");
            try {
                switch (choice) {
                    case 1:
                        list.addFirst(readInt(scanner, "Value: "));
                        System.out.println("Added at the beginning: " + list);
                        break;
                    case 2:
                        list.addLast(readInt(scanner, "Value: "));
                        System.out.println("Added at the end: " + list);
                        break;
                    case 3: {
                        int index = readInt(scanner, "Index (0 through " + list.size() + "): ");
                        int value = readInt(scanner, "Value: ");
                        list.insertAt(index, value);
                        System.out.println("Updated list: " + list);
                        break;
                    }
                    case 4: {
                        int index = readInt(scanner, "Index to remove: ");
                        System.out.println("Removed value: " + list.removeAt(index));
                        System.out.println("Updated list: " + list);
                        break;
                    }
                    case 5: {
                        int index = readInt(scanner, "Index to read: ");
                        System.out.println("Value: " + list.get(index));
                        break;
                    }
                    case 6: {
                        int index = readInt(scanner, "Index to update: ");
                        int value = readInt(scanner, "New value: ");
                        System.out.println("Previous value: " + list.set(index, value));
                        System.out.println("Updated list: " + list);
                        break;
                    }
                    case 7: {
                        int value = readInt(scanner, "Value to search for: ");
                        int index = list.indexOf(value);
                        System.out.println(index == -1 ? "Value not found." : "Found at index " + index + ".");
                        break;
                    }
                    case 8:
                        list.reverse();
                        System.out.println("Reversed list: " + list);
                        break;
                    case 9:
                        list.clear();
                        System.out.println("List cleared: " + list);
                        break;
                    case 10:
                        System.out.println("List: " + list);
                        break;
                    case 11:
                        System.out.println("Size: " + list.size() + "; empty: " + list.isEmpty());
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Please choose one of the listed options.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void treeMenu(Scanner scanner) {
        BinaryTree tree = new BinaryTree();
        while (true) {
            System.out.println("\nBINARY TREE");
            System.out.println("Insertion fills the next available position, level by level.");
            System.out.println("1. Insert value");
            System.out.println("2. Search for a value");
            System.out.println("3. Remove first matching value");
            System.out.println("4. Show all traversals");
            System.out.println("5. Show height and node count");
            System.out.println("6. Clear tree");
            System.out.println("0. Back to main menu");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1: {
                    int value = readInt(scanner, "Value to insert: ");
                    tree.insert(value);
                    System.out.println("Inserted at the next available position.");
                    System.out.println("Level-order: " + tree.levelOrder());
                    break;
                }
                case 2: {
                    int value = readInt(scanner, "Value to search for: ");
                    System.out.println(tree.contains(value) ? "Value found." : "Value not found.");
                    break;
                }
                case 3: {
                    int value = readInt(scanner, "Value to remove: ");
                    System.out.println(tree.remove(value)
                            ? "First matching value removed."
                            : "Value not found.");
                    System.out.println("Level-order: " + tree.levelOrder());
                    break;
                }
                case 4:
                    System.out.println("In-order    (left, root, right): " + tree.inOrder());
                    System.out.println("Pre-order   (root, left, right): " + tree.preOrder());
                    System.out.println("Post-order  (left, right, root): " + tree.postOrder());
                    System.out.println("Level-order (breadth-first):     " + tree.levelOrder());
                    System.out.println("Note: in-order is not necessarily sorted in a general binary tree.");
                    break;
                case 5:
                    System.out.println("Nodes: " + tree.size());
                    System.out.println("Height (counted in nodes): " + tree.height());
                    break;
                case 6:
                    tree.clear();
                    System.out.println("Tree cleared.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose one of the listed options.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                throw new InputEndedException();
            }
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number, such as 12 or -4.");
            }
        }
    }

    private static void runDemo() {
        System.out.println("\n========== LINKED LIST DEMONSTRATION ==========");
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        System.out.println("Start:                  " + list);
        list.insertAt(1, 15);
        System.out.println("After insertAt(1, 15):  " + list);
        System.out.println("get(2):                 " + list.get(2));
        System.out.println("indexOf(30):            " + list.indexOf(30));
        System.out.println("removeAt(0):            " + list.removeAt(0));
        System.out.println("After removal:          " + list);
        list.reverse();
        System.out.println("After reverse():        " + list);
        System.out.println("Size:                   " + list.size());

        System.out.println("\n========== BINARY TREE DEMONSTRATION ==========");
        BinaryTree tree = new BinaryTree();
        int[] values = {10, 20, 30, 40, 50, 60, 70};
        for (int value : values) {
            tree.insert(value);
        }
        System.out.println("Inserted in level-order:  10, 20, 30, 40, 50, 60, 70");
        System.out.println("In-order:                 " + tree.inOrder());
        System.out.println("Pre-order:                " + tree.preOrder());
        System.out.println("Post-order:               " + tree.postOrder());
        System.out.println("Level-order:              " + tree.levelOrder());
        System.out.println("Search 60:                " + tree.contains(60));
        System.out.println("Height / node count:      " + tree.height() + " / " + tree.size());
        tree.remove(20);
        System.out.println("After remove(20):         " + tree.levelOrder());
        System.out.println("\nDemonstration complete.");
    }

    private static final class InputEndedException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
